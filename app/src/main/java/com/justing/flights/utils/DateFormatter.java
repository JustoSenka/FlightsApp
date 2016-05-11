package com.justing.flights.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JustInG on 5/10/2016.
 */
public class DateFormatter {

    /**
     DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
     String date = df.format(Calendar.getInstance().getTime());

     "yyyy.MM.dd G 'at' HH:mm:ss z" ---- 2001.07.04 AD at 12:08:56 PDT
     "hh 'o''clock' a, zzzz" ----------- 12 o'clock PM, Pacific Daylight Time
     "EEE, d MMM yyyy HH:mm:ss Z"------- Wed, 4 Jul 2001 12:08:56 -0700
     "yyyy-MM-dd'T'HH:mm:ss.SSSZ"------- 2001-07-04T12:08:56.235-0700
     "yyMMddHHmmssZ"-------------------- 010704120856-0700
     "K:mm a, z" ----------------------- 0:08 PM, PDT
     "h:mm a" -------------------------- 12:08 PM
     "EEE, MMM d, ''yy" ---------------- Wed, Jul 4, '01
     */

    private static DateFormat toYear = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat toMonth = new SimpleDateFormat("MM-dd HH:mm");
    private static DateFormat toTime = new SimpleDateFormat("HH:mm");
    private static DateFormat toFull = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getYear(Date date){
        return toYear.format(date);
    }

    public static String getMonth(Date date){
        return toMonth.format(date);
    }

    public static String getTime(Date date){
        return toTime.format(date);
    }

    public static String getFull(Date date){
        return toFull.format(date);
    }

    public static Date fromYear(String date) throws ParseException {
        return toYear.parse(date);
    }

    public static Date fromMonth(String date) throws ParseException {
        return toMonth.parse(date);
    }

    public static Date fromTime(String date) throws ParseException {
        return toTime.parse(date);
    }

    public static Date fromFull(String date) throws ParseException {
        return toFull.parse(date);
    }
}
