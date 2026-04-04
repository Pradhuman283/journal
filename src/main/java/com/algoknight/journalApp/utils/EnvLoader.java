package com.algoknight.journalApp.utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnvLoader {
    private static boolean loaded = false;

    public static synchronized void load() {
        if (!loaded) {
            try {
                Dotenv dotenv = Dotenv.load();
                System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
                System.setProperty("REDIS_URI", dotenv.get("REDIS_URI"));
                System.setProperty("WEATHER_API_KEY", dotenv.get("WEATHER_API_KEY"));
                System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
                System.setProperty("jwt.secret", dotenv.get("jwt.secret"));
                log.info("Environment variables loaded from .env successfully.");
                loaded = true;
            } catch (Exception e) {
                log.warn("Could not load .env file. Falling back to system environment variables. Error: {}", e.getMessage());
                // Fallback is implicit since System.getProperty returns null if not set, 
                // and Spring will then look into actual environment variables.
            }
        }
    }
}
