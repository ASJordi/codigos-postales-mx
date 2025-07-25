package dev.asjordi.persistence.repository;

import java.util.List;

public interface IDataReader<T> {

    List<T> getAll();

}
