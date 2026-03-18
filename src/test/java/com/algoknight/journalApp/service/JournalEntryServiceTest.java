package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.repository.EntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JournalEntryServiceTest {

    @InjectMocks
    private JournalEntryService journalEntryService;

    @Mock
    private EntryRepository entryRepository;

    @Mock
    private UserEntryService userEntryService;

    @Test
    void save_Success() {
        UserEntry user = new UserEntry();
        user.setUsername("ram");
        user.setJournalEntries(new ArrayList<>());

        journalEntry entry = new journalEntry();
        entry.setTitle("Title");

        when(userEntryService.findByUsername("ram")).thenReturn(user);
        when(entryRepository.save(any(journalEntry.class))).thenReturn(entry);

        journalEntry result = journalEntryService.save(entry, "ram");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Title", result.getTitle());
        verify(userEntryService, times(1)).saveUser(user);
    }

    @Test
    void save_UserNotFound() {
        when(userEntryService.findByUsername("unknown")).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            journalEntryService.save(new journalEntry(), "unknown");
        });
    }
}
