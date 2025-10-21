package com.kalyani.journalApp.controller;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/create-user")
    public void addUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
