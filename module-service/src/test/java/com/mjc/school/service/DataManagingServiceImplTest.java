package com.mjc.school.service;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import com.mjc.school.service.exceptions.validation.ContentLengthException;
import com.mjc.school.service.exceptions.validation.TitleLengthException;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;
import com.mjc.school.service.implementation.DataManagingServiceImpl;
import com.mjc.school.service.utils.NewsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataManagingServiceImplTest {
    private static final List<NewsModel> newsList;
    private static final List<AuthorModel> authorsList;

    private DataManagingService manager;

    static {
        newsList = new ArrayList<>(Arrays.asList(
                new NewsModel("title1", "content1", new AuthorModel("author1")),
                new NewsModel("title2", "content2", new AuthorModel("author2")),
                new NewsModel("title3", "content3", new AuthorModel("author3")),
                new NewsModel("title4", "content4", new AuthorModel("author4")),
                new NewsModel("title5", "content5", new AuthorModel("author5")),
                new NewsModel("title6", "content6", new AuthorModel("author6")),
                new NewsModel("title7", "content7", new AuthorModel("author7")),
                new NewsModel("title8", "content8", new AuthorModel("author8")),
                new NewsModel("title9", "content9", new AuthorModel("author9")),
                new NewsModel("title10", "content10", new AuthorModel("author10"))));

        authorsList = newsList.stream().map(NewsModel::getAuthor).collect(Collectors.toList());
    }

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        manager = new DataManagingServiceImpl();

        DataSource mockDataSource = mock(DataSource.class);

        //mocking DataSource in newsDAO
        Field newsDaoField = manager.getClass().getDeclaredField("newsRepository");
        newsDaoField.setAccessible(true);
        NewsRepositoryImpl newsDao = (NewsRepositoryImpl) newsDaoField.get(manager);

        Field dataSourceField = NewsRepositoryImpl.class.getDeclaredField("dataSource");
        dataSourceField.setAccessible(true);
        dataSourceField.set(newsDao, mockDataSource);

        //mocking DataSource in authorDAO
        Field authorDaoField = manager.getClass().getDeclaredField("authorRepository");
        authorDaoField.setAccessible(true);
        AuthorRepositoryImpl authorDao = (AuthorRepositoryImpl) authorDaoField.get(manager);

        Field authorDataSourceField = authorDao.getClass().getDeclaredField("dataSource");
        authorDataSourceField.setAccessible(true);
        authorDataSourceField.set(authorDao, mockDataSource);

        when(mockDataSource.getNewsModels()).thenReturn(newsList);
        when(mockDataSource.getAuthorModels()).thenReturn(authorsList);
    }

    @Test
    void readAllTest() {
        final int expectedListSize = 10;
        final List<NewsDTOResponse> expectedList = newsList.stream()
                .map(NewsMapper.INSTANCE::mapNews)
                .collect(Collectors.toList());
        List<NewsDTOResponse> news = manager.readAll();
        assertEquals(expectedListSize, news.size());
        assertEquals(expectedList, news);
    }

    @Test
    void readNewsByIdSuccessTest() throws SearchNewsException {
        final NewsDTOResponse expectedNewsWithId1 = NewsMapper.INSTANCE.mapNews(newsList.get(0));
        final NewsDTOResponse expectedNewsWithId2 = NewsMapper.INSTANCE.mapNews(newsList.get(1));
        NewsDTOResponse newsById1 = manager.readByIdNews(new NewsDTORequest(1L));
        NewsDTOResponse newsById2 = manager.readByIdNews(new NewsDTORequest(2L));
        assertEquals(expectedNewsWithId1, newsById1);
        assertEquals(expectedNewsWithId2, newsById2);
    }

    @Test
    void readNewsByIdWrongIdTest() {
        assertThrows(SearchNewsException.class,
                () -> manager.readByIdNews(new NewsDTORequest(65L)));
    }

    @Test
    void createNewsSuccessTest() throws SearchAuthorException, ValidatingDTOException {
        final String title = "title";
        final String article = "article";
        final long authorId = 1;

        NewsDTOResponse news = manager.createNews(new NewsDTORequest(title,
                article, authorId));
        NewsDTOResponse expNews = NewsMapper.INSTANCE.mapNews(newsList.get(newsList.size() - 1));
        assertEquals(expNews, news);
    }

    @Test
    void createNewsWrongTitleLengthTest() {
        assertThrows(TitleLengthException.class,
                () -> manager.createNews(new NewsDTORequest("", "article", 1L)));
    }

    @Test
    void createNewsWrongContentLengthTest() {
        assertThrows(ContentLengthException.class,
                () -> manager.createNews(new NewsDTORequest("title",
                        "art", 1L)));
    }

    @Test
    void createNewsWrongAuthorIdTest() {
        assertThrows(SearchAuthorException.class,
                () -> manager.createNews(new NewsDTORequest("title",
                        "article", 110L)));
    }

    @Test
    void updateNewsSuccessTest() throws SearchNewsException, SearchAuthorException, ValidatingDTOException {
        final String title = "title";
        final String article = "article";
        final long authorId = 1;
        final long id = 1;

        NewsDTOResponse news = manager.updateNews(new NewsDTORequest(id, title, article, authorId));
        NewsDTOResponse expNews = NewsMapper.INSTANCE.mapNews(newsList.get(0));
        assertEquals(expNews, news);
    }

    @Test
    void updateNewsWrongTitleLengthTest() {
        assertThrows(TitleLengthException.class,
                () -> manager.updateNews(new NewsDTORequest(1L ,"", "article", 1L)));
    }

    @Test
    void updateNewsWrongContentLengthTest() {
        assertThrows(ContentLengthException.class,
                () -> manager.updateNews(new NewsDTORequest(1L ,"title", "art", 1L)));
    }

    @Test
    void updateNewsWrongAuthorIdTest() {
        assertThrows(SearchAuthorException.class,
                () -> manager.updateNews(new NewsDTORequest(1L ,"title", "article", 101L)));
    }

    @Test
    void updateNewsWrongNewsIdTest() {
        assertThrows(SearchNewsException.class,
                () -> manager.updateNews(new NewsDTORequest(101L ,"title", "article", 1L)));
    }


    @Test
    void deleteNewsTest() throws ValidatingDTOException, SearchAuthorException {
        NewsDTOResponse news = manager.createNews(new NewsDTORequest("for delete", "roe delete", 1L));
        boolean success = manager.deleteNews(new NewsDTORequest(news.getId()));
        boolean fail = manager.deleteNews(new NewsDTORequest(100L));
        assertTrue(success);
        assertFalse(fail);
    }
}