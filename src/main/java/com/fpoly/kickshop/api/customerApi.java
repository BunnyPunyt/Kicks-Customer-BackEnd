package com.fpoly.kickshop.api;

import com.fpoly.kickshop.model.Customer;
import com.fpoly.kickshop.service.CustomerService;
import com.fpoly.kickshop.service.MailerServiceImp;
import com.fpoly.kickshop.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class customerApi {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private MailerServiceImp mailerService;


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (otpService.validateOTP(email, otp)) {
            // Kích hoạt tài khoản
            Customer customer = customerService.loadCustomerByEmail(email);
            if (customer != null) {
                customer.setStatus(true);
                customerService.saveCustomer(customer);
                return ResponseEntity.ok("Account verified successfully!");
            }
            return ResponseEntity.status(400).body("Customer not found!");
        }

        return ResponseEntity.status(400).body("Invalid OTP or OTP expired!");
    }

    // Lấy thông tin Customer theo ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(404).body("Customer not found!");
        }
        return ResponseEntity.ok(customer);
    }

    // Cập nhật thông tin Customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(404).body("Customer not found!");
        }

        customer.setName(updatedCustomer.getName());
        customer.setPhone(updatedCustomer.getPhone());
        customerService.saveCustomer(customer);

        return ResponseEntity.ok("Customer updated successfully!");
    }



}
