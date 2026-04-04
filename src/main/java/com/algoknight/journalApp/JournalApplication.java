package com.algoknight.journalApp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.authentication.AuthenticationManager;

import com.algoknight.journalApp.utils.EnvLoader;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class JournalApplication {

    public static void main(String[] args) {
        EnvLoader.load();
        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean
    public com.mongodb.client.MongoClient mongoClient() {
        return com.mongodb.client.MongoClients.create(System.getProperty("MONGO_URI"));
    }

    @Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Application started successfully");
        };
    }

}
