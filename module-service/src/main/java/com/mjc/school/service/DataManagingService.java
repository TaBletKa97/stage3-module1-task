package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;

import java.util.List;

public interface DataManagingService {
    List<NewsDTOResponse> readAll();
    NewsDTOResponse readNewsById(NewsDTORequest req) throws SearchNewsException, ValidatingDTOException;
    NewsDTOResponse createNews(NewsDTORequest req) throws SearchAuthorException, ValidatingDTOException;
    NewsDTOResponse updateNews(NewsDTORequest req) throws SearchNewsException, SearchAuthorException, ValidatingDTOException;
    boolean removeNews(NewsDTORequest req) throws ValidatingDTOException;
}
