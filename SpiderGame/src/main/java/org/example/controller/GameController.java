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
        this.game = new Game();
        this.gameView = new GameView(this.gridController.getGridView(), playerControllers.get(0).getPawnBox(),
                playerControllers.get(this.playerControllers.size() - 1).getPawnBox());

        this.gameView.getMenu().setOnValidateButtonPressed(this::handleValidateButtonPressed);
        this.gameView.getMenu().setOnRestartButtonPressed(this::handleRestartButtonPressed);
    }

    public void handleValidateButtonPressed() {
        Optional<PlayerController> playingPlayer = this.playerControllers.stream()
                                                                .filter(PlayerController::isPlaying).findFirst();
        Optional<PlayerController> waitingPlayer = this.playerControllers.stream()
                .filter(playerController -> !playerController.isPlaying()).findFirst();

        playingPlayer.ifPresent(this::validateMove);
        this.gameView.getMenu().getPlayerOrder().setForeground(waitingPlayer.get().getPawnBox().getPawns().get(0)
                .getColor());
        this.playerControllers.forEach(PlayerController::setPlaying);
    }

    public void handleRestartButtonPressed() {
        this.game.restart();
        this.gameView.restart();
    }

    public void movePawn(PlayerController playerController) {
        playerController.registerPawnListeners();
        playerController.findSelectedPawn();
        Optional<PawnController> pawnSelected = playerController.findSelectedPawn();
        pawnSelected.ifPresent(pawnController -> {
            for (Cell cell : this.game.defineLegalMoves(pawnController.getPawn())) {
                this.gridController.getCells().get(this.gridController.getGrid().getCells().indexOf(cell))
                        .setCellStatus(true);
            }

            Optional<int[]> cellId = playerController.findSelectedCellId();
            cellId.ifPresent(cell -> playerController.unregisterPawnListeners());
        });
    }

    public void validateMove(PlayerController playerController) {
        this.gridController.getGridView().getCells().forEach(cellView -> cellView.setStatus(false));
        playerController.findSelectedPawn().ifPresent(pawnController ->
                this.game.updatePosition(pawnController.getPawn(),
                        playerController.findSelectedCellId().get()));
    }

    public void play() {
/*        Pawn lastMove = null;
        while (this.game.isOver(lastMove)) {
            for (PlayerController playerController : this.playerControllers) {
                playerController.setPlaying(true);
                while (playerController.isPlaying()) {
                    this.movePawn(playerController);
                }
                lastMove = playerController.getSelectedPawn().get().getPawn();
            }
        }*/
    }

    public void run() {
        this.gameView.display();
    }

}
