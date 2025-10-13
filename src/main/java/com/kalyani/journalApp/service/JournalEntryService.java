package com.kalyani.journalApp.service;

import com.kalyani.journalApp.entity.JournalEntry;
import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.JournalEntryRepo;
import com.kalyani.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    public void save(JournalEntry journalEntry, String userName) {
        User user = userService.getUserByName(userName);
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userRepo.save(user);
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void delteById(ObjectId id) {
        journalEntryRepo.deleteById(id);
    }

}


