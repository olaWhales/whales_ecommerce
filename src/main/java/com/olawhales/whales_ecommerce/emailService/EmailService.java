package com.olawhales.whales_ecommerce.emailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service  //This makes it a Spring-managed bean
public class EmailService {

    @Autowired
    private JavaMailSender mailSender; // Ensure JavaMailSender is correctly configured

    public void sendEmail(String to, String subject, String body) {
        System.out.println("Attempting to send email to: " + to);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("atwhalescommercial@gmail.com");
//        message.setFrom("ajaditaoreed@gmail.com");

        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
