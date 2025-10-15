package com.kalyani.journalApp.controller;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepo;
import com.kalyani.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

   /* @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }*/


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User exisitngUser = userService.getUserByName(userName);
        if (exisitngUser != null) {
            exisitngUser.setUserName(user.getUserName());
            exisitngUser.setPassword(user.getPassword());
            userService.saveNewUser(exisitngUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User deletedUser = userService.deleteByUserName(userName);

        if (deletedUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
}


