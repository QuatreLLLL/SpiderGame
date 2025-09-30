package org.example.controller;

import org.example.model.Cell;
import org.example.model.Game;
import org.example.model.Pawn;
import org.example.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {

    private final Game game;

    private final GameView gameView;

    private final GridController gridController;
    private final List<PlayerController> playerControllers;


    public GameController(GridController gridController, PlayerController playerController1,
                          PlayerController playerController2) {
        this.gridController = gridController;
        this.playerControllers = new ArrayList<>();
        this.playerControllers.add(playerController1);
        this.playerControllers.add(playerController2);
        this.game = new Game();
        this.gameView = new GameView(gridController.getGridView(), playerController1.getPawnBox(),
                playerController2.getPawnBox());


        this.gameView.setOnValidateButtonPressed(this::handleValidateButtonPressed);
    }

    public void handleValidateButtonPressed() {
        Optional<PlayerController> playingPlayer = this.playerControllers.stream()
                .filter(PlayerController::isPlaying).findFirst();
        playingPlayer.ifPresent(playerController -> {
            this.validateMove(playerController);
            playerController.setPlaying(false);
        });
    }

    public void movePawn(PlayerController playerController) {
        playerController.registerPawnListeners();
        playerController.findSelectedPawn();
        Optional<PawnController> pawnSelected = playerController.getSelectedPawn();
        pawnSelected.ifPresent(pawnController -> {
            for (Cell cell : this.game.defineLegalMoves(pawnController.getPawn())) {
                gridController.getCells().get(gridController.getGrid().getCells().indexOf(cell)).setCellStatus(true);
            }

            playerController.findSelectedCell();
            Optional<int[]> cellId = playerController.getSelectedCell();
            cellId.ifPresent(cell -> playerController.unregisterPawnListeners());
        });
    }

    public void validateMove(PlayerController playerController) {
        playerController.getSelectedPawn().ifPresent(pawnController ->
                this.game.updatePosition(pawnController.getPawn(),
                        playerController.getSelectedCell().get()));
    }

    public void play() {
        Pawn lastMove = null;
        while (this.game.isOver(lastMove)) {
            for (PlayerController playerController : this.playerControllers) {
                playerController.setPlaying(true);
                while (playerController.isPlaying()) {
                    this.movePawn(playerController);
                }
                lastMove = playerController.getSelectedPawn().get().getPawn();
            }
        }
    }

    public void display() {
        this.gameView.display();
    }

}
