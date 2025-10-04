package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public static final int PLAYER_PAWNS_NUMBER = 3;

    private final int id;

    private final List<Pawn> pawns;

    private int wins;

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

    public int countPlayedPawns() {
        int count = 0;
        for (Pawn pawn : this.pawns) {
            if (pawn.getPosition() != null) {
                count++;
            }
        }
        return count;
    }

    public int getWins() {
        return this.wins;
    }

    public void incrementWins() {
        this.wins++;
    }
}
