package com.epam.lab.task25.dao;

import java.util.List;

public interface BaseDAO<T> {

     int delete(int id);

     List<T> findAll();


     T findById(int id);

     T findByName(String name);

     int insert(T employee);

     int update(int id, String name, String number);
}
