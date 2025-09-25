package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends JFrame {

    private int width;

    private int height;

    private JButton startButton;

    public MenuPage(int width, int height) {
        this.width = width;
        this.height = height;
        this.startButton = new JButton("Start");
    }

    public void display() {
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setLayout(null);
        this.startButton.setBounds(this.width / 2 - 50, this.height / 2 - 50, 100, 100);
        this.add(this.startButton);
        this.pack();
        this.setVisible(true);
    }
}
