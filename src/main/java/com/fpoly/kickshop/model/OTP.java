package com.fpoly.kickshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data

public class OTP {
    private String email;
    private String otp;
    private LocalDateTime expiryTime;

    public OTP() {
    }

    public OTP(String email, String otp, LocalDateTime expiryTime) {
        this.email = email;
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
