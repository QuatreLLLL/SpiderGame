package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerView extends JLayeredPane {

    public static final int BOX_WIDTH = 150;
    public static final int BOX_HEIGHT = 450;

    public static final int BOX_PADDING = 25;
    public static final int BOX_MARGIN = 100;

    public static final int PAWN_NUMBER = 3;

    private final List<PawnView> pawns;

    private final int playerId;

    private final Color innerColor;
    private final Color borderColor;
    private final Color playerColor;

    public PlayerView(int playerId) {
        this.pawns = new ArrayList<>();
        this.playerId = playerId;
        this.innerColor = GameColor.DARK_ASPHALT.get();
        this.borderColor = GameColor.LIGHT_ASPHALT.get();
        this.playerColor = this.playerId == 0 ? GameColor.BLUE.get() : GameColor.RED.get();
        this.createPawnBox();
    }

    public List<PawnView> getPawns() {
        return this.pawns;
    }

    public void initialize(int x, int y) {
        this.setLayout(null);
        this.setBounds(x, (y - PlayerView.BOX_HEIGHT) / 2, PlayerView.BOX_WIDTH, PlayerView.BOX_HEIGHT);
    }

    public void createPawnBox() {
        for (int i = 0; i < PlayerView.PAWN_NUMBER; i++) {
            int PAWN_OFFSET = ((PlayerView.BOX_HEIGHT - 2 * PlayerView.BOX_PADDING) / (PlayerView.PAWN_NUMBER));
            PawnView pawn = new PawnView(PlayerView.BOX_WIDTH / 2 - PawnView.RADIUS,
                    PlayerView.BOX_PADDING + PAWN_OFFSET * i + PAWN_OFFSET / 2 - PawnView.RADIUS,
                    this.playerColor, i);
            this.add(pawn, JLayeredPane.DEFAULT_LAYER);
            pawn.setCurrentParent(this);
            pawn.setCurrentLocation(pawn.getLocation());
            pawn.updateParent();
            this.pawns.add(pawn);
        }
    }

    public void restart() {
        for (int i = 0; i < PlayerView.PAWN_NUMBER; i++) {
            int PAWN_OFFSET = ((PlayerView.BOX_HEIGHT - 2 * PlayerView.BOX_PADDING) / (PlayerView.PAWN_NUMBER));
            PawnView pawn = this.pawns.get(i);
            pawn.getCurrentParent().remove(pawn);
            pawn.getCurrentParent().repaint();
            pawn.setLocation(PlayerView.BOX_WIDTH / 2 - PawnView.RADIUS,
                    PlayerView.BOX_PADDING + PAWN_OFFSET * i + PAWN_OFFSET / 2 - PawnView.RADIUS);
            this.add(pawn);
        }
        this.revalidate();
        this.repaint();
    }

/*    public void initializePawns() {
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D2 = (Graphics2D) graphics;
        graphics2D2.setColor(this.borderColor);
        graphics2D2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D2.fillRoundRect(0, 0, PlayerView.BOX_WIDTH, PlayerView.BOX_HEIGHT, GameView.BORDER_RADIUS, GameView.BORDER_RADIUS);

        Graphics2D graphics2D1 = (Graphics2D) graphics;
        graphics2D1.setColor(this.innerColor);
        graphics2D1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D1.fillRoundRect(PlayerView.BOX_PADDING, PlayerView.BOX_PADDING,
                PlayerView.BOX_WIDTH - 2 * PlayerView.BOX_PADDING, PlayerView.BOX_HEIGHT - 2 * PlayerView.BOX_PADDING,
                GameView.BORDER_RADIUS, GameView.BORDER_RADIUS);
    }
}
