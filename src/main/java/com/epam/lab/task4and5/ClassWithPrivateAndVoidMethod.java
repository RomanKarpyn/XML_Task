package com.epam.lab.task4and5;

import java.util.Arrays;
import java.util.List;

public class ClassWithPrivateAndVoidMethod {

    static char getRandomChar(char c1, char c2, char c3) {
        char[] charArray = {c1, c2, c3};
        return charArray[(int) (Math.random() * charArray.length)];
    }

    private String printMessage(String msg) {
        return msg + getRandomChar('r', 'y', 'e');
    }
    int sum() {
        return (int) (Math.random() * 4 + Math.random() * 4 - 2);
    }
    private double share() {
        return Math.random() * 50 / (Math.random() * 50 - 30);
    }


    List<Character> list() {
        return Arrays.asList(getRandomChar('s', 'd', 'f'), getRandomChar('s', 'd', 'f'), getRandomChar('s', 'd', 'f'));
    }
}
