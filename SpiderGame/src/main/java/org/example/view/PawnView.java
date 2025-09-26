package org.example.view;

import javax.swing.*;
import java.awt.*;

public class PawnView extends JLayeredPane {

    public static final int RADIUS = 45;

    private final int x;

    private final int y;

    private final Color color;

    public PawnView(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        this.setLayout(null);
        this.setBounds(this.x, this.y, 2 * PawnView.RADIUS, 2 * PawnView.RADIUS);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.color);
        graphics.fillOval(0, 0, 2 * PawnView.RADIUS, 2 * PawnView.RADIUS);
    }
}
