package com.kalyani.journalApp.scheduler;

import com.kalyani.journalApp.entity.JournalEntry;
import com.kalyani.journalApp.entity.User;
import com.kalyani.journalApp.repository.UserRepositoryImpl;
import com.kalyani.journalApp.service.EmailService;
import com.kalyani.journalApp.service.SentimentAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    //@Scheduled(cron = "0 0 9 * * SUN")
    @Scheduled(cron = " 0 * * ? * * ")
    public void fetchUsersAndSendSentimentAnalysisMail(){
        List<User> users=userRepository.getUserForSentimentAnalysis();
        for(User user:users){
            List<JournalEntry>journalEntries  = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now()
                            .minus(25, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), " Sentiment for last 7 days ", sentiment);
            log.info("Scheduler is running at  " + LocalDateTime.now());

        }
    }

}
