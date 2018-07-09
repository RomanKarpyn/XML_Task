package com.epam.lab.task1;

import org.apache.log4j.Logger;

public class Passenger {
    private String name;
    private int age;
    private final static Logger log = Logger.getLogger(Passenger.class);

    public Passenger(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }


}
