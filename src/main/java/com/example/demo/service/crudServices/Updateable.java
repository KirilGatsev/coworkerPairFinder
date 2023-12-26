package com.example.demo.service.crudServices;

public interface Updateable<T, S> {
    T updateEntity(T entity, S id);
}
