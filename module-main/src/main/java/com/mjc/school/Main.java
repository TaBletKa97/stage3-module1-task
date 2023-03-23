package com.mjc.school;

import com.mjc.school.controller.GraphicInterface;
import com.mjc.school.controller.GraphicInterfaceImpl;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.DataSourceImpl;
import com.mjc.school.service.DataManagerImpl;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSourceImpl();
        DataManagerImpl dataManager = new DataManagerImpl(dataSource);
        GraphicInterface menu = new GraphicInterfaceImpl(dataManager);
        menu.start();
    }
}
