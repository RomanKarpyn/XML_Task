package com.epam.lab.task3;

import com.epam.lab.task4and5.TestClassWithPrivateAndVoidMethod;
import org.junit.runner.JUnitCore;

public class MainTest {

    public static void main(String[] args) {
        JUnitCore.runClasses(TestClassWithPrivateAndVoidMethod.class);
    }
}
