package org.gucardev.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void testIsNullOrEmpty() {
        assertTrue(StringUtil.isNullOrEmpty(null));
        assertTrue(StringUtil.isNullOrEmpty(""));
        assertFalse(StringUtil.isNullOrEmpty("test"));
    }

    @Test
    void testReverse() {
        assertEquals("tset", StringUtil.reverse("test"));
    }

    @Test
    void testCapitalize() {
        assertEquals("Test", StringUtil.capitalize("test"));
        assertEquals("Test", StringUtil.capitalize("Test"));
        assertEquals("T", StringUtil.capitalize("t"));
        assertNull(StringUtil.capitalize(null));
        assertEquals("", StringUtil.capitalize(""));
    }

    @Test
    void testJoin() {
        String[] array = {"a", "b", "c"};
        assertEquals("a,b,c", StringUtil.join(array, ","));
    }

    @Test
    void testIsNumeric() {
        assertTrue(StringUtil.isNumeric("123"));
        assertFalse(StringUtil.isNumeric("123a"));
        assertFalse(StringUtil.isNumeric(null));
        assertFalse(StringUtil.isNumeric(""));
    }
}
