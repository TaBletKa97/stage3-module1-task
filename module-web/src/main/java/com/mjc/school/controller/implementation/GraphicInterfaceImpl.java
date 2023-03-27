package com.mjc.school.controller.implementation;

import com.mjc.school.controller.GraphicInterface;
import com.mjc.school.service.DataManagingService;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.exceptions.validation.ValidatingDTOException;
import com.mjc.school.service.implementation.DataManagingServiceImpl;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchAuthorException;
import com.mjc.school.service.exceptions.SearchNewsException;
import lombok.Cleanup;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphicInterfaceImpl implements GraphicInterface {
    private final DataManagingService manager;

    public GraphicInterfaceImpl() {
        this.manager = new DataManagingServiceImpl();
    }

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
            try {
                switch (selectedVariant) {
                    case 1 -> getAllNews();
                    case 2 -> getNewsById(sc);
                    case 3 -> createNews(sc);
                    case 4 -> updateNews(sc);
                    case 5 -> removeNews(sc);
                    case 0 -> System.out.println("Goodbye!");
                    default -> System.err.println("Incorrect value.");
                }
            } catch (SearchNewsException e) {
                System.err.println("ERROR01 " + e.getMessage());
            } catch (SearchAuthorException e) {
                System.err.println("ERROR02 " + e.getMessage());
            } catch (ValidatingDTOException e) {
                System.err.println("ERROR03 " + e.getMessage());
            }
        } while (selectedVariant != 0);
    }

    private void getAllNews() {
        manager.readAll().forEach(System.out::println);
    }

    private void getNewsById(Scanner sc) throws SearchNewsException,
            ValidatingDTOException {
        long id = enterId(sc, "Type news id:");
        System.out.println(manager.readNewsById(new NewsDTORequest(id)));

    }

    private void createNews(Scanner sc) throws ValidatingDTOException,
            SearchAuthorException {
        sc.nextLine();
        String title = enterString(sc, "Type a title:");
        String content = enterString(sc, "Type a article:");
        long authorId = enterId(sc, "Type author id");
        NewsDTOResponse news = manager.createNews(new NewsDTORequest(title,
                content, authorId));
        System.out.println("News created:");
        System.out.println(news);
        System.out.println("News created.");
    }

    private void updateNews(Scanner sc) throws SearchNewsException,
            ValidatingDTOException, SearchAuthorException {
        long newsId = enterId(sc, "Type news id:");
        sc.nextLine();
        String title = enterString(sc, "Type a new title:");
        String content = enterString(sc, "Type a new article:");
        long authorId = enterId(sc, "Type author id");
        System.out.println(manager.updateNews(new NewsDTORequest(newsId,
                title, content, authorId)));

    }

    private void removeNews(Scanner sc) throws ValidatingDTOException {
        long newsId = enterId(sc, "Type news id:");
        System.out.println(manager.removeNews(new NewsDTORequest(newsId)));
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
