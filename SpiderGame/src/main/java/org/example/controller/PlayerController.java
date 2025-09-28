package org.example.controller;

import org.example.model.Player;
import org.example.view.PawnBox;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

public class PlayerController extends MouseAdapter {

    private final Player player;

    private final PawnBox pawnBox;

    private List<PawnListener> pawns;

    public PlayerController(int id) {
        this.player = new Player(id);
        this.pawnBox = new PawnBox(id);
        this.pawns = new ArrayList<>();

        this.createPawnControllers();
        this.registerPawnControllers();
    }

    public Player getPlayer() {
        return this.player;
    }

    public PawnBox getPawnBox() {
        return this.pawnBox;
    }

    private void createPawnControllers() {
        for (int i = 0; i < 3; i++) {
            this.pawns.add(new PawnListener(this.player.getPawns().get(i), this.pawnBox.getPawns().get(i)));
        }
    }

    public void registerPawnControllers() {
        for (PawnListener pawnListener : this.pawns) {
            pawnListener.registerListeners();
        }
    }

    public void unregisterPawnControllers() {
        for (PawnListener pawnListener : this.pawns) {
            pawnListener.unregisterListeners();
        }
    }

}
