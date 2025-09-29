package org.example.controller;

import org.example.model.Player;
import org.example.view.PawnBox;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController extends MouseAdapter {

    private final Player player;

    private final PawnBox pawnBox;

    private List<PawnController> pawns;

    private boolean isPlaying;

    private Optional<PawnController> selectedPawn;

    private Optional<int[]> selectedCell;

    public PlayerController(int id) {
        this.player = new Player(id);
        this.pawnBox = new PawnBox(id);
        this.pawns = new ArrayList<>();
        this.isPlaying = false;

        this.createPawnControllers();
        this.registerPawnListeners();
    }

    public Player getPlayer() {
        return this.player;
    }

    public PawnBox getPawnBox() {
        return this.pawnBox;
    }

    public Optional<PawnController> getSelectedPawn() {
        return this.selectedPawn;
    }

    public Optional<int[]> getSelectedCell() {
        return this.selectedCell;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    private void createPawnControllers() {
        for (int i = 0; i < 3; i++) {
            this.pawns.add(new PawnController(this.player.getPawns().get(i), this.pawnBox.getPawns().get(i)));
        }
    }

    public void registerPawnListeners() {
        this.pawns.forEach(pawnController -> pawnController.getPawnView().enableListeners());
    }

    public void unregisterPawnListeners() {
        this.pawns.forEach(pawnController -> pawnController.getPawnView().disableListeners());
    }

    public void findSelectedPawn() {
       this.selectedPawn = this.pawns.stream()
                .filter(pawnController -> pawnController.getPawnView().isSelected()).findFirst();
    }

    public void findSelectedCell() {
        this.selectedCell = this.selectedPawn.get().getPawnView().getCellId();
    }

}
