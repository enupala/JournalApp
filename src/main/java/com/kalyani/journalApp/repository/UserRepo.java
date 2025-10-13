package com.kalyani.journalApp.repository;

import com.kalyani.journalApp.entity.JournalEntry;
import com.kalyani.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}
