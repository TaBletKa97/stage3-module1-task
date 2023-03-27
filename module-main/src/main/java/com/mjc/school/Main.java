package com.mjc.school;

import com.mjc.school.controller.GraphicInterface;
import com.mjc.school.controller.implementation.GraphicInterfaceImpl;

public class Main {
    public static void main(String[] args) {
        GraphicInterface menu = new GraphicInterfaceImpl();
        menu.start();
    }
}
