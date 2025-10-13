package com.kalyani.journalApp.service;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void save(User user) {
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void delteById(ObjectId id) {
        userRepo.deleteById(id);
    }

    public User updateUser(String userName) {
        return userRepo.findByUserName(userName);
    }

    public User getUserByName(String userName) {
        return userRepo.findByUserName(userName);
    }

}


