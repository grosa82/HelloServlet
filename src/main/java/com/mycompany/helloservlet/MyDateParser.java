package com.mycompany.helloservlet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateParser {

    public static Date parse(String text) {
        /* i.e. 2015-02-28T00:29:34 */
        String[] dateTxt = text.split("T");
        int year = Integer.parseInt(dateTxt[0].substring(0, 4));
        int month = Integer.parseInt(dateTxt[0].substring(5, 7));
        int day = Integer.parseInt(dateTxt[0].substring(8, 10));
        int hour = Integer.parseInt(dateTxt[1].substring(0, 2));
        int minute = Integer.parseInt(dateTxt[1].substring(3, 5));
        int second = Integer.parseInt(dateTxt[1].substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static String toString(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return df.format(date);
    }
}
