package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CellView extends JLayeredPane {

    public static final int CELL_SIZE = 100;
    public static final int ARC_PARAMETER = 50;

    private final int x;
    private final int y;

    private final Color borderColor;
    private final Color innerColor;

    public CellView(int x, int y) {
        this.x = x;
        this.y = y;
        this.borderColor = GameColor.LIGHT_ASPHALT.get();
        this.innerColor = GameColor.DARK_ASPHALT.get();

        this.setLayout(null);
        this.setBounds(this.x, this.y, CellView.CELL_SIZE, CellView.CELL_SIZE);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.innerColor);
        graphics.fillRoundRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE, CellView.ARC_PARAMETER, CellView.ARC_PARAMETER);
        graphics.setColor(this.borderColor);
        graphics.drawRect(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE);
    }

}
