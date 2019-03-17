package com.doctor.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class OrderIdGenerator {

    private static final FastDateFormat dateFormat = FastDateFormat.getInstance("yyMMddHHmmss");

    public static String generateId() {
        StringBuilder sb = new StringBuilder();
        String dateStr = dateFormat.format(new Date());
        sb.append(dateStr);
        sb.append(RandomUtils.generateRandomStr(6));

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(OrderIdGenerator.generateId());
    }

}
