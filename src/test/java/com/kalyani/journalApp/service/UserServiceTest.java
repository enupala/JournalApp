package com.kalyani.journalApp.service;

import com.kalyani.journalApp.repository.UserRepo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo  userRepo;
   @ParameterizedTest
   @CsvSource({
       "indu","kalyani"
   })
    public void testFindUserByUsername(String name) {
       assertNotNull(userRepo.findByUserName(name));

    }
}
