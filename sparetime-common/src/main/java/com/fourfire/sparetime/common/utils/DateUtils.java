package com.fourfire.sparetime.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuyi
 * @date 2020/11/29
 * @desc
 */

public class DateUtils {

    public static boolean isWeekend(String dateStr) {
        boolean isWeekend = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            isWeekend = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isWeekend;
    }
}
