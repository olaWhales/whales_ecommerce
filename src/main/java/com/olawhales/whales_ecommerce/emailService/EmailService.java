package com.olawhales.whales_ecommerce.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service  // 👈 This makes it a Spring-managed bean
public class EmailService {

    @Autowired
    private JavaMailSender mailSender; // Ensure JavaMailSender is correctly configured

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("your-email@gmail.com");

        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }

    @Override
    public String toString() {
        return "EmailService{" +
                "mailSender=" + mailSender +
                '}';
    }
}
