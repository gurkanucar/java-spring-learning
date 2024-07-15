package org.gucardev.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

    // Truncate Date to remove time part
    public static Date truncateDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // Format Date to String
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // Parse String to Date
    public static Date parseDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    // Add days to Date
    public static Date addDays(Date date, int days) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(days);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Compare two Dates (returns true if first date is before the second date)
    public static boolean isBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    // Compare two Dates (returns true if first date is after the second date)
    public static boolean isAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    // Compare two Dates (returns true if both dates are equal)
    public static boolean isEqual(Date date1, Date date2) {
        return date1.equals(date2);
    }

    // Calculate difference in days between two Dates
    public static long daysBetween(Date date1, Date date2) {
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(localDate1, localDate2);
    }

    // Get the current Date
    public static Date getCurrentDate() {
        return new Date();
    }

    // Get the current DateTime
    public static Date getCurrentDateTime() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
