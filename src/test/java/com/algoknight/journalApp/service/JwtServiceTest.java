package com.algoknight.journalApp.service;

import com.algoknight.journalApp.utils.EnvLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JwtServiceTest {

    static {
        EnvLoader.load();
    }

    @Autowired
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testGenerateToken() {
        UserDetails mockUser = User.builder()
                .username("testUser")
                .password("password")
                .authorities(Collections.emptyList())
                .build();
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(mockUser);

        String token = jwtService.generateToken("testUser");
        assertNotNull(token);
        System.out.println("Generated Token: " + token);
    }
}
