package com.fpoly.kickshop.controller;

import com.fpoly.kickshop.model.Customer;
import com.fpoly.kickshop.repository.CustomerRepository;
import com.fpoly.kickshop.security.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class OAuthGoogleController {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;
    private final GoogleIdTokenVerifier verifier;

    public OAuthGoogleController(CustomerRepository customerRepository, JwtUtil jwtUtil,
                                 @Value("${google.clientId}") String googleClientId) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;

        // Cache GoogleIdTokenVerifier
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
    }

//    @PostMapping("/google")
//    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
//        String token = request.get("token");
//
//        try {
//            GoogleIdToken idToken = verifier.verify(token);
//            if (idToken == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Google token"));
//            }
//
//            GoogleIdToken.Payload payload = idToken.getPayload();
//            String email = payload.getEmail();
//            String name = (String) payload.get("name");
//
//            // Kiểm tra xem người dùng đã tồn tại chưa
//            Optional<Customer> existingUser = customerRepository.findByEmail(email);
//            Customer user = existingUser.orElseGet(() -> {
//                Customer newUser = new Customer();
//                newUser.setEmail(email);
//                newUser.setName(name);
//                return customerRepository.save(newUser); // Đảm bảo có ID trước khi tạo token
//            });
//
//            // Tạo Access Token
//            String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail());
//
//            return ResponseEntity.ok(Map.of("accessToken", accessToken));
//        } catch (Exception e) {
//            e.printStackTrace(); // In lỗi ra console để debug
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
//        }
//    }
}
