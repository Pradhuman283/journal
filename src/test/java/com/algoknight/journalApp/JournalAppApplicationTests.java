package com.algoknight.journalApp;

import com.algoknight.journalApp.utils.EnvLoader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JournalAppApplicationTests {

    static {
        EnvLoader.load();
    }

    @Test
    void contextLoads() {
    }

}
