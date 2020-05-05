package com.bridgelabz.parkingLot;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {

    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static String date = dateFormat.format(new Date());
    public static long[] timeDiff = new long[4];

/*---------------------------- time difference -------------------------------------*/

    static String timeDifference(String start, String end) throws ParseException {
        Date dStart = dateFormat.parse(start);
        Date dEnd = dateFormat.parse(end);
        long diff = dEnd.getTime() - dStart.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        timeDiff[0]=diffDays;timeDiff[1]=diffHours;timeDiff[2]=diffMinutes;timeDiff[3]=diffSeconds;
        return diffDays+" days "+diffHours+" hours "+diffMinutes+" minutes "+diffSeconds+" seconds";
    }
}
