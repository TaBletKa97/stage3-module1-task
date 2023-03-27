package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Getter
public class DataGenerator {
    private static final DataGenerator instance = new DataGenerator();

    private final List<News> news = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();

    private DataGenerator() {
        init();
    }

    public static DataGenerator getInstance() {
        return instance;
    }

    private void init() {
        final String authorSource = "C:\\Users\\TaBletKa97\\IdeaProjects\\stage3-module1-task\\module-repository\\src\\main\\resources\\author.txt";
        final String contentSource = "C:\\Users\\TaBletKa97\\IdeaProjects\\stage3-module1-task\\module-repository\\src\\main\\resources\\content.txt";

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
            news.add(new News(titles.get(random.nextInt(titles.size())),
                    articles.get(random.nextInt(articles.size())),
                    authors.get(random.nextInt(authors.size()))));
        }
    }
}
