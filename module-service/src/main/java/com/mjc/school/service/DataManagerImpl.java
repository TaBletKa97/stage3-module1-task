package com.mjc.school.service;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.beens.Author;
import com.mjc.school.repository.beens.News;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DataManagerImpl implements DataManager {
    private final Repository repository;

    public DataManagerImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<NewsDTO> readAll() {
        List<NewsDTO> resultList = new ArrayList<>();

        repository.getData().forEach(news -> resultList.add(mapNews(news)));
        return resultList;
    }

    @Override
    public NewsDTO readNewsById(long id) throws SearchNewsException {
        return mapNews(searchNews(id));
    }

    @Override
    public NewsDTO createNews(String title, String content, long authorId)
            throws SearchAuthorException {
        List<News> data = repository.getData();
        Author author = searchAuthor(authorId);
        data.add(new News(title, content, author));
        return mapNews(data.get(data.size() - 1));
    }

    @Override
    public NewsDTO updateNews(long id, String newTitle, String newArticle,
                              long newAuthorId) throws SearchNewsException,
            SearchAuthorException {
        News news = searchNews(id);
        Author author = searchAuthor(newAuthorId);
        news.setTitle(newTitle);
        news.setContent(newArticle);
        news.setAuthor(author);
        return mapNews(news);
    }

    @Override
    public boolean removeNews(long id) {
        try {
            News news = searchNews(id);
            repository.getData().remove(news);
            return true;
        } catch (SearchNewsException e) {
            return false;
        }
    }

    private NewsDTO mapNews(News news) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(news, NewsDTO.class);
    }

    private Author searchAuthor(long id) throws SearchAuthorException {
        return repository.getAuthors().stream()
                .filter(auth -> auth.getId() == id)
                .findFirst()
                .orElseThrow(() -> new SearchAuthorException("There isn't" +
                        " author with id " + id));
    }

    private News searchNews(long id) throws SearchNewsException {
        return repository.getData().stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElseThrow(() -> new SearchNewsException("There isn't" +
                        " news with id " + id));
    }
}
