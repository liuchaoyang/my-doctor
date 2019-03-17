package com.doctor.util;

import java.util.Random;

public class RandomUtils {

    public static String generateRandomStr(int size) {
        char origin[] = {'0','1','2','3','4','5','6','7','8','9'
                , 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
                , 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(62);
            sb.append(origin[nextInt]);
        }
        return sb.toString();
    }

}
