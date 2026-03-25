package com.algoknight.journalApp.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class EmailServiceTest {

    static {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
        System.setProperty("WEATHER_API_KEY", dotenv.get("WEATHER_API_KEY"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
    }

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendEmail("pradhumanrathore580@gmail.com", "Testing Email via SMTP",
                "This is the first time i am using SMTP and sending email via spring boot");
        log.info("Email sent successfully");
    }
}
