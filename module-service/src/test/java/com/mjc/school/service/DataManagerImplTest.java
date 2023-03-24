package com.mjc.school.service;

import com.mjc.school.repository.implementation.RepositoryImpl;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerImplTest {
    static DataManager manager;

    @BeforeAll
    public static void setUp() {
        final String abs = new File("").getAbsolutePath();
        final String authors = abs + "/../module-service/src/test/resources/author.txt";
        final String news = abs + "/../module-service/src/test/resources/content.txt";

        manager = new DataManagerImpl(new RepositoryImpl(authors, news));
    }

    @Test
    void getNews() {
        final int expectedListSize = 20;
        System.out.println();
        List<NewsDTO> news = manager.readAll();
        assertEquals(expectedListSize, news.size());
    }

    @Test
    void getNewsById() throws SearchNewsException {
        NewsDTO newsByIdOne = manager.readNewsById(1);
        NewsDTO newsByIdTwo = manager.readNewsById(2);
        assertEquals(1, newsByIdOne.getNewsId());
        assertEquals(2, newsByIdTwo.getNewsId());
    }

    @Test
    void createNews() throws SearchAuthorException {
        final String title = "title";
        final String article = "article";
        final long authorId = 1;

        NewsDTO news = manager.createNews(title, article, authorId);
        assertEquals(title, news.getNewsTitle());
        assertEquals(article, news.getNewsContent());
        assertEquals(authorId, news.getAuthorId());
    }

    @Test
    void updateNews() throws SearchNewsException, SearchAuthorException {
        final String title = "title";
        final String article = "article";
        final long authorId = 1;
        final long id = 1;

        NewsDTO news = manager.updateNews(id, title, article, authorId);
        assertEquals(title, news.getNewsTitle());
        assertEquals(article, news.getNewsContent());
        assertEquals(authorId, news.getAuthorId());
    }

    @Test
    void removeNews() {
        manager.removeNews(1);
        assertThrows(SearchNewsException.class, () -> manager.readNewsById(1));
    }
}