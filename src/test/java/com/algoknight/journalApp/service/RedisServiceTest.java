package com.algoknight.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisConnection() {
        // Test basic connection by setting and getting a value
        String key = "name1";
        String value = "Noone";

        redisTemplate.opsForValue().set(key, value);

        String retrievedValue = (String) redisTemplate.opsForValue().get(key);

        assertThat(retrievedValue).isEqualTo(value);

        redisTemplate.delete(key);
    }
}
