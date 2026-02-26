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
    public List<journalEntry> getAll(){
          return journalEntryService.getAll();
           }


    @PostMapping("/add")
    public void createEntry(@RequestBody journalEntry journal){
        System.out.println("Before save");
         journal.setDate(LocalDateTime.now());
         journalEntry saved = journalEntryService.save(journal);
         System.out.println("Saved id: "+saved.getId());
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getJournalById(@PathVariable ObjectId myId){
           Optional<journalEntry> journalEntry = journalEntryService.findById(myId);
           if(journalEntry.isPresent()) return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{id}")
    public boolean deleteJournalEntry(@PathVariable ObjectId id){
             journalEntryService.deleteById(id);
             return true;

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updatejournalById(@PathVariable ObjectId id,@RequestBody journalEntry updated){
        Optional<journalEntry> old = journalEntryService.findById(id);
        if(old.isPresent()){
           old.get().setTitle(updated.getTitle() != null && updated.getTitle() != ""?updated.getTitle():old.get().getTitle());
            old.get().setContent(updated.getContent() != null && updated.getContent() != ""?updated.getContent():old.get().getContent());
            journalEntryService.save(old.get());
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    }

