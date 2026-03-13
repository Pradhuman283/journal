package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userEntryRepository.findByUsername(username);
        if (user != null) {
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles() != null && !user.getRoles().isEmpty()
                            ? user.getRoles().toArray(new String[0])
                            : new String[] { "USER" })
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
