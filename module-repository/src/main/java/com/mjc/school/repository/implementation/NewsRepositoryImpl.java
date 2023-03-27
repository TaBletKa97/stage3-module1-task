package com.mjc.school.repository.implementation;

import com.mjc.school.repository.DataGenerator;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.News;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class NewsRepositoryImpl implements Repository<News, Long> {
    private final List<News> newsRepo;

    public NewsRepositoryImpl() {
        DataGenerator generator = DataGenerator.getInstance();
        newsRepo = generator.getNews();
    }

    @Override
    public List<News> readAll() {
        return newsRepo;
    }

    @Override
    public News readById(Long id) {
        return newsRepo.stream()
                .filter(e -> Objects.equals(e.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public News create(News entity) {
        newsRepo.add(entity);
        return entity;
    }

    @Override
    public News update(News entity) {
        News news = readById(entity.getId());
        news.setTitle(entity.getTitle());
        news.setContent(entity.getContent());
        news.setAuthor(entity.getAuthor());
        news.setLastUpdateDate(LocalDateTime.now()
                .truncatedTo(ChronoUnit.SECONDS));
        return news;
    }

    @Override
    public boolean deleteById(Long id) {
        return newsRepo.remove(readById(id));
    }
}
