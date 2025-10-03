package org.example.controller;

import org.example.model.Player;
import org.example.view.PlayerView;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController extends MouseAdapter {

    private final Player player;

    private final PlayerView pawnBox;

    private final List<PawnController> pawnControllers;

    private boolean isPlaying = false;

    public PlayerController(int id, boolean isPlaying) {
        this.player = new Player(id);
        this.pawnBox = new PlayerView(id);
        this.pawnControllers = new ArrayList<>();
        this.createPawnControllers();

        if (isPlaying) {
            this.updatePlayingStatus();
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerView getPawnBox() {
        return this.pawnBox;
    }

    public List<PawnController> getPawnControllers() {
        return this.pawnControllers;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void updatePlayingStatus() {
        this.isPlaying = !this.isPlaying;
        if (this.isPlaying) {
            this.registerPawnListeners();
        }
    }

    private void createPawnControllers() {
        for (int i = 0; i < 3; i++) {
            this.pawnControllers.add(new PawnController(this.player.getPawns().get(i), this.pawnBox.getPawns().get(i)));
        }
    }

    public void registerPawnListeners() {
        for (PawnController pawnController : this.pawnControllers) {
            if (pawnController.getPawn().isMovable(this.player)) {
                pawnController.getPawnView().enableListeners();
            }
        }
    }

    public void unregisterPawnListeners() {
        this.pawnControllers.forEach(pawnController -> pawnController.getPawnView().disableListeners());
    }

    public Optional<PawnController> findSelectedPawn() {
        return this.pawnControllers.stream()
                .filter(pawnController -> pawnController.getPawnView().isSelected())
                .findFirst();
    }
}
