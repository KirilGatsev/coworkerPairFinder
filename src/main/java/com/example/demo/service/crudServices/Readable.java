package com.example.demo.service.crudServices;

import java.util.List;

public interface Readable<T> {
    T getEntityById(int id);
    List<T> getAllEntities();
}
