package com.example.demo.service.crudServices;

public interface Creatable<T> {
    T saveEntity(T entity);
}
