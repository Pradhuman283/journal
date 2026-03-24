package com.algoknight.journalApp.contoller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algoknight.journalApp.dto.UserEntryDTO;
import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminContoller {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all-users")
    public List<UserEntry> getAllUsers() {
        log.info("Admin accessed all users");
        return userEntryService.getAll();
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> addAmin(@RequestBody UserEntryDTO userdto) {
        UserEntry user = new UserEntry();
        user.setUsername(userdto.getUsername());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setRoles(Arrays.asList("ADMIN"));
        // user.setRoles(userdto.getRoles());
        log.info("Admin added user");
        return new ResponseEntity<>(userEntryService.saveNewUser(user), HttpStatus.OK);
    }
}
