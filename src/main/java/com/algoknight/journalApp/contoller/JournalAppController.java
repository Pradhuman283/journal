package com.algoknight.journalApp.contoller;

import com.algoknight.journalApp.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalAppController {

    private Map<Long, journalEntry> journalEntries = new HashMap<>();


    @GetMapping()
    public List<journalEntry> getAll(){
       return new ArrayList<>(journalEntries.values());
    }


    @PostMapping()
    public void createEntry(){

    }

    @GetMapping("/{myId}")
    public journalEntry getJournalById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
}
