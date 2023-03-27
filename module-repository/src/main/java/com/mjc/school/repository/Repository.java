package com.mjc.school.repository;

import com.mjc.school.repository.model.Entity;

import java.util.List;

public interface Repository<T extends Entity<ID> , ID> {
    List<T> readAll();
    T readById(ID id);
    T create(T entity);
    T update(T entity);
    boolean deleteById(ID id);
}
