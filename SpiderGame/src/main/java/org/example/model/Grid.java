package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public final static int GRID_SIZE = 3;

    private List<Cell> grid;

    public Grid() {
        this.grid = new ArrayList<>();

        this.createGrid();
    }

    public List<Cell> getGrid() {
        return this.grid;
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
}
