package com.doctor.cache;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    private static final FastDateFormat hourFormatter = FastDateFormat.getInstance("yyyyMMddHH");
    private static final FastDateFormat minuteFormatter = FastDateFormat.getInstance("yyyyMMddHHmm");

    @Autowired
    private RedisClient redisClient;

    public boolean tryLock(String task, TimeUnit unit) {
        Date now = new Date();
        String key = task + "_";
        switch (unit) {
            case HOURS:
                key = key + hourFormatter.format(now);
                break;
            case MINUTES:
                key = key + minuteFormatter.format(now);
                break;
            default:
                return false;
        }
        boolean result = redisClient.setnx(key, "1");
        logger.info("RedisLock Get key:{}, result:{}", key, result);
        return result;
    }

}
