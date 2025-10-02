package org.example;

import org.example.controller.GameController;
import org.example.controller.GridController;
import org.example.controller.PlayerController;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}