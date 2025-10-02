package org.example.model;

public class Pawn {

    private final int id;

    private final int symbol;

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
        return this.symbol;
    }

    public Cell getPosition() {
        return this.position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean isMovable(Player player) {
        return this.position == null || player.countPlayedPawns() == Player.PLAYER_PAWNS_NUMBER;
    }
}

