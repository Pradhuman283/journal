package com.algoknight.journalApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.algoknight.journalApp.repository.UserRepositoryImp;
import com.algoknight.journalApp.entity.UserEntry;
import java.util.List;

@SpringBootTest
public class UserRepositoryImpTest {

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Test
    public void testGetUsersForSA() {
        List<UserEntry> users = userRepositoryImp.getUsersForSA();
        System.out.println(users);
    }
}
