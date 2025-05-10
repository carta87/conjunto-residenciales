package com.microservice.auth.service;

import java.util.List;
import java.util.Optional;

public interface IRepositoryGenery<T, I> {

    List<T> findAll();

    Optional<T> findById(I id);

    T create(T entity);

    T update(I id, T entity);

    void delete(I id);
}
