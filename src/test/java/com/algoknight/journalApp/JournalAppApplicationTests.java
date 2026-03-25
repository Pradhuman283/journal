package com.algoknight.journalApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JournalAppApplicationTests {

    static {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
        System.setProperty("WEATHER_API_KEY", dotenv.get("WEATHER_API_KEY"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
    }

	@Test
	void contextLoads() {
	}

}
