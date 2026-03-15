package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.repository.EntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class JournalEntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public journalEntry save(journalEntry journal, String username) {
        UserEntry user = userEntryService.findByUsername(username);
        if (user != null) {
            journal.setDate(LocalDateTime.now());
            journalEntry saved = entryRepository.save(journal);
            user.getJournalEntries().add(saved);
            userEntryService.saveUser(user);
            return saved;
        }
        throw new RuntimeException("User not found with username: " + username);
    }

    public List<journalEntry> getAll() {
        return entryRepository.findAll();
    }

    public List<journalEntry> getAllEntriesOfUser(String username) {
        UserEntry user = userEntryService.findByUsername(username);
        if (user != null) {
            return user.getJournalEntries();
        }
        throw new RuntimeException("User not found with username: " + username);
    }

    public Optional<journalEntry> findById(ObjectId myid, String username) {
        UserEntry user = userEntryService.findByUsername(username);
        if (user != null) {
            return entryRepository.findById(myid);
        }
        throw new RuntimeException("User not found with username: " + username);
    }

    public void deleteById(ObjectId id, String username) {
        Optional<journalEntry> journal = entryRepository.findById(id);
        if (journal.isPresent())
            entryRepository.deleteById(id);

    }

    public void saveEntry(journalEntry journal) {
        entryRepository.save(journal);
    }
}

// controller -> service -> repository