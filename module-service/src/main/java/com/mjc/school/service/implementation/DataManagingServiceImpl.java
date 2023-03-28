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

import java.util.List;
import java.util.stream.Collectors;

import static com.mjc.school.service.utils.NewsRequestValidator.validateNews;

public class DataManagingServiceImpl implements DataManagingService {
    private final NewsRepositoryImpl newsDAO = new NewsRepositoryImpl();
    private final AuthorRepositoryImpl authorDAO = new AuthorRepositoryImpl();

    @Override
    public List<NewsDTOResponse> readAll() {
        return newsDAO.readAll().stream()
                .map(NewsMapper.INSTANCE::mapNews)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTOResponse readNewsById(NewsDTORequest req) throws
            SearchNewsException, ValidatingDTOException {
        validateNews(req);
        NewsModel newsModel = newsDAO.readById(NewsMapper.INSTANCE.unmapNewsReq(req).getId());
        if (newsModel == null) {
            throw new SearchNewsException("News with such id was not found.");
        }
        return NewsMapper.INSTANCE.mapNews(newsModel);
    }

    @Override
    public NewsDTOResponse createNews(NewsDTORequest req) throws
            SearchAuthorException, ValidatingDTOException {
        validateNews(req);
        AuthorModel authorModel = authorDAO.readById(req.getAuthorId());
        if (authorModel == null) {
            throw new SearchAuthorException("Author with such id was not found.");
        }
        NewsModel newsModel = newsDAO.create(new NewsModel(req.getTitle(), req.getContent(), authorModel));
        return NewsMapper.INSTANCE.mapNews(newsModel);
    }

    @Override
    public NewsDTOResponse updateNews(NewsDTORequest req) throws ValidatingDTOException {
        validateNews(req);
        NewsModel newsModel = NewsMapper.INSTANCE.unmapNewsReq(req);
        AuthorModel authorModel = authorDAO.readById(req.getAuthorId());
        newsModel.setAuthor(authorModel);
        NewsModel update = newsDAO.update(newsModel);
        return NewsMapper.INSTANCE.mapNews(update);
    }

    @Override
    public boolean removeNews(NewsDTORequest req) {
        return newsDAO.deleteById(req.getId());
    }
}
