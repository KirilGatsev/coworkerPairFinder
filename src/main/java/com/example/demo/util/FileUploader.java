package com.example.demo.util;

public interface FileUploader<T> {
    void readAndUploadFromFile(String filename);
    void saveEntityToDB(T entity);
}
