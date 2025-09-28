package org.example.controller;

import org.example.model.Cell;
import org.example.model.Game;
import org.example.model.Pawn;
import org.example.view.GameView;
import org.example.view.GridView;
import org.example.view.PawnBox;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private Game game;

    private GameView gameView;

    private GridController gridController;
    private List<PlayerController> playerControllers;


    public GameController(GridController gridController, PlayerController playerController1,
                          PlayerController playerController2) {
        this.gridController = gridController;
        this.playerControllers = new ArrayList<>();
        this.playerControllers.add(playerController1);
        this.playerControllers.add(playerController2);
        //this.game = new Game();
        this.gameView = new GameView(gridController.getGridView(), playerController1.getPawnBox(),
                playerController2.getPawnBox());
    }

    public void display() {
        gameView.display();
    }

}
