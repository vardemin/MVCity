package com.vardemin.vcity.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xavie on 14.08.2017.
 */

public class DateUtil {
    public static String getStrFromDate(Date date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
    public static Date getDateFromStr(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(date);
    }
}
