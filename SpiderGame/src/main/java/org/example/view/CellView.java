package org.example.view;

import javax.swing.*;
import java.awt.*;

public class CellView extends JLayeredPane {

    public static final int CELL_SIZE = 100;
    private static final int ANIMATION_DURATION = 500;
    private static final int FPS = 30;
    private static final float MAX_PREVIEW_ALPHA = 0.25f;

    private final int rowId;
    private final int columnId;

    private final Color borderColor;
    private final Color innerColor;

    private final JComponent preview;
    private float previewAlpha = 0f;
    private Timer animationTimer;
    private long animationStartTime;

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

        this.preview = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                if (CellView.this.previewAlpha > 0f) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, CellView.this.previewAlpha));
                    g2d.setColor(new Color(255, 255, 255));
                    g2d.fillOval(PawnView.PAWN_PADDING, PawnView.PAWN_PADDING,
                            2 * PawnView.RADIUS, 2 * PawnView.RADIUS);
                    g2d.dispose();
                }
            }
        };
        this.preview.setBounds(0, 0, CellView.CELL_SIZE, CellView.CELL_SIZE);
        this.preview.setOpaque(false);
        this.add(this.preview, JLayeredPane.PALETTE_LAYER);
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
        if (this.status == status) {
            return;
        }
        this.status = status;
        this.animateCircle(status);
    }

    private void animateCircle(boolean fadeIn) {
        if (this.animationTimer != null && this.animationTimer.isRunning()) {
            this.animationTimer.stop();
        }

        this.animationStartTime = System.currentTimeMillis();

        this.animationTimer = new Timer(1000 / CellView.FPS, e -> {
            long elapsed = System.currentTimeMillis() - this.animationStartTime;
            this.previewAlpha = Math.min(CellView.MAX_PREVIEW_ALPHA, (float) elapsed / CellView.ANIMATION_DURATION);

            if (!fadeIn) {
                this.previewAlpha = CellView.MAX_PREVIEW_ALPHA - this.previewAlpha;
            }

            this.preview.repaint();

            if (elapsed >= CellView.ANIMATION_DURATION) {
                ((Timer) e.getSource()).stop();
                this.previewAlpha = fadeIn ? 0.25f : 0f;
                this.preview.repaint();
            }
        });

        this.animationTimer.start();
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
