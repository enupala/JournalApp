package com.kalyani.journalApp.repository;

import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testSave() {
    Assertions.assertNotNull(userRepository.getUserForSentimentAnalysis());

    }
}
