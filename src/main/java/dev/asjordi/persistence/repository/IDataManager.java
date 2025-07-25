package dev.asjordi.persistence.repository;

public interface IDataManager<T> {

    T getById(int id);
    void add(T entity);
    void delete(int id);

}
