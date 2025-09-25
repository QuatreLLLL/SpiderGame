package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CellView extends JLayeredPane {

    public static final int CELL_SIZE = 100;

    private final int x;

    private final int y;

    private final Color borderColor;

    private final Color innerColor;

    public CellView(int x, int y) {
        this.x = x;
        this.y = y;
        this.borderColor = Color.BLACK;
        this.innerColor = Color.WHITE;

        this.setLayout(null);
        this.setBounds(this.x, this.y, CellView.CELL_SIZE, CellView.CELL_SIZE);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.innerColor);
        graphics.fillRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE);
        graphics.setColor(this.borderColor);
        graphics.drawRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE);
    }

}
