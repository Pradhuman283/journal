package com.algoknight.journalApp.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JwtServiceTest {

    @BeforeAll
    static void setup() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("jwt.secret", dotenv.get("jwt.secret"));
    }

    @Autowired
    private JwtService jwtService;

    @Test
    public void testGenerateToken() {
        String token = jwtService.generateToken("testUser");
        assertNotNull(token);
        System.out.println("Generated Token: " + token);
    }
}
