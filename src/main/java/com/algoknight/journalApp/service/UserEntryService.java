package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;

import com.algoknight.journalApp.repository.UserEntryRepository;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
     * instead of writing the this everytime in every file we can use @Slf4j
     * annotation at the top the class
     * private static final Logger logger =
     * LoggerFactory.getLogger(UserEntryService.class);
     */

    public boolean saveNewUser(UserEntry user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntryRepository.save(user);
            log.info("User saved successfully: " + user.getUsername());
            return true;
        } catch (Exception e) {
            log.error("Error while saving user: " + e.getMessage());
            return false;
        }

    }

    public void saveUser(UserEntry user) {
        userEntryRepository.save(user);
    }

    public List<UserEntry> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId myid) {
        return userEntryRepository.findById(myid);
    }

    public void deleteById(ObjectId id) {
        Optional<UserEntry> user = userEntryRepository.findById(id);
        if (user.isPresent())
            userEntryRepository.deleteById(id);

    }

    public UserEntry findByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }
}
