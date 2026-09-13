package com.FirstProject.StudentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> T getValue(String key, Class<T> responseType) throws Exception {
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(o.toString(), responseType);

    }

    public void setValue(String key, Object o, Long ttl) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        if (o == null) {
            return;
        }
        String value = objectMapper.writeValueAsString(o);
        redisTemplate.opsForValue().set(key, value,ttl, TimeUnit.SECONDS);
    }
}
