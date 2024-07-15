package org.gucardev.utilities;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class DateUtilTest {

    @Test
    void testTruncateDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2023-07-15 13:45:30");
        Date truncatedDate = DateUtil.truncateDate(date);
        assertEquals("2023-07-15 00:00:00", sdf.format(truncatedDate));
    }

    @Test
    void testFormatDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2023-07-15");
        assertEquals("2023-07-15", DateUtil.formatDate(date, "yyyy-MM-dd"));
    }

    @Test
    void testParseDate() throws ParseException {
        String dateStr = "2023-07-15";
        Date date = DateUtil.parseDate(dateStr, "yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(dateStr, sdf.format(date));
    }

    @Test
    void testAddDays() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2023-07-15");
        Date newDate = DateUtil.addDays(date, 5);
        assertEquals("2023-07-20", sdf.format(newDate));
    }

    @Test
    void testIsBefore() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-07-15");
        Date date2 = sdf.parse("2023-07-20");
        assertTrue(DateUtil.isBefore(date1, date2));
        assertFalse(DateUtil.isBefore(date2, date1));
    }

    @Test
    void testIsAfter() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-07-15");
        Date date2 = sdf.parse("2023-07-20");
        assertTrue(DateUtil.isAfter(date2, date1));
        assertFalse(DateUtil.isAfter(date1, date2));
    }

    @Test
    void testIsEqual() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-07-15");
        Date date2 = sdf.parse("2023-07-15");
        assertTrue(DateUtil.isEqual(date1, date2));
    }

    @Test
    void testDaysBetween() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-07-15");
        Date date2 = sdf.parse("2023-07-20");
        assertEquals(5, DateUtil.daysBetween(date1, date2));
    }
}
