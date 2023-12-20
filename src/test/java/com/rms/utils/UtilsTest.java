package com.rms.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

class UtilsTest {

    @Test
    void testGetDateFromString() {
        Date result = Utils.getDateFromString("strDate", "dateFormat");
        Assertions.assertNotNull(result);
    }
}