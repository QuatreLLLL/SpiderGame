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

    private final List<PawnController> pawns;

    private boolean isPlaying;

    private PawnController selectedPawn;

    private int[] selectedCell;

    public PlayerController(int id, boolean isPlaying) {
        this.player = new Player(id);
        this.pawnBox = new PawnBox(id);
        this.pawns = new ArrayList<>();
        this.isPlaying = isPlaying;

        this.createPawnControllers();
        this.registerPawnListeners();
    }

    public Player getPlayer() {
        return this.player;
    }

    public PawnBox getPawnBox() {
        return this.pawnBox;
    }

    public List<PawnController> getPawns() {
        return this.pawns;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying() {
        this.isPlaying = !this.isPlaying;
    }

    private void createPawnControllers() {
        for (int i = 0; i < 3; i++) {
            this.pawns.add(new PawnController(this.player.getPawns().get(i), this.pawnBox.getPawns().get(i)));
        }
    }

    public void registerPawnListeners() {
        for (PawnController pawnController : this.pawns) {
            if (pawnController.getPawn().isMovable(this.player)) {
                pawnController.getPawnView().enableListeners();
            }
        }
    }

    public void unregisterPawnListeners() {
        this.pawns.forEach(pawnController -> pawnController.getPawnView().disableListeners());
    }

    public Optional<PawnController> findSelectedPawn() {
        return this.pawns.stream().filter(pawnController -> pawnController.getPawnView().isSelected())
                .findFirst();
    }

    public Optional<int[]> findSelectedCellId() {
        return this.findSelectedPawn().get().getPawnView().getCellId();
    }
}
