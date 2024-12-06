package dev.asjordi.database.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T entity);
    void delete(int id);
}
