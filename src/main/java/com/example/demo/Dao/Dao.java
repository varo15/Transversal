package com.example.demo.Dao;

import java.util.List;

public interface Dao<T> {

    T getByID(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(int id);

}
