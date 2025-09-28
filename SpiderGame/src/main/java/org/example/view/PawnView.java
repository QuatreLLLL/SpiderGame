package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PawnView extends JLayeredPane {

    public static final int RADIUS = 45;

    private final int x;
    private final int y;

    private final int id;

    private final Color color;

    public PawnView(int x, int y, Color color, int id) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.id = id;

        this.setLayout(null);
        this.setBounds(this.x, this.y, 2 * PawnView.RADIUS, 2 * PawnView.RADIUS);
        this.setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics.create();

        int x = 0;
        int y = 0;

        float centerX = x + PawnView.RADIUS;
        float centerY = y + PawnView.RADIUS;

        float[] dist = {0.4f, 1.0f};
        Color[] colors = {this.color, this.color.darker()};

        RadialGradientPaint paint = new RadialGradientPaint(
                new Point2D.Float(centerX, centerY),
                PawnView.RADIUS,
                dist,
                colors
        );

        g2d.setPaint(paint);
        g2d.fillOval(x, y, PawnView.RADIUS * 2, PawnView.RADIUS * 2);

        GradientPaint highlight = new GradientPaint(
                centerX, y, new Color(255, 255, 255, 50),
                centerX, y + PawnView.RADIUS / 3f, new Color(255, 255, 255, 0)
        );
        g2d.setPaint(highlight);
        g2d.fillOval(x + PawnView.RADIUS / 2, y, PawnView.RADIUS, PawnView.RADIUS / 2);

        g2d.dispose();
    }
}
