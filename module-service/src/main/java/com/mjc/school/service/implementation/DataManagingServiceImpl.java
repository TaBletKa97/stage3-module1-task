package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
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
        News news = newsDAO.readById(NewsMapper.INSTANCE.unmapNewsReq(req).getId());
        if (news == null) {
            throw new SearchNewsException("News with such id was not found.");
        }
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse createNews(NewsDTORequest req) throws
            SearchAuthorException, ValidatingDTOException {
        validateNews(req);
        Author author = authorDAO.readById(req.getAuthorId());
        if (author == null) {
            throw new SearchAuthorException("Author with such id was not found.");
        }
        News news = newsDAO.create(new News(req.getTitle(), req.getContent(), author));
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse updateNews(NewsDTORequest req) throws
            SearchNewsException, ValidatingDTOException {
        validateNews(req);
        News news = NewsMapper.INSTANCE.unmapNewsResp(readNewsById(req));
        News update = newsDAO.update(news);
        return NewsMapper.INSTANCE.mapNews(update);
    }

    @Override
    public boolean removeNews(NewsDTORequest req) {
        return newsDAO.deleteById(req.getId());
    }
}
