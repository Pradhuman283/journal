package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEntryServiceTest {

    @InjectMocks
    private UserEntryService userEntryService;

    @Mock
    private UserEntryRepository userEntryRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void saveNewUser_Success() {
        UserEntry user = new UserEntry();
        user.setUsername("shyam");
        user.setPassword("pass");

        when(passwordEncoder.encode("pass")).thenReturn("encoded_pass");

        userEntryService.saveNewUser(user);

        Assertions.assertEquals("encoded_pass", user.getPassword());
        verify(userEntryRepository, times(1)).save(user);
    }

    @Test
    void findByUsername_Success() {
        UserEntry user = new UserEntry();
        user.setUsername("shyam");

        when(userEntryRepository.findByUsername("shyam")).thenReturn(user);

        UserEntry result = userEntryService.findByUsername("shyam");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("shyam", result.getUsername());
    }
}
