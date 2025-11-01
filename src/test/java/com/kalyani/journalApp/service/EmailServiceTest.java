package com.kalyani.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailTest(){
        emailService.sendEmail("kalyanireddyenupala123@gmail.com","Testing java mail sender","Hi,How are you doing!");
    }
}
