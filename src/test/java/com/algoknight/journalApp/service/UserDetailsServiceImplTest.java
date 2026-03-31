package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserEntryRepository userEntryRepository;

    @Test
    void loadUserByUsername_Success() {
        UserEntry userEntry = new UserEntry();
        userEntry.setUsername("ram");
        userEntry.setPassword("123");
        userEntry.setRoles(new ArrayList<>());

        when(userEntryRepository.findByUsername("ram")).thenReturn(userEntry);

        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("ram", userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(userEntryRepository.findByUsername("unknown")).thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("unknown");
        });
    }
}
