package com.fpoly.kickshop.service;

import com.fpoly.kickshop.model.OTP;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPService {
    private final Map<String, OTP> otpCache = new ConcurrentHashMap<>();

    public String generateOTP(String email) {
        // Tạo OTP ngẫu nhiên (6 chữ số)
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        // Lưu OTP cùng thời gian hết hạn
        OTP otpObject = new OTP(email, otp, LocalDateTime.now().plusSeconds(90));
        otpCache.put(email, otpObject);
        OTP otpObject2 = otpCache.get(email);
        System.out.println("...." + otpObject2.getOtp());
        System.out.println("...." + otpObject2.getEmail());
        return otp;
    }

    public boolean validateOTP(String email, String inputOtp) {
        System.out.println("check otp" + email);
        System.out.println(inputOtp);
        OTP otpObject = otpCache.get(email);
        System.out.println("...." + otpObject.getOtp());
        if (otpObject == null) return false;

        // Kiểm tra OTP hợp lệ và còn hiệu lực
        if (otpObject.getOtp().equals(inputOtp) && LocalDateTime.now().isBefore(otpObject.getExpiryTime())) {
            otpCache.remove(email); // Xóa OTP sau khi dùng
            return true;
        }
        return false;
    }
}
