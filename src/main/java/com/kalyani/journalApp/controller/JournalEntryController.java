package com.kalyani.journalApp.controller;


import com.kalyani.journalApp.entity.JournalEntry;
import com.kalyani.journalApp.repository.JournalEntryRepo;
import com.kalyani.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @GetMapping("/abc")
    public List<JournalEntry> getAll() {

        return journalEntryService.getAll();
    }

    @PostMapping("/create")
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.save(journalEntry);
        return journalEntry;
    }

    @GetMapping("/id/{myId}")
    public Optional<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public Boolean deleteJournalEntryById(@PathVariable ObjectId myId) {
        journalEntryService.delteById(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
         JournalEntry old=journalEntryService.findById(id).orElse(null);
         if(old!=null)
         {
             old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
             old.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
         }
         journalEntryService.save(old);

        return old;
    }
}


