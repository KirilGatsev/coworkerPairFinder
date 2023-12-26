package com.example.demo.service.crudServices;

public interface CrudService<T, S> extends Creatable<T>, Readable<T>, Updateable<T, S>, Deletable{
}
