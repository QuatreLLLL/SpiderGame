package org.example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    public static final int GRID_PAWNS_NUMBER = 6;

    private final List<Player> players;
    private final Set<Pawn> pawns;
    private final Grid grid;

    public Game(Grid grid) {
        this.players = new ArrayList<>();
        this.pawns = new HashSet<>();
        this.grid = grid;
    }

    public void addPawn(Pawn pawn) {
        this.pawns.add(pawn);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Grid getGrid() {
        return this.grid;
    }

    public List<Cell> getLegalMoves(Pawn pawn) {
        List<Cell> candidates = (this.pawns.size() == Game.GRID_PAWNS_NUMBER)
                ? pawn.getPosition().getNeighborhood()
                : this.grid.getCells();

        return candidates.stream()
                .filter(Cell::isEmpty)
                .collect(Collectors.toList());
    }

    public void updatePosition(Pawn pawn, int[] cellId) {
        Cell cell = this.grid.findCell(cellId[0], cellId[1]);
        if (this.pawns.size() == Game.GRID_PAWNS_NUMBER) {
            pawn.getPosition().setPawn(null);
        }
        pawn.setPosition(cell);
        cell.setPawn(pawn);
    }

    private int countConsecutive(int row, int column, int dx, int dy, Pawn pawn) {
        int count = 0;
        int x = row + dx;
        int y = column + dy;

        while (x >= 0 && x < Grid.GRID_SIZE && y >= 0 && y < Grid.GRID_SIZE
                && this.grid.findCell(x, y).getPawn() != null
                && this.grid.findCell(x, y).getPawn().getSymbol() == pawn.getSymbol()) {
            count++;
            x += dx;
            y += dy;
        }
        return count;
    }

    public boolean isOver(Pawn lastMove) {
        if (this.pawns.size() < Game.GRID_PAWNS_NUMBER - 1) {
            return false;
        }

        int[][] directions = {
                {1, 0},
                {0, 1},
                {1, 1},
                {1, -1}
        };

        for (int[] dir : directions) {
            int count = 1;
            int row = lastMove.getPosition().getRowId();
            int column = lastMove.getPosition().getColumnId();

            count += this.countConsecutive(row, column, dir[0], dir[1], lastMove);
            count += this.countConsecutive(row, column, -dir[0], -dir[1], lastMove);
            if (count >= 3) {
                return true;
            }
        }

        return false;
    }

    public void restart() {
        this.grid.getCells().forEach(cell -> cell.setPawn(null));
        this.pawns.forEach(pawn -> pawn.setPosition(null));
        this.pawns.clear();
    }
}
