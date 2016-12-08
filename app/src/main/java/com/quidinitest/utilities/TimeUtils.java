package com.quidinitest.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static Long convertTimeStampToMilli(String timeStamp) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = df.parse(timeStamp.replace("T", " ").replace("Z", ""));
        return date.getTime();
    }

    public static String convertTimeToClockFormat(Long time) {
        return new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date(time));
    }
}
