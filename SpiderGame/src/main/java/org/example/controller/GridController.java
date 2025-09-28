package org.example.controller;

import org.example.model.Grid;
import org.example.view.GridView;

public class GridController {

    private final Grid grid;

    private final GridView gridView;


    public GridController() {
        this.grid = new Grid();
        this.gridView = new GridView();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public GridView getGridView() {
        return this.gridView;
    }
}
