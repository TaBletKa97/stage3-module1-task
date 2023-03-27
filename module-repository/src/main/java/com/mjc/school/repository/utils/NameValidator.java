package com.mjc.school.repository.utils;

public class NameValidator {
    public static void validateLength(String source, int minLength
            , int maxLength, String sourceName) {
        if (source.length() < minLength || source.length() > maxLength) {
            throw new IllegalArgumentException(sourceName + " length should be" +
                    " more then " + minLength + " and less then " + maxLength +
                    ". Actual length of \"" + source + "\" is " + source.length());
        }
    }
}
