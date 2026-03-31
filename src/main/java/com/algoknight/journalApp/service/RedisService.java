package com.algoknight.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Object value, long timeout, TimeUnit unit) {

        redisTemplate.opsForValue().set(key, value, timeout, unit);
        log.info("Value set in Redis with key: " + key);
    }

    public Object getValue(String key) {
        log.info("Value retrieved from Redis with key: " + key);
        return redisTemplate.opsForValue().get(key);

    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
