package id.co.flipbox.mvvmstarter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class CalendarUtils
{
    public static Calendar parseDate (String date, String format) throws ParseException
    {
        Calendar lCalendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        lCalendar.setTime(formatter.parse(date));
        return lCalendar;
    }

    public static String getMonthName (int month)
    {
        String months[] = {"JAN", "FEB", "MAR", "APR", "MEI", "JUN", "JUL", "AUG", "SEPT", "OKT", "NOV", "DES"};
        return months[month];
    }

    public static String getDayName (int dayOfWeek)
    {
        String days[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
        return days[dayOfWeek];
    }
}
