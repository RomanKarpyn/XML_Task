package com.epam.lab.task1;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import static org.junit.Assert.*;

public class PassengerTest {
    private final static Logger log = Logger.getLogger(PassengerTest.class);

    private static Passenger passenger1;
    private static Passenger passenger2;

    @BeforeClass
    public static void initTest() {
        passenger1 = new Passenger("Women", 33);
        passenger2 = new Passenger("Son", 14);
    }

    @AfterClass
    public static void finishTest() {
        passenger1 = null;
        passenger2 = null;
    }

    @Test
    public void testAreAgeNotSame() {
        log.info("AreAgeNotSame");
        assertFalse(passenger1.getAge() < passenger2.getAge());
    }

    @Test
    public void testNotNull() {
        log.info("AreNameNotNull");
        assertNotNull(passenger1.getName());
    }

    @Test
    public void testNotSame() {
        log.info("AreNameNotSame");
        assertNotSame(passenger1.getName(), passenger2.getName());
    }

    @Test
    public void testIfArrayEquals() {
        log.info("AreArrayEquals");
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int[] array2 = {1, 2, 3, 4, 5, 6};

        assertArrayEquals(array1, array2);
    }

}