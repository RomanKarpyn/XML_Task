package com.epam.lab.task2;

import java.util.List;

public interface DataDao {

     int delete(int id);

     List<Employee> findAll();


     Employee findById(int id);

     Employee findByName(String name);

     int insert(Employee employee);

     int update(Employee employee);
}
