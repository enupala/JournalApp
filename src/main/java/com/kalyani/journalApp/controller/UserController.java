package com.kalyani.journalApp.controller;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepo;
import com.kalyani.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User exisitngUser = userService.updateUser(userName);
        if (exisitngUser != null) {
            exisitngUser.setUserName(user.getUserName());
            exisitngUser.setPassword(user.getPassword());
            userRepo.save(exisitngUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}


