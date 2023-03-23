package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;

import java.util.List;

public interface DataManager {
    List<NewsDTO> getNews();
    NewsDTO getNewsById(long id) throws SearchNewsException;
    NewsDTO createNews(String title, String content, long authorId) throws SearchAuthorException;
    NewsDTO updateNews(long id, String newTitle, String newArticle, long newAuthorId) throws SearchNewsException, SearchAuthorException;
    boolean removeNews(long id);
}
