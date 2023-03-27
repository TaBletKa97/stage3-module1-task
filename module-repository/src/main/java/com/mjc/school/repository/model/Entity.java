package com.mjc.school.repository.model;

import lombok.Data;

@Data
public abstract class Entity<K> {
    private K id;
}
