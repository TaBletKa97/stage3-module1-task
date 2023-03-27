package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import com.mjc.school.service.implementation.DataManagingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataManagingServiceImplTest {
    static DataManagingService manager;

    @BeforeAll
    public static void setUp() {
        manager = new DataManagingServiceImpl();
    }

    //todo write Tests
//    @Test
//    void getNews() {
//        final int expectedListSize = 20;
//        System.out.println();
//        List<NewsDTOResponse> news = manager.readAll();
//        assertEquals(expectedListSize, news.size());
//    }
//
//    @Test
//    void getNewsById() throws SearchNewsException {
//        NewsDTOResponse newsByIdOne = manager.readNewsById(1);
//        NewsDTOResponse newsByIdTwo = manager.readNewsById(2);
//        assertEquals(1, newsByIdOne.getNewsId());
//        assertEquals(2, newsByIdTwo.getNewsId());
//    }
//
//    @Test
//    void createNews() throws SearchAuthorException {
//        final String title = "title";
//        final String article = "article";
//        final long authorId = 1;
//
//        NewsDTOResponse news = manager.createNews(title, article, authorId);
//        assertEquals(title, news.getNewsTitle());
//        assertEquals(article, news.getNewsContent());
//        assertEquals(authorId, news.getAuthorId());
//    }
//
//    @Test
//    void updateNews() throws SearchNewsException, SearchAuthorException {
//        final String title = "title";
//        final String article = "article";
//        final long authorId = 1;
//        final long id = 1;
//
//        NewsDTOResponse news = manager.updateNews(id, title, article, authorId);
//        assertEquals(title, news.getNewsTitle());
//        assertEquals(article, news.getNewsContent());
//        assertEquals(authorId, news.getAuthorId());
//    }
//
//    @Test
//    void removeNews() {
//        manager.removeNews(1);
//        assertThrows(SearchNewsException.class, () -> manager.readNewsById(1));
//    }
}