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


    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody journalEntry journal,@PathVariable String username){
     try{

         journalEntry saved = journalEntryService.save(journal,username);
         return new ResponseEntity<>(saved,HttpStatus.CREATED);}
     catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getJournalById(@PathVariable ObjectId myId){
           Optional<journalEntry> journalEntry = journalEntryService.findById(myId);
           if(journalEntry.isPresent()) return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id){
        Optional<journalEntry> journal = journalEntryService.findById(id);
        if(journal.isPresent()){
            journalEntryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //@PutMapping("id/{id}")
//        public ResponseEntity<?> updatejournalById(@PathVariable ObjectId id,@RequestBody journalEntry updated){
//            Optional<journalEntry> old = journalEntryService.findById(id);
//            if(old.isPresent()){
//               old.get().setTitle(updated.getTitle() != null && updated.getTitle() != ""?updated.getTitle():old.get().getTitle());
//                old.get().setContent(updated.getContent() != null && updated.getContent() != ""?updated.getContent():old.get().getContent());
//                journalEntryService.save(old.get());
//                return new ResponseEntity<>(old,HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }



