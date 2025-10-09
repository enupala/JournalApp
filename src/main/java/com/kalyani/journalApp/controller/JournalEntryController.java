package com.kalyani.journalApp.controller;



import com.kalyani.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries=new HashMap<>();

    @GetMapping("/abc")
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping("/create")
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id,@RequestBody JournalEntry journalEntry){
        return journalEntries.put(id, journalEntry);
    }
}


