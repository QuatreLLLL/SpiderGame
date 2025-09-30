package org.example.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final int BORDER_RADIUS = 50;

    private static final int SCREEN_MARGIN = 50;
    private static final int LABEL_MARGIN = 10;

    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 100;
    private static final int TITLE_FONT_SIZE = 28;

    private static final double GAMEBOARD_COEFFICIENT = 0.7;
    private static final double MENU_COEFFICIENT = 0.8;

    private final int width;
    private final int height;

    private final JPanel panel;
    private final GridView gridView;
    private final PawnBox pawnBox1;
    private final PawnBox pawnBox2;
    private final Menu menu;

    private Runnable onValidateButtonPressed;

    public GameView(GridView gridView, PawnBox pawnBox1, PawnBox pawnBox2) {
        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - GameView.SCREEN_MARGIN;
        this.panel = (JPanel) this.getContentPane();
        this.gridView = gridView;
        this.pawnBox1 = pawnBox1;
        this.pawnBox2 = pawnBox2;
        this.menu = new Menu(this.width, (int) (GameView.MENU_COEFFICIENT * this.height));

        this.menu.getValidateButton().addActionListener(event -> {
            if (GameView.this.onValidateButtonPressed != null) {
                GameView.this.onValidateButtonPressed.run();
            }
        });
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setOnValidateButtonPressed(Runnable handler) {
        this.onValidateButtonPressed = handler;
    }


    public void initialize() {

        this.panel.setBounds(0, 0, this.width, this.height);
        this.panel.setLayout(null);
        this.panel.setBackground(GameColor.LIGHT_GRAY.get());

        this.gridView.initialize(this.panel.getWidth(),
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.gridView, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox1.initialize(PawnBox.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.pawnBox1, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox2.initialize(this.panel.getWidth() - PawnBox.BOX_WIDTH - PawnBox.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.pawnBox2, JLayeredPane.DEFAULT_LAYER);

        JLabel gameTitle = new JLabel("SPIDER GAME");
        gameTitle.setBounds((this.panel.getWidth() - GameView.TITLE_WIDTH) / 2, GameView.LABEL_MARGIN,
                GameView.TITLE_WIDTH, GameView.TITLE_HEIGHT);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setFont(new Font("Arial", Font.BOLD, GameView.TITLE_FONT_SIZE));
        this.panel.add(gameTitle);
        this.panel.add(this.menu);
    }

    public void display() {
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setResizable(false);
        this.setLayout(null);

        this.initialize();
        this.pack();
        this.setVisible(true);
    }
}

