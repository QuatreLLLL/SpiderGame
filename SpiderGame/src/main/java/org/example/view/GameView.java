package org.example.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private final static int SCREEN_MARGIN = 50;
    private final static int LABEL_MARGIN = 10;

    private final static int TITLE_WIDTH = 200;
    private final static int TITLE_HEIGHT = 100;
    private final static int TITLE_FONT_SIZE = 28;

    private final static double GAMEBOARD_COEFFICIENT = 0.7;
    private final static double MENU_COEFFICIENT = 0.8;

    private int width;

    private int height;

    private JPanel panel;

    private GridView gridView;

    private PawnBox pawnBox1;

    private PawnBox pawnBox2;


    public GameView(GridView gridView, PawnBox pawnBox1, PawnBox pawnBox2) {
        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - GameView.SCREEN_MARGIN;
        this.panel = (JPanel) this.getContentPane();
        this.gridView = gridView;
        this.pawnBox1 = pawnBox1;
        this.pawnBox2 = pawnBox2;
    }

    public void initialize() {

        this.panel.setBounds(0, 0, this.width, this.height);
        this.panel.setLayout(null);
        this.panel.setBackground(GameColor.LIGHT_GRAY.get());

        this.gridView.iniatialize(this.panel.getWidth(),
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(gridView, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox1.initialize(PawnBox.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(pawnBox1, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox2.initialize(this.panel.getWidth() - PawnBox.BOX_WIDTH - PawnBox.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(pawnBox2, JLayeredPane.DEFAULT_LAYER);

        JLabel gameTitle = new JLabel("SPIDER GAME");
        gameTitle.setBounds((this.panel.getWidth() - GameView.TITLE_WIDTH) / 2, GameView.LABEL_MARGIN,
                GameView.TITLE_WIDTH, GameView.TITLE_HEIGHT);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setFont(new Font("Arial", Font.BOLD, GameView.TITLE_FONT_SIZE));
        this.panel.add(gameTitle);

        Menu menu = new Menu(this.width, (int) (GameView.MENU_COEFFICIENT * this.height));
        this.panel.add(menu);
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

