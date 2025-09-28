package org.example.view;

import javax.swing.*;
import java.awt.*;

public class GamePage extends JFrame {

    private final static int SCREEN_MARGIN = 50;

    private final static int TITLE_WIDTH = 200;

    private final static int TITLE_HEIGHT = 100;

    private int width;

    private int height;

    private JPanel panel;

    public GamePage() {
        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - GamePage.SCREEN_MARGIN;
        this.panel = (JPanel) this.getContentPane();
    }

    public void initialize() {

        this.panel.setBounds(0, 0, this.width, this.height);
        this.panel.setLayout(null);
        this.panel.setBackground(GameColor.LIGHT_GRAY.get());

        GridView gridView = new GridView(this.panel.getWidth(), (int) (0.7 * this.panel.getHeight()));
        this.panel.add(gridView, JLayeredPane.DEFAULT_LAYER);

        PawnBox pawnBox1 = new PawnBox(PawnBox.BOX_MARGIN, (int) (0.7 * this.panel.getHeight()), 0);
        this.panel.add(pawnBox1, JLayeredPane.DEFAULT_LAYER);
        pawnBox1.addListenerToPawns();


        PawnBox pawnBox2 = new PawnBox(this.panel.getWidth() - PawnBox.BOX_WIDTH - PawnBox.BOX_MARGIN,
                (int) (0.7 * this.panel.getHeight()), 1);
        this.panel.add(pawnBox2, JLayeredPane.DEFAULT_LAYER);
        pawnBox2.addListenerToPawns();

        JLabel gameTitle = new JLabel("SPIDER GAME");
        gameTitle.setBounds((this.panel.getWidth() - GamePage.TITLE_WIDTH) / 2, 10, GamePage.TITLE_WIDTH,
                GamePage.TITLE_HEIGHT);
        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setFont(new Font("Arial", Font.BOLD, 28));
        this.panel.add(gameTitle);

        Menu menu = new Menu(this.width, (int) (0.8 * this.height));
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
