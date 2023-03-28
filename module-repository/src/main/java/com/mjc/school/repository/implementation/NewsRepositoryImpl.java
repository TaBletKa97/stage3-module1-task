package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class NewsRepositoryImpl implements Repository<NewsModel, Long> {
    private final DataSource dataSource;

    public NewsRepositoryImpl() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModels();
    }

    @Override
    public NewsModel readById(Long id) {
        return dataSource.getNewsModels().stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public NewsModel create(NewsModel entity) {
        dataSource.getNewsModels().add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId());
        newsModel.setTitle(entity.getTitle());
        newsModel.setContent(entity.getContent());
        newsModel.setAuthor(entity.getAuthor());
        newsModel.setLastUpdateDate(LocalDateTime.now()
                .truncatedTo(ChronoUnit.SECONDS));
        return newsModel;
    }

    @Override
    public Boolean deleteById(Long id) {
        return dataSource.getNewsModels().remove(readById(id));
    }
}
