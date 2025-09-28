package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell {

    private int rowId;
    private int columnId;

    private boolean status;

    private Pawn pawn;

    private List<Cell> neighborhood;

    public Cell(int rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
        this.status = true;
        this.pawn = null;
        this.neighborhood = new ArrayList<>();
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }

    public Pawn getPawn() {
        return this.pawn;
    }

    public List<Cell> getNeighborhood() {
        return neighborhood;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public boolean isEmpty() {
        return this.pawn == null;
    }

    public void createNeighborhood(Grid grid) {
        int[][] potentialNeighborsIds = {
                {this.rowId + 1, this.columnId},
                {this.rowId - 1, this.columnId},
                {this.rowId, this.columnId + 1},
                {this.rowId, this.columnId - 1}
        };

        for (int i = 0; i < Arrays.stream(potentialNeighborsIds).count(); i++) {
            Cell neighbor = grid.findCell(potentialNeighborsIds[i][0], potentialNeighborsIds[i][1]);
            if (neighbor != null) {
                this.neighborhood.add(neighbor);
            }
        }
    }
}
