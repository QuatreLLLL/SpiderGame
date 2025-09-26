package org.example.view;

import javax.swing.*;
import java.awt.*;

public class GamePage extends JFrame {

    private double topPanelHeightCoefficient = 0.7;

    private int width;

    private int height;

    private JPanel topPanel;

    private JPanel bottomPanel;

    public GamePage() {
        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.topPanel = new JPanel();
        this.bottomPanel = new JPanel();

        this.add(this.topPanel);
        this.add(this.bottomPanel);
    }

    public void draw() {

        this.topPanel.setLayout(null);
        this.topPanel.setBounds(0, 0,
                this.width, (int) (topPanelHeightCoefficient * this.height));
        this.topPanel.setBackground(GameColor.LIGHT_GRAY.get());

        GridView gridView = new GridView(this.topPanel.getWidth(), this.topPanel.getHeight());
        this.topPanel.add(gridView, JLayeredPane.DEFAULT_LAYER);

        PawnBox pawnBox1 = new PawnBox(PawnBox.BOX_MARGIN, (this.topPanel.getHeight() - PawnBox.BOX_HEIGHT) / 2, 0);
        this.topPanel.add(pawnBox1, JLayeredPane.DEFAULT_LAYER);
        pawnBox1.addListenerToPawns();

        PawnBox pawnBox2 = new PawnBox(this.topPanel.getWidth() - PawnBox.BOX_WIDTH - PawnBox.BOX_MARGIN,
                (this.topPanel.getHeight() - PawnBox.BOX_HEIGHT) / 2, 1);
        this.topPanel.add(pawnBox2, JLayeredPane.DEFAULT_LAYER);
        pawnBox2.addListenerToPawns();

        JLabel gameTitle = new JLabel("Spider Game");
        gameTitle.setBounds(this.topPanel.getWidth(), 10, 200, 100);
        this.topPanel.add(gameTitle);

        this.bottomPanel.setLayout(null);
        this.bottomPanel.setBounds(0, (int) (topPanelHeightCoefficient * this.height),
                this.width, (int) ((1 - topPanelHeightCoefficient) * this.height));
        this.bottomPanel.setBackground(GameColor.DARK_GRAY.get());

        JLabel playerOrder = new JLabel("ttttt");
        playerOrder.setBounds(this.bottomPanel.getWidth() / 2,  25, 200, 50);

        JButton validateButton = new JButton("Validate");
        validateButton.setBounds((this.bottomPanel.getWidth()) / 2 - 200, (this.bottomPanel.getHeight()) / 2 - 50,  200, 50);

        JButton undoButton = new JButton("Undo");
        undoButton.setBounds((this.bottomPanel.getWidth()) / 2 + 25, (this.bottomPanel.getHeight()) / 2 - 50,  200, 50);


        JButton backButton = new JButton("Back");
        backButton.setBounds( this.bottomPanel.getWidth() - 200, this.bottomPanel.getHeight() - 50,200, 50);

        this.bottomPanel.add(playerOrder);
        this.bottomPanel.add(validateButton);
        this.bottomPanel.add(undoButton);
        this.bottomPanel.add(backButton);
    }

    public void display() {
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setResizable(true);
        this.setLayout(null);

        this.draw();
        this.pack();
        this.setVisible(true);
    }
}
