package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CellView extends JLayeredPane {

    public static final int CELL_SIZE = 100;

    private final int rowId;
    private final int columnId;

    private final Color borderColor;
    private final Color innerColor;

    private boolean status;

    public CellView(int x, int y, int rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
        this.borderColor = GameColor.LIGHT_ASPHALT.get();
        this.innerColor = GameColor.DARK_ASPHALT.get();
        this.status = false;

        this.setLayout(null);
        this.setBounds(x, y, CellView.CELL_SIZE, CellView.CELL_SIZE);
        this.setOpaque(false);
    }

    public int getRowId() {
        return this.rowId;
    }

    public int getColumnId() {
        return this.columnId;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.innerColor);
        graphics.fillRoundRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE, GameView.BORDER_RADIUS, GameView.BORDER_RADIUS);
        graphics.setColor(this.borderColor);
        graphics.drawRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE);
    }

}
