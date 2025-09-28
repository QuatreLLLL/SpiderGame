package org.example;

import org.example.controller.GameController;
import org.example.controller.GridController;
import org.example.controller.PlayerController;
import org.example.model.Grid;
import org.example.model.Player;
import org.example.view.GridView;
import org.example.view.PawnBox;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {


        GridController gridController = new GridController();
        PlayerController playerController1 = new PlayerController(1);
        PlayerController playerController2 = new PlayerController(2);

        GameController gameController = new GameController(gridController, playerController1, playerController2);
        gameController.display();

    }
}