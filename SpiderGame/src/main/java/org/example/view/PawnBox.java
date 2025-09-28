package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PawnBox extends JLayeredPane {

    public static final int BOX_WIDTH = 150;
    public static final int BOX_HEIGHT = 450;
    public static final int ARC_PARAMETER = 50;

    public static final int BOX_PADDING = 25;
    public static final int BOX_MARGIN = 100;

    public static final int PAWN_NUMBER = 3;

    private final List<PawnView> pawns;

    private int x;
    private int y;

    private final int playerId;

    private final Color innerColor;
    private final Color borderColor;
    private final Color playerColor;

    public PawnBox(int playerId) {
        this.pawns = new ArrayList<>();
        this.playerId = playerId;
        this.innerColor = GameColor.DARK_ASPHALT.get();
        this.borderColor = GameColor.LIGHT_ASPHALT.get();
        this.playerColor = this.playerId == 1 ? GameColor.BLUE.get() : GameColor.RED.get();
        this.createPawnBox();
    }

    public List<PawnView> getPawns() {
        return this.pawns;
    }

    public void initialize(int x, int y) {
        this.x = x;
        this.y = y;
        this.setLayout(null);
        this.setBounds(this.x, (this.y - PawnBox.BOX_HEIGHT) / 2, PawnBox.BOX_WIDTH, PawnBox.BOX_HEIGHT);
    }

    public void createPawnBox() {
        for (int i = 0; i < PawnBox.PAWN_NUMBER; i++) {
            int PAWN_OFFSET = ((PawnBox.BOX_HEIGHT - 2 * PawnBox.BOX_PADDING) / (PawnBox.PAWN_NUMBER));
            PawnView pawn = new PawnView(PawnBox.BOX_WIDTH / 2 - PawnView.RADIUS,
                    PawnBox.BOX_PADDING + PAWN_OFFSET * i + PAWN_OFFSET / 2 - PawnView.RADIUS,
                    this.playerColor, i);
            this.add(pawn, JLayeredPane.DEFAULT_LAYER);
            this.pawns.add(pawn);
        }
    }

/*    public void initializePawns() {
        for (int i = 0; i < 3; i++) {
            int PAWN_OFFSET = ((PawnBox.BOX_HEIGHT - 2 * PawnBox.BOX_PADDING) / (PawnBox.PAWN_NUMBER));
            this.pawns.get(i).initialize(PawnBox.BOX_WIDTH / 2 - PawnView.RADIUS,
                    PawnBox.BOX_PADDING + PAWN_OFFSET * i + PAWN_OFFSET / 2 - PawnView.RADIUS);
        }
    }*/

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D2 = (Graphics2D) graphics;
        graphics2D2.setColor(this.borderColor);
        graphics2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D2.fillRoundRect(0, 0, PawnBox.BOX_WIDTH, PawnBox.BOX_HEIGHT, PawnBox.ARC_PARAMETER, PawnBox.ARC_PARAMETER);

        Graphics2D graphics2D1 = (Graphics2D) graphics;
        graphics2D1.setColor(this.innerColor);
        graphics2D1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D1.fillRoundRect(PawnBox.BOX_PADDING, PawnBox.BOX_PADDING,
                PawnBox.BOX_WIDTH - 2 * PawnBox.BOX_PADDING, PawnBox.BOX_HEIGHT - 2 * PawnBox.BOX_PADDING,
                PawnBox.ARC_PARAMETER, PawnBox.ARC_PARAMETER);
    }
}
