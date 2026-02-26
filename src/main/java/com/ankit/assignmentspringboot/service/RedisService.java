package com.ankit.assignmentspringboot.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String ping() {
        return redisTemplate.getConnectionFactory().getConnection().ping();
    }

    public void saveWithTTL(String key, String value, long duration, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, duration, unit);
    }

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }
}