package com.algoknight.journalApp.service;

import com.algoknight.journalApp.entity.UserEntry;
import com.algoknight.journalApp.enums.Sentiment;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public String getSentimentFromLast7Days(UserEntry user) {
        // Placeholder for real sentiment analysis logic
        return "Your sentiment for the last 7 days was POSITIVE.";
    }
}
