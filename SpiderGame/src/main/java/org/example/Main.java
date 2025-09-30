package org.example;

import org.example.controller.GameController;
import org.example.controller.GridController;
import org.example.controller.PlayerController;

public class Main {

    public static void main(String[] args) {
        GridController gridController = new GridController();
        PlayerController playerController1 = new PlayerController(1);
        PlayerController playerController2 = new PlayerController(2);

        GameController gameController = new GameController(gridController, playerController1, playerController2);
        gameController.display();

    }
}