package com.epam.lab.task2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.epam.lab.task1.PassengerTest;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({
        PassengerTest.class
})

public class TestSuite {
}