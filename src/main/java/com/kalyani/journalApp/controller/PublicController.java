package com.kalyani.journalApp.controller;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.service.CustomUserDetailsServiceImpl;
import com.kalyani.journalApp.service.UserService;
import com.kalyani.journalApp.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsServiceImpl  userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            log.info("entering inside try");
          log.info("user name is "+user.getUserName());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            log.info("validated username and password");
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt= jwtUtil.generateJwtToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Exception occurred while createAuthentication token ",e);
            return new ResponseEntity<>(" Incorrect Username and Password ",HttpStatus.BAD_REQUEST);
        }
    }
}
