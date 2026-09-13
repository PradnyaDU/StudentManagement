package com.FirstProject.StudentManagement.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    public void sendEmailViaRedis() {

//        redisTemplate.opsForValue().set("pradnya", "pradnya@gmail.com");

        String email = redisTemplate.opsForValue().get("name");

        System.out.println("Email from Redis = " + email);
    }
}