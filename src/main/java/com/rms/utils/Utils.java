package com.rms.utils;
import com.rms.exception.CommonsException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date getDateFromString(String strDate, String dateFormat) {
        try {
            DateFormat format = new SimpleDateFormat(dateFormat);
            Date date = format.parse(strDate);

            return date;
        } catch (Exception ex) {
            throw new CommonsException("");
        }
    }
}
