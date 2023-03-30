package com.mjc.school;

import com.mjc.school.controller.Controller;
import com.mjc.school.controller.implementation.ControllerImpl;

public class Main {
    public static void main(String[] args) {
        Controller menuController = new ControllerImpl();
        menuController.start();
    }
}
