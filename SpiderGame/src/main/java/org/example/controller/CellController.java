package org.example.controller;

import org.example.model.Cell;
import org.example.view.CellView;

public class CellController {

    private final Cell cell;

    private final CellView cellView;

    public CellController(Cell cell, CellView cellView) {
        this.cell = cell;
        this.cellView = cellView;
    }

    public Cell getCell() {
        return this.cell;
    }

    public CellView getCellView() {
        return this.cellView;
    }

    public void setCellStatus(boolean status) {
        this.cellView.setStatus(status);
    }

}
