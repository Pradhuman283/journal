package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;

import com.algoknight.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;

    public UserEntry save(UserEntry journal){
        return userEntryRepository.save(journal);
    }
    public List<UserEntry> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId myid){
        return userEntryRepository.findById(myid);
    }

    public void deleteById(ObjectId id){
        Optional<UserEntry> user = userEntryRepository.findById(id);
        if(user.isPresent())  userEntryRepository.deleteById(id);


    }

    public UserEntry findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }
}
