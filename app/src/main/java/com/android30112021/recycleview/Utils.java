package com.android30112021.recycleview;

import java.util.Calendar;

public class Utils {
    public static long getTime(int hour , int minute , int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, minute, second);
        return calendar.getTimeInMillis();
    }
}
