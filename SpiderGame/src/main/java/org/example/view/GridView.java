package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridView extends JLayeredPane {

    public static final int GRID_SIZE = 350;

    public static final int GRID_PADDING = 25;

    public static final int ARC_PARAMETER = 50;

    private final List<CellView> cells;

    private final Color gridColor;

    private final int x;

    private final int y;

    public GridView(int x, int y) {
        this.cells = new ArrayList<>();
        this.gridColor = GameColor.LIGHT_ASPAHLT.get();
        this.x = x;
        this.y = y;

        this.setLayout(null);
        this.setBounds((this.x - GridView.GRID_SIZE) / 2, (this.y - GridView.GRID_SIZE) / 2, GridView.GRID_SIZE, GridView.GRID_SIZE);
        this.createGrid();
    }

    public List<CellView> getCells() {
        return this.cells;
    }

    private void createGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CellView cellView = new CellView(GridView.GRID_PADDING + j * CellView.CELL_SIZE,
                        GridView.GRID_PADDING + i * CellView.CELL_SIZE);
                cellView.setBounds(GridView.GRID_PADDING + j * CellView.CELL_SIZE,
                        GridView.GRID_PADDING + i * CellView.CELL_SIZE,
                        CellView.CELL_SIZE,
                        CellView.CELL_SIZE);
                cellView.addMouseListener(new CellListener(cellView));
                this.add(cellView, JLayeredPane.DEFAULT_LAYER);
                this.cells.add(cellView);
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(this.gridColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, GridView.GRID_SIZE, GridView.GRID_SIZE, GridView.ARC_PARAMETER, GridView.ARC_PARAMETER);
    }
}
