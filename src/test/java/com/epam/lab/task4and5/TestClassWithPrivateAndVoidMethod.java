package com.epam.lab.task4and5;

import com.epam.lab.task1.PassengerTest;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TestClassWithPrivateAndVoidMethod {

    private final static Logger log = Logger.getLogger(TestClassWithPrivateAndVoidMethod.class);
    private static ClassWithPrivateAndVoidMethod classWithPrivateAndVoidMethod;


    @BeforeClass
    public static void initTest() {
        classWithPrivateAndVoidMethod = new ClassWithPrivateAndVoidMethod();
    }

    @AfterClass
    public static void finishTest() {
        classWithPrivateAndVoidMethod = null;
    }

    @SuppressWarnings(value = "unchecked")
    @Test
    public void testShare() {
        Class cl = ClassWithPrivateAndVoidMethod.class;
        try {
            Method method = cl.getDeclaredMethod("share");
            method.setAccessible(true);
            assertNotEquals(0, method.invoke(classWithPrivateAndVoidMethod));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Test
    public void testPrintMes() {
        Class cl = ClassWithPrivateAndVoidMethod.class;
        try {

            Method method = cl.getDeclaredMethod("printMessage", String.class);
            method.setAccessible(true);

            assertEquals("Romana", method.invoke(classWithPrivateAndVoidMethod, "Roman"));
        } catch (Exception e) {

            log.error(e.getMessage());
        }
    }

    @Rule
    public MethodRule watchman = new TestWatchman() {
        public void failed(Throwable e, FrameworkMethod method) {
            log.error("Test " + method.getName() + " failed: " + e.getMessage());
        }
    };

}