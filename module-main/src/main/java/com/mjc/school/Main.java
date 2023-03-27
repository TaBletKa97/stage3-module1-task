package com.mjc.school;

import com.mjc.school.controller.Controller;
import com.mjc.school.controller.implementation.ControllerImpl;

public class Main {
    public static void main(String[] args) {
        Controller menu = new ControllerImpl();
        menu.start();
    }
}
