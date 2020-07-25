package com.bohniman.vmsmaintenance.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil
 */
public class DateUtil {

    public static String getFormattedDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        return df.format(date);
    }

    public static String getFormattedDateTimeWithMilliseconds(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(date);
    }

    public static String getFormattedDateTimeWithSeconds(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static Date getDateFromDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(df.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.parse(date);
    }

    public static String getFormattedTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
        return df.format(date);
    }

    public static String getFormattedTimeNotSecond(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        return df.format(date);
    }

    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}