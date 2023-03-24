package com.mjc.school;

import com.mjc.school.controller.GraphicInterface;
import com.mjc.school.controller.GraphicInterfaceImpl;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.implementation.RepositoryImpl;
import com.mjc.school.service.DataManagerImpl;

public class Main {
    public static void main(String[] args) {
        final String authorSource = "module-repository/src/main/resources/author.txt";
        final String contentSource = "module-repository/src/main/resources/content.txt";
        Repository repository = new RepositoryImpl(authorSource, contentSource);
        DataManagerImpl dataManager = new DataManagerImpl(repository);
        GraphicInterface menu = new GraphicInterfaceImpl(dataManager);
        menu.start();
    }
}
