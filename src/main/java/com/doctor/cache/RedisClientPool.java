package com.doctor.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018/10/29.
 */
@Component
public class RedisClientPool implements RedisClient {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean setnx(String key, String value) {
        BoundValueOperations<Object, Object> operations = redisTemplate.boundValueOps(key);
        System.out.println("RedisClientPool..........setnx");
        return operations.setIfAbsent(value);
    }

}
