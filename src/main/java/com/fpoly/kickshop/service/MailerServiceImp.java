package com.fpoly.kickshop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailerServiceImp implements MailerService {
    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendPasswordResetEmail(String email, String otp) throws MessagingException {
        // Tạo nội dung email
        String subject = "Password Reset Request";
        String body = "<p>Hello,</p>" +
                "<p>We received a request to reset your password. Use the OTP below to reset it:</p>" +
                "<h3 style='color:blue;'>" + otp + "</h3>" +
                "<p>This OTP is valid for 90 seconds. If you did not request a password reset, please ignore this email.</p>";

        // Tạo MimeMessage
        MimeMessage message = sender.createMimeMessage();

        // Thiết lập thông tin email với MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom("hieuttps37675@fpt.edu.vn"); // Email người gửi mặc định
        helper.setTo(email); // Email người nhận
        helper.setSubject(subject); // Tiêu đề
        helper.setText(body, true); // Nội dung HTML

        // Gửi email
        sender.send(message);
    }

    public void sendOTP(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp + ". This code is valid for 90 seconds.");
        sender.send(message);
    }
}
