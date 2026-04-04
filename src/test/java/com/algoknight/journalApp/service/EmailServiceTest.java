package com.algoknight.journalApp.service;

import com.algoknight.journalApp.utils.EnvLoader;
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
        EnvLoader.load();
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
