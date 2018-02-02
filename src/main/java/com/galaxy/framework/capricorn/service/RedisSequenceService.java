package com.galaxy.framework.capricorn.service;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisSequenceService {

    @Autowired
    private RedissonClient redissonClient;

    public String generate(String category) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(category);
        String code = String.format("%08d", atomicLong.incrementAndGet());
        return code;
    }
}
