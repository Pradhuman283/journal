package com.algoknight.journalApp.contoller;

import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/journal")
@Slf4j
public class journalAppController1 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<journalEntry> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalEntryService.getAllEntriesOfUser(username);
    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody journalEntry journal) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntry saved = journalEntryService.save(journal, username);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating entry for user {}: ", SecurityContextHolder.getContext().getAuthentication().getName(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{myId}")
    public ResponseEntity<journalEntry> getJournalById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<journalEntry> journalEntry = journalEntryService.findById(myId, username);
        if (journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<journalEntry> journal = journalEntryService.findById(id, username);
        if (journal.isPresent()) {
            journalEntryService.deleteById(id, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updatejournalById(@PathVariable ObjectId id,
            @RequestBody journalEntry updated) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<journalEntry> old = journalEntryService.findById(id, username);
        if (old.isPresent()) {
            journalEntry oldEntry = old.get();
            if (updated.getTitle() != null && !updated.getTitle().trim().isEmpty()) {
                oldEntry.setTitle(updated.getTitle());
            }
            if (updated.getContent() != null && !updated.getContent().trim().isEmpty()) {
                oldEntry.setContent(updated.getContent());
            }
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
