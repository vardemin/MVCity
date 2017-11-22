package com.vardemin.vcity.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String getTimeStrFromDate(Date date) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static Date getDateFromStr(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(date);
    }

    public static boolean isAdult(Date date) {
        Calendar now = Calendar.getInstance();
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(date);
        return now.get(Calendar.YEAR)-birthCal.get(Calendar.YEAR)>=18;
    }
}
