package com.kalyani.journalApp.service;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void save(User user) {
        userRepo.save(user);
    }

    public void saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        }
        catch (Exception e) {
           // logger.error("Error occurred for {} : ",user.getUserName(),e);
            log.error("Error occurred for {} : ",user.getUserName(),e);
            logger.info("Error occurred for {} : ",user.getUserName(),e);
            logger.warn("Error occurred for {} : ",user.getUserName(),e);
         //  these both needs customisation
          logger.debug("Error occurred for {} : ",user.getUserName(),e);
            logger.trace("Error occurred for {} : ",user.getUserName(),e);
        }
    }

    public void saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
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
    public User deleteByUserName(String userName){
        return userRepo.deleteByUserName(userName);
    }

    public User getUserByName(String userName) {
        return userRepo.findByUserName(userName);
    }


}


