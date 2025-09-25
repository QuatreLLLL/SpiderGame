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

    public Pawn choosePawn() {
        System.out.print("Choose a pawn: ");
        try {
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            return this.pawns.stream().filter(pawn -> pawn.getId() == id).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int[] choosePosition() {
        System.out.println("Choose the position : ");
        try {
            Scanner scanner1 = new Scanner(System.in);
            Scanner scanner2 = new Scanner(System.in);
            int row = scanner1.nextInt();
            int column = scanner2.nextInt();
            int[] move = {row, column};
            return move;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
