package com.doctor.util;

import java.util.regex.Pattern;

public class MobileUtils {
    private static final String pattern = "^((1[358][0-9])|(14[57])|(17[0678])|(19[7]))\\d{8}$";

    public static boolean isValid(String mobile) {
        return Pattern.matches(pattern, mobile);
    }

}
