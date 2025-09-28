package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Player {

    private final int PLAYER_PAWNS_NUMBER = 3;

    private int id;

    private List<Pawn> pawns;

    public Player(int id) {
        this.id = id;
        this.pawns = new ArrayList<>();
        this.createPawns();
    }

    public int getId() {
        return id;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void createPawns() {
        for (int i = 0; i < PLAYER_PAWNS_NUMBER; i++) {
            this.pawns.add(new Pawn(i, this.id));
        }
    }
}
