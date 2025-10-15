package com.kalyani.journalApp.service;

import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo  userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userRepo.findByUserName(username);
       UserDetails userDetails= org.springframework.security.core.userdetails.User.builder()
               .username(user.getUserName())
               .password(user.getPassword())
               .roles(user.getRoles().toArray(new String[0]))
               .build();
       return userDetails;
    }
}
