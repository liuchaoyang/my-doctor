package com.doctor.cache;

public interface RedisClient {
    boolean setnx(String key, String value);
}
