package org.example.controller;

import org.example.model.Cell;
import org.example.model.Game;
import org.example.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {

    private final Game game;

    private final GameView gameView;

    private final GridController gridController;
    private final List<PlayerController> playerControllers;

    public GameController() {
        this.gridController = new GridController();
        this.playerControllers = new ArrayList<>();
        this.playerControllers.add(new PlayerController(0, true));
        this.playerControllers.add(new PlayerController(1, false));
        this.game = new Game(this.gridController.getGrid());
        this.playerControllers.stream()
                .flatMap(playerController -> playerController.getPawnControllers().stream())
                .forEach(pawnController -> {
                    pawnController.setLegalMovesUpdater(this::updateLegalMoves);
                    pawnController.setPawnPlacementHandler(this::handlePawnPlacement);
                });
        this.gameView = new GameView(this.gridController.getGridView(), this.playerControllers.get(0).getPawnBox(),
                this.playerControllers.get(this.playerControllers.size() - 1).getPawnBox());

        this.gameView.getMenu().setOnValidateButtonPressed(this::handleValidateButtonPressed);
        this.gameView.getMenu().setOnRestartButtonPressed(this::handleRestartButtonPressed);
        this.gameView.getMenu().setOnUndoButtonPressed(this::handleUndoButtonPressed);
    }

    public void handleValidateButtonPressed() {
        Optional<PlayerController> playingPlayer = this.playerControllers.stream()
                                                                .filter(PlayerController::isPlaying).findFirst();
        this.playerControllers.stream()
                .filter(playerController -> !playerController.isPlaying()).findFirst()
                .ifPresent(playerController ->
                        this.gameView.updateLabelColor(this.gameView.getMenu().getPlayerOrder(),
                                playerController.getPawnBox()));

        this.playerControllers.forEach(PlayerController::updatePlayingStatus);
        playingPlayer.ifPresent(this::validateMove);
    }

    public void handleRestartButtonPressed() {
        this.game.restart();
        this.playerControllers.forEach(PlayerController::restart);
        this.gameView.showComponent(this.gameView.getMenu().getRestartButton(), false);
    }

    public void handleUndoButtonPressed() {
        Optional<PlayerController> playingPlayer = this.playerControllers.stream().filter(PlayerController::isPlaying)
                .findFirst();
        playingPlayer.ifPresent(playerController -> {
            playerController.registerPawnListeners();
            playerController.findSelectedPawn().ifPresent(pawnController ->
                    pawnController.getPawnView().undoMove());
        });
        this.gameView.getMenu().getValidateButton().setEnabled(false);
        this.gameView.getMenu().getUndoButton().setEnabled(false);
    }

    public void updateLegalMoves(PawnController pawnController) {
        this.gridController.getGridView().resetCellStatus();
        if (pawnController == null) {
            return;
        }
        for (Cell cell : this.game.getLegalMoves(pawnController.getPawn())) {
            this.gridController.getCells().get(this.gridController.getGrid().getCells().indexOf(cell))
                    .setCellStatus(true);
        }
    }

    public void handlePawnPlacement(PawnController pawnController) {
        this.playerControllers.stream().filter(PlayerController::isPlaying).findFirst()
                .ifPresent(PlayerController::unregisterPawnListeners);
        this.gridController.getGridView().resetCellStatus();
        this.gameView.getMenu().getUndoButton().setEnabled(true);
        this.gameView.getMenu().getValidateButton().setEnabled(true);
    }

    public void validateMove(PlayerController playerController) {
        this.gridController.getGridView().resetCellStatus();
        this.gameView.getMenu().getValidateButton().setEnabled(false);
        this.gameView.getMenu().getUndoButton().setEnabled(false);
        playerController.findSelectedPawn().ifPresent(pawnController -> {
            this.game.updatePosition(pawnController.getPawn(), pawnController.getPawnView().getCellId());
            this.game.addPawn(pawnController.getPawn());
            pawnController.getPawnView().updateParent();
            if (this.game.isOver(pawnController.getPawn())) {
                this.playerControllers.forEach(PlayerController::unregisterPawnListeners);
                this.gameView.updateLabelColor(this.gameView.getLabel(), playerController.getPawnBox());
                this.gameView.showComponent(this.gameView.getDialog(), true);
                this.gameView.showComponent(this.gameView.getMenu().getRestartButton(), true);
            }
        });
    }

    public void run() {
        this.gameView.display();
    }

}
