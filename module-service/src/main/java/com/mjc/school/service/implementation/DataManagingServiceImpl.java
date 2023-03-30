package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.DataManagingService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;
import com.mjc.school.service.utils.NewsMapper;
import com.mjc.school.service.utils.NewsRequestValidator;

import java.util.List;
import java.util.stream.Collectors;

public class DataManagingServiceImpl implements DataManagingService {
    private NewsRepositoryImpl newsRepository = new NewsRepositoryImpl();
    private AuthorRepositoryImpl authorDAO = new AuthorRepositoryImpl();
    private NewsRequestValidator requestValidator = new NewsRequestValidator();

    @Override
    public List<NewsDTOResponse> readAll() {
        return newsRepository.readAll().stream()
                .map(NewsMapper.INSTANCE::mapNews)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTOResponse readByIdNews(Long id) throws
            SearchNewsException {
        NewsModel newsModel = newsRepository.readById(id);
        if (newsModel == null) {
            throw new SearchNewsException("News with such id was not found.");
        }
        return NewsMapper.INSTANCE.mapNews(newsModel);
    }

    @Override
    public NewsDTOResponse createNews(NewsDTORequest req) throws
            SearchAuthorException, ValidatingDTOException {
        requestValidator.validateNews(req);
        AuthorModel authorModel = authorDAO.readById(req.getAuthorId());
        if (authorModel == null) {
            throw new SearchAuthorException("Author with such id was not found.");
        }
        NewsModel newsModel = newsRepository.create(new NewsModel(req.getTitle(), req.getContent(), authorModel));
        return NewsMapper.INSTANCE.mapNews(newsModel);
    }

    @Override
    public NewsDTOResponse updateNews(NewsDTORequest req) throws ValidatingDTOException, SearchAuthorException, SearchNewsException {
        requestValidator.validateNews(req);
        NewsModel newsModel = NewsMapper.INSTANCE.unmapNewsReq(req);
        readByIdNews(req.getId());
        AuthorModel authorModel = authorDAO.readById(req.getAuthorId());
        if (authorModel == null) {
            throw new SearchAuthorException("Author with such id was not found.");
        }
        newsModel.setAuthor(authorModel);
        NewsModel update = newsRepository.update(newsModel);
        return NewsMapper.INSTANCE.mapNews(update);
    }

    @Override
    public Boolean deleteNews(Long id) {
        return newsRepository.deleteById(id);
    }
}
