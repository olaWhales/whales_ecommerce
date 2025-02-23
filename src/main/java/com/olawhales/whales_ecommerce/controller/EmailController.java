package com.olawhales.whales_ecommerce.controller;

import com.olawhales.whales_ecommerce.emailSpringEventPackage.EmailEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final ApplicationEventPublisher eventPublisher;

    public EmailController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String text) {
        eventPublisher.publishEvent(new EmailEvent(this, to, subject, text));
        return "Email request received and will be processed!";
    }

    @Override
    public String toString() {
        return "EmailController{" +
                "eventPublisher=" + eventPublisher +
                '}';
    }
}
