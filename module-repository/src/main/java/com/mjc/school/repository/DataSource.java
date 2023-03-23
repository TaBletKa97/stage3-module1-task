package com.mjc.school.repository;

import com.mjc.school.repository.beens.Author;
import com.mjc.school.repository.beens.News;

import java.util.List;

public interface DataSource {
    List<News> getData();
    List<Author> getAuthors();
}
