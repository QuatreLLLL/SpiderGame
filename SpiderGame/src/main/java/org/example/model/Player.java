package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int PLAYER_PAWNS_NUMBER = 3;

    private final int id;

    private final List<Pawn> pawns;

    public Player(int id) {
        this.id = id;
        this.pawns = new ArrayList<>();

        this.createPawns();
    }

    public int getId() {
        return this.id;
    }

    public List<Pawn> getPawns() {
        return this.pawns;
    }

    public void createPawns() {
        for (int i = 0; i < Player.PLAYER_PAWNS_NUMBER; i++) {
            this.pawns.add(new Pawn(i, this.id));
        }
    }

    public Pawn selectPawn(Pawn pawn) {
        return pawn;
    }

    public Cell selectCell(Cell cell) {
        return cell;
    }
}
