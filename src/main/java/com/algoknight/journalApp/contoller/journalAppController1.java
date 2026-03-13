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

@RestController
@RequestMapping("/journal")
public class journalAppController1 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<journalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody journalEntry journal, @PathVariable String username) {
        try {

            journalEntry saved = journalEntryService.save(journal, username);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{username}/{myId}")
    public ResponseEntity<journalEntry> getJournalById(@PathVariable ObjectId myId, @PathVariable String username) {
        Optional<journalEntry> journalEntry = journalEntryService.findById(myId, username);
        if (journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id, @PathVariable String username) {
        Optional<journalEntry> journal = journalEntryService.findById(id, username);
        if (journal.isPresent()) {
            journalEntryService.deleteById(id, username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<?> updatejournalById(@PathVariable ObjectId id, @PathVariable String username,
            @RequestBody journalEntry updated) {
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
