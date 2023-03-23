package com.mjc.school.repository.beens;

import com.mjc.school.repository.util.NameValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Author {
    private static long idCounter = 1;

    private final long id;
    private String name;

    public Author(String name) {
        id = idCounter++;
        setName(name);
    }

    public void setName(String name) {
        int minLength = 3;
        int maxLength = 15;
        NameValidator.validateLength(name, minLength,
                maxLength, "Name");
        this.name = name;
    }
}
