package org.example.model;

public class Pawn {

    private int id;

    private int symbol;

    private Cell position;

    public Pawn(int id, int symbol) {
        this.id = id;
        this.symbol = symbol;
        this.position = null;
    }

    public int getId() {
        return this.id;
    }

    public int getSymbol() {
        return symbol;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean isPlayable(Player player) {
        return this.symbol == player.getId();
    }

    public boolean isOnGrid() {
        return this.position != null;
    }
}

