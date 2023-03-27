package com.mjc.school.service.utils;

import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.exceptions.validation.ContentLengthException;
import com.mjc.school.service.exceptions.validation.TitleLengthException;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;

public class NewsRequestValidator {
    public static void validateNews(NewsDTORequest req) throws ValidatingDTOException {
        String title = req.getTitle();
        String content = req.getContent();
        if (title != null) {
            if (title.length() < 5 || title.length() > 30) {
                throw new TitleLengthException("Title field should have length of value from 5 to 30.");
            }
        }
        if (content != null) {
            if (content.length() < 5 || content.length() > 255) {
                throw new ContentLengthException("Content field should have length of value from 5 to 255.");
            }
        }
    }
}
