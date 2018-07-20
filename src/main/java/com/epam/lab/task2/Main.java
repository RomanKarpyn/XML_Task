package com.epam.lab.task2;

import org.apache.log4j.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Employee employee = new Employee(2355,"frfr", "rewrwe", "d4","Lviv");
        CrudOperations crudOperations = new CrudOperations();
        log.info("Delete");crudOperations.delete(2581);
        log.info(crudOperations.findAll());
        log.info(crudOperations.findById(10102));
        crudOperations.deleteAndReplaceEmployyesToAnother(2345,9031);
    }
}

