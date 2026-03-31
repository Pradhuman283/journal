package com.algoknight.journalApp.contoller;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.service.JournalEntryService;
import com.algoknight.journalApp.service.UserEntryService;
import com.algoknight.journalApp.service.WeatherService;

import lombok.extern.slf4j.Slf4j;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.algoknight.journalApp.api_response.WeatherApiResponse;
import com.algoknight.journalApp.dto.UserEntryDTO;
import com.algoknight.journalApp.service.JwtService;

@RestController
@RequestMapping("/User")
@Slf4j
public class UserController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/journals")
    public ResponseEntity<List<journalEntry>> getAllUserJournal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntry userEntry = userEntryService.findByUsername(username);
        if (userEntry != null)
            return new ResponseEntity<>(userEntry.getJournalEntries(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("signup")
    public ResponseEntity<UserEntry> signup(@RequestBody UserEntryDTO userDTO) {
        UserEntry user = new UserEntry();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userEntryService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserEntryDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
            String token = jwtService.generateToken(userDTO.getUsername());
            log.info("User logged in successfully: " + userDTO.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping()
    public ResponseEntity<UserEntry> updateUser(@RequestBody UserEntryDTO user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntry userInDb = userEntryService.findByUsername(username);
        if (userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userEntryService.saveNewUser(userInDb);
            log.info("User updated successfully: " + user.getUsername());
        } else {
            log.error("User not found with username: " + username);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<UserEntry> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntry userInDb = userEntryService.findByUsername(username);
        if (userInDb != null) {
            userEntryService.deleteById(userInDb.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("weather/{city}")
    public ResponseEntity<WeatherApiResponse> weather(@PathVariable String city) {
        WeatherApiResponse weather = weatherService.getWeather(city);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }
}
