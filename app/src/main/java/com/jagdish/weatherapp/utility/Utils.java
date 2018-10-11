package com.jagdish.weatherapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getFormattedDate(String date) {
//        String date="Mar 10, 2016 6:30:00 PM";
//        2018-10-10 15:00:00
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss aaa");
            date = spf.format(newDate);
        } catch (ParseException parseEx) {
            parseEx.printStackTrace();
        }
        return date;
    }
}
