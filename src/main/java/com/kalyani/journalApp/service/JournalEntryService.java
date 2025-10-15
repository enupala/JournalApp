package com.kalyani.journalApp.service;

import com.kalyani.journalApp.entity.JournalEntry;
import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.JournalEntryRepo;
import com.kalyani.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void save(JournalEntry journalEntry, String userName) {
     try{
         User user = userService.getUserByName(userName);
         JournalEntry saved = journalEntryRepo.save(journalEntry);
         user.getJournalEntries().add(saved);
       //  user.setUserName(null);
         userService.save(user);
     }
     catch(Exception e){
         System.out.println(e.getMessage());
         throw new  RuntimeException("An error occurred while saving the journal entry",e);
     }
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


