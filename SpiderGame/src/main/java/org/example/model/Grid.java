package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private final int GRID_SIZE = 3;

    private final int GRID_PAWNS_NUMBER = 6;

    private List<Cell> grid;

    private List<Player> players;

    private List<Pawn> pawns;

    public Grid() {
        this.grid = new ArrayList<>();
        this.players = new ArrayList<>();
        this.pawns = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addPawn(Pawn pawn) {
        this.pawns.add(pawn);
    }

    public void createGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                this.grid.add(new Cell(i, j));
            }
        }

        for (Cell cell : this.grid) {
            cell.createNeighborhood(this);
        }
    }

    public Cell findCell(int rowId, int columnId) {
        for (Cell cell : this.grid) {
            if (cell.getRowId() == rowId && cell.getColumnId() == columnId) {
                return cell;
            }
        }
        return null;
    }

    public void movePawns() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (Player player : this.players) {
                System.out.println("Player " + player.getId() + " : ");
                this.display();
                Pawn pawn = player.choosePawn();
                int[] position = player.choosePosition();
                if (this.isPlayable(position, pawn)) {
                    if (this.pawns.size() == GRID_PAWNS_NUMBER) {
                        pawn.getPosition().setPawn(null);
                        this.grid.get(position[0] * GRID_SIZE + position[1]).setPawn(pawn);
                        pawn.setPosition(this.grid.get(position[0] * GRID_SIZE + position[1]));
                    } else {
                        this.grid.get(position[0] * GRID_SIZE + position[1]).setPawn(pawn);
                        pawn.setPosition(this.grid.get(position[0] * GRID_SIZE + position[1]));
                        this.pawns.add(pawn);
                    }
                }
            }
        }
    }

    public void display() {
        int count = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.println(
                    (this.grid.get(count).getPawn() == null
                            ? "0 "
                            : this.grid.get(count).getPawn().getSymbol() + "(" + this.grid.get(count).getPawn().getId() + ") ")
                            + (this.grid.get(count + 1).getPawn() == null
                            ? "0 "
                            : this.grid.get(1 + count).getPawn().getSymbol() + "(" + this.grid.get(1 + count).getPawn().getId() + ") ")
                            + (this.grid.get(2 + count).getPawn() == null
                            ? "0 "
                            : this.grid.get(2 + count).getPawn().getSymbol() + "(" + this.grid.get(2 + count).getPawn().getId() + ") ")
            );
            count += GRID_SIZE;
        }
    }

    public boolean isPlayable(int[] move, Pawn pawn) {
        Cell cell = this.grid.get(move[0] * GRID_SIZE + move[1]);
        if (cell.isEmpty()) {
            if (!pawn.isOnGrid()) {
                return true;
            } else {
                return pawn.isOnGrid()
                        && pawn.getPosition().getNeighborhood().contains(cell)
                        && this.pawns.size() == GRID_PAWNS_NUMBER;
            }
        }
        return false;
    }

    public boolean isOver() {
        return true;
    }
}
