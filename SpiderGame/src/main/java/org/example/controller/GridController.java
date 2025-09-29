package org.example.controller;

import org.example.model.Grid;
import org.example.view.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridController {

    private final Grid grid;

    private final GridView gridView;

    private List<CellController> cells;


    public GridController() {
        this.grid = new Grid();
        this.gridView = new GridView();
        this.cells = new ArrayList<>();

        this.createCrid();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public GridView getGridView() {
        return this.gridView;
    }

    public List<CellController> getCells() {
        return this.cells;
    }

    public void createCrid() {
        for (int i = 0; i < Grid.GRID_SIZE * Grid.GRID_SIZE; i++) {
            this.cells.add(new CellController(this.grid.getCells().get(i), this.gridView.getCells().get(i)));
        }
    }
}
