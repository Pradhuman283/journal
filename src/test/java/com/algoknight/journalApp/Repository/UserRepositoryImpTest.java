package com.algoknight.journalApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.algoknight.journalApp.repository.UserRepositoryImp;
import com.algoknight.journalApp.entity.UserEntry;
import java.util.List;

@SpringBootTest
public class UserRepositoryImpTest {

    static {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.load();
        System.setProperty("MONGO_URI", dotenv.get("MONGO_URI"));
        System.setProperty("WEATHER_API_KEY", dotenv.get("WEATHER_API_KEY"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
    }

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Test
    public void testGetUsersForSA() {
        List<UserEntry> users = userRepositoryImp.getUsersForSA();
        System.out.println(users);
    }
}
