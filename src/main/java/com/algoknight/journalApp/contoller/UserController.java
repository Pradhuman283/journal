package com.algoknight.journalApp.contoller;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.service.JournalEntryService;
import com.algoknight.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping()
    public List<UserEntry> getAll() {
        return userEntryService.getAll();
    }

    @GetMapping("{username}")
    public List<UserEntry> getAllEUserEntries() {
        return userEntryService.getAll();
    }

    @GetMapping("id/{username}")
    public ResponseEntity<UserEntry> getJournalById(@PathVariable String username) {
        UserEntry userEntry = userEntryService.findByUsername(username);
        if (userEntry != null)
            return new ResponseEntity<>(userEntry, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserEntry user) {
        userEntryService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntry user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntry userInDb = userEntryService.findByUsername(username);
        if (userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntry userInDb = userEntryService.findByUsername(username);
        if (userInDb != null) {
            userEntryService.deleteById(userInDb.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
