package com.algoknight.journalApp.Scheduler;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.repository.UserRepositoryImp;
import com.algoknight.journalApp.service.EmailService;
import com.algoknight.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImp userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSentimentMail() {
        List<UserEntry> users = userRepository.getUsersForSA();
        for (UserEntry user : users) {
             String sentiment = sentimentAnalysisService.getSentimentFromLast7Days(user);
             emailService.sendEmail(user.getEmail(), "Sentiment Analysis Report", sentiment);
        }
    }
}
