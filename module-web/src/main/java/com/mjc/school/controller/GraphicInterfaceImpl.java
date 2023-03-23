package com.mjc.school.controller;

import com.mjc.school.service.DataManager;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import lombok.AllArgsConstructor;
import lombok.Cleanup;

import java.util.NoSuchElementException;
import java.util.Scanner;

@AllArgsConstructor
public class GraphicInterfaceImpl implements GraphicInterface {
    private DataManager manager;

    @Override
    public void start() {
        final String menu = """
                Choose an option:
                1. Get all news.
                2. Get news by id.
                3. Create news.
                4. Update news by id.
                5. Remove news by id.
                0. Exit""";

        int selectedVariant;
        @Cleanup Scanner sc = new Scanner(System.in);
        do {
            System.out.println(menu);
            try {
                selectedVariant = sc.nextInt();
            } catch (NoSuchElementException e) {
                sc.nextLine();
                selectedVariant = -1;
            }
            switch (selectedVariant) {
                case 1 -> getAllNews();
                case 2 -> getNewsById(sc);
                case 3 -> createNews(sc);
                case 4 -> updateNews(sc);
                case 5 -> removeNews(sc);
                case 0 -> System.out.println("Goodbye!");
                default -> System.err.println("Incorrect value.");
            }
        } while (selectedVariant != 6);
    }

    private void getAllNews() {
        manager.getNews().forEach(System.out::println);
    }

    private void getNewsById(Scanner sc) {
        long id = enterId(sc, "Type news id:");
        try {
            System.out.println(manager.getNewsById(id));
        } catch (SearchNewsException e) {
            System.err.println("ERROR01 " + e.getMessage());
        }
    }

    private void createNews(Scanner sc) {
        sc.nextLine();
        String title = enterString(sc, "Type a title:");
        String content = enterString(sc, "Type a article:");
        long authorId = enterId(sc, "Type author id");
        try {
            NewsDTO news = manager.createNews(title, content, authorId);
            System.out.println("News created:");
            System.out.println(news);
        } catch (SearchAuthorException e) {
            System.err.println("ERROR02 " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR03 " + e.getMessage());
        }
        System.out.println("create");
    }

    private void updateNews(Scanner sc) {
        long newsId = enterId(sc, "Type news id");
        sc.nextLine();
        String title = enterString(sc, "Type a new title:");
        String content = enterString(sc, "Type a new article:");
        long authorId = enterId(sc, "Type author id");
        try {
            System.out.println(manager.updateNews(newsId, title, content,
                    authorId));
        } catch (SearchNewsException e) {
            System.err.println("ERROR01 " + e.getMessage());
        } catch (SearchAuthorException e) {
            System.err.println("ERROR02 " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR03 " + e.getMessage());
        }
    }

    private void removeNews(Scanner sc) {
        long newsId = enterId(sc, "Type news id");
        System.out.println(manager.removeNews(newsId));
    }

    private long enterId(Scanner sc, String message) {
        boolean isLong = false;
        long selectedVariant;
        do {
            try {
                System.out.println(message);
                selectedVariant = sc.nextLong();
                isLong = true;
            } catch (NoSuchElementException e) {
                System.err.println("It isn't long value.");
                sc.nextLine();
                selectedVariant = 0;
            }
        } while (!isLong);
        return selectedVariant;
    }

    private String enterString(Scanner sc, String message) {
        boolean isCorrect = false;
        String string = "";
        do {
            try {
                System.out.println(message);
                string = sc.nextLine();
                isCorrect = true;
            } catch (NoSuchElementException e) {
                System.err.println("Type not empty string:");
            }
        } while (!isCorrect);
        return string;
    }
}
