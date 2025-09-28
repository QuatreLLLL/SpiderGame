package org.example.model;

import org.example.controller.PawnListener;
import org.example.view.CellView;

import java.util.ArrayList;
import java.util.List;

import static org.example.model.Grid.GRID_SIZE;

public class Game {

    private final static int GRID_PAWNS_NUMBER = 6;

    private List<Player> players;
    private Grid grid;

    public Game() {
        this.players = new ArrayList<>();
        this.grid = new Grid();
    }

    public void movePawns() {
        for (Player player : this.players) {
        }
    }

    public boolean isPlayable(Pawn pawn, Cell cell) {
        return pawn.getPosition().getNeighborhood().contains(cell) && cell.isEmpty();
    }

/*    public void movePawn(Pawn pawn, Cell cell) {
        Pawn pawn = player.choosePawn();
        int[] position = player.choosePosition();
        if (this.isPlayable(position, pawn)) {
            if (this.pawns.size() == Game.GRID_PAWNS_NUMBER) {
                pawn.getPosition().setPawn(null);
                this.grid.getGrid().get(position[0] * GRID_SIZE + position[1]).setPawn(pawn);
                pawn.setPosition(this.grid.getGrid().get(position[0] * GRID_SIZE + position[1]));
            } else {
                this.grid.getGrid().get(position[0] * GRID_SIZE + position[1]).setPawn(pawn);
                pawn.setPosition(this.grid.getGrid().get(position[0] * GRID_SIZE + position[1]));
                this.pawns.add(pawn);
            }
        }
    }*/

/*    private boolean isPlayable(int[] move, Pawn pawn) {
        Cell cell = this.grid.getGrid().get(move[0] * GRID_SIZE + move[1]);
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
    }*/
}
