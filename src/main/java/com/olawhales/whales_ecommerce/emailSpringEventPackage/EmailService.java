package com.olawhales.whales_ecommerce.emailSpringEventPackage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class EmailService {
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

public void sendEmail(String to, String subject, String text) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // 'true' enables HTML

        mailSender.send(message);
        System.out.println("Email sent successfully!");
    } catch (MessagingException e) {
        System.err.println("Failed to send email: " + e.getMessage());
        throw new RuntimeException(e);  // Keeps only one catch block
    }
}

    @Override
    public String toString() {
        return "EmailService{" +
                "mailSender=" + mailSender +
                '}';
    }
}
