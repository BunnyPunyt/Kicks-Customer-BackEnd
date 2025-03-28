package com.fpoly.kickshop.service;

import jakarta.mail.MessagingException;

public interface MailerService {
    // Gửi email đặt lại mật khẩu
    void sendPasswordResetEmail(String email, String otp) throws MessagingException;
}
