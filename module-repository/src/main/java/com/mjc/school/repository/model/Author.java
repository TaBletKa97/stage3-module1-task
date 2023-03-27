package com.mjc.school.repository.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Author extends Entity<Long> {
    private static long idCounter = 1;

    @Setter
    private String name;

    public Author(String name) {
        setId(idCounter++);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
