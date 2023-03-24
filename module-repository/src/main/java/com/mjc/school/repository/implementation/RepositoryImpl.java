package com.mjc.school.repository.implementation;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.beens.Author;
import com.mjc.school.repository.beens.News;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RepositoryImpl implements Repository {
    private final List<News> repository;
    private final List<Author> authors = new ArrayList<>();

    final String authorSource;
    final String contentSource;

    public RepositoryImpl(String authorSource, String contentSource) {
        this.authorSource = authorSource;
        this.contentSource = contentSource;
        repository = init();
    }

    @Override
    public List<News> getData() {
        return repository;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    private List<News> init() {
        List<News> resultList = new ArrayList<>();

        List<String> titles = new ArrayList<>();
        List<String> articles = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(authorSource))) {
            while (sc.hasNext()) {
                authors.add(new Author(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can not find file with name " + authorSource);
            e.printStackTrace();
        }

        try (Scanner sc = new Scanner(new File(contentSource))) {
            final int titleIndex = 0;
            final int contentIndex = 1;
            while (sc.hasNext()) {
                String[] content = sc.nextLine().split(" / ");
                titles.add(content[titleIndex]);
                articles.add(content[contentIndex]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can not find file with name " + authorSource);
            e.printStackTrace();
        }

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            resultList.add(new News(titles.get(random.nextInt(titles.size())),
                    articles.get(random.nextInt(articles.size())),
                    authors.get(random.nextInt(authors.size()))));
        }
        return resultList;
    }
}
