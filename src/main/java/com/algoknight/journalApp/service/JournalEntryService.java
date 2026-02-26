package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.journalEntry;
import com.algoknight.journalApp.repository.EntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private EntryRepository entryRepository;

    public journalEntry save(journalEntry journal){
        return entryRepository.save(journal);
    }
    public List<journalEntry> getAll(){
        return entryRepository.findAll();
    }

    public Optional<journalEntry> findById(ObjectId myid){
        return entryRepository.findById(myid);
    }

    public void deleteById(ObjectId id){
         Optional<journalEntry> journal = entryRepository.findById(id);
         if(journal.isPresent())  entryRepository.deleteById(id);


    }
}

// controller -> service -> repository