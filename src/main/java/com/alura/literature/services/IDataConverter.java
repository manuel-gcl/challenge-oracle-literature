package com.alura.literature.services;

public interface IDataConverter {
    <T> T getData(String json, Class<T> tClass);
}
