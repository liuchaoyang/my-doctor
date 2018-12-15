package com.doctor.converter;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private static final FastDateFormat datetimeFormater = FastDateFormat.getInstance("yyyy-MM-dd HH-mm-ss");
    private static final FastDateFormat dateFormater = FastDateFormat.getInstance("yyyy-MM-dd");

    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            date = datetimeFormater.parse(s);
        } catch (ParseException e) {
            try {
                date = dateFormater.parse(s);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return date;
    }
}
