package com.fpoly.kickshop.controller;

import com.fpoly.kickshop.model.*;
import com.fpoly.kickshop.repository.CustomerRepository;
import com.fpoly.kickshop.security.JwtUtil;
import com.fpoly.kickshop.service.CustomerService;
import com.fpoly.kickshop.service.MailerService;
import com.fpoly.kickshop.service.MailerServiceImp;
import com.fpoly.kickshop.service.OTPService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPService otpService;

    @Autowired
    private MailerServiceImp mailerService;

    @Autowired
    private CustomerRepository customerRepository;


    private GoogleIdTokenVerifier verifier;

    // ✅ API Đăng ký
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        if (customerService.existsByEmail(customer.getEmail())) {
            return ResponseEntity.badRequest().body("Email has already been used!");
        }

        try {
            // Tạo OTP và gửi email
            String otp = otpService.generateOTP(customer.getEmail());
            mailerService.sendOTP(customer.getEmail(), otp);

            // Lưu thông tin khách hàng tạm thời mà chưa kích hoạt tài khoản
            customer.setPassword(passwordEncoder.encode(customer.getPassword())); // Mã hóa mật khẩu
            customer.setStatus(false); // Chưa kích hoạt
            customerService.saveCustomer(customer);

            return ResponseEntity.ok("OTP sent to email. Please verify!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed! Please try again.");
        }
    }

    // ✅ API Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer loginRequest, HttpServletResponse response) {
        Customer customer = customerService.loadCustomerByEmail(loginRequest.getEmail());

        if (customer == null) {
            return ResponseEntity.status(401).body("Could not find customer!");
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(customer.getId(), customer.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(customer.getId());

            // Gửi refresh token qua Cookie
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(true);
            refreshTokenCookie.setPath("/api/auth/refresh");
            refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
            refreshTokenCookie.setAttribute("SameSite", "Strict"); // Bảo mật hơn
            response.addCookie(refreshTokenCookie);

            // Trả về access token trong body
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            return ResponseEntity.ok(tokens);
        }

        return ResponseEntity.status(401).body("Email or password incorrect!");
    }

    // ✅ API làm mới Access Token bằng Refresh Token
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(401).body("No refresh token found!");
        }

        try {
            String userId = jwtUtil.extractId(refreshToken);
            if (jwtUtil.validateToken(refreshToken, userId)) {
                Customer customer = customerService.findById(Integer.parseInt(userId));
                if (customer == null) {
                    return ResponseEntity.status(401).body("Customer not found!");
                }

                String newAccessToken = jwtUtil.generateAccessToken(customer.getId(), customer.getEmail());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", newAccessToken);
                return ResponseEntity.ok(tokens);
            }
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(401).body("Refresh token expired!");
        } catch (JwtException e) {
            return ResponseEntity.status(401).body("Invalid refresh token!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during token refresh.");
        }

        return ResponseEntity.status(401).body("Invalid refresh token!");
    }

    // API OAuthGoogle
    @Autowired
    public AuthController(CustomerRepository customerRepository, JwtUtil jwtUtil,
                                 @Value("${google.clientId}") String googleClientId) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;

        // Cache GoogleIdTokenVerifier
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        System.out.println("Received Google Token: " + token); // Debug

        try {
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Google token"));
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Kiểm tra xem người dùng đã tồn tại chưa
            Optional<Customer> existingUser = customerRepository.findByEmail(email);
            Customer user = existingUser.orElseGet(() -> {
                Customer newUser = new Customer();
                newUser.setEmail(email);
                newUser.setName(name);
                // Đặt mật khẩu mặc định là chuỗi ngẫu nhiên hoặc để trống
                newUser.setPassword(UUID.randomUUID().toString());
                // Gán số điện thoại mặc định (hoặc để trống)
                newUser.setPhone("");
                return customerRepository.save(newUser);
            }); 

            // Tạo Access Token
            String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail());

            System.out.println("Generated Access Token: " + accessToken); // Debug

            return ResponseEntity.ok(Map.of("accessToken", accessToken));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
        }
    }


}
