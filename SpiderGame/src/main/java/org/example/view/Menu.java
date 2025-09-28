package org.example.view;

import org.example.controller.ValidateButtonListener;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    private final static int MENU_WIDTH = 400;
    private final static int MENU_HEIGHT = 200;

    private final static int ARC_PARAMETER = 50;

    private final static int MENU_MARGIN = 100;
    private final static int LABEL_PADDING = 10;
    private final static int BUTTON_PADDING = 20;

    private final static int LABEL_HEIGHT = 20;

    private final static int BUTTON_WIDTH = 100;
    private final static int BUTTON_HEIGHT = 50;

    private final static Color MENU_COLOR = GameColor.LIGHT_ASPHALT.get();
    private final static Color DEFAULT_COLOR_BUTTON = Color.GREEN;
    private final static Color DANGER_COLOR_BUTTON = Color.RED;

    private int x;
    private int y;

    private JButton validateButton;
    private JButton undoButton;
    private JButton restartButton;
    private JLabel playerOrder;


    public Menu(int x, int y) {
        this.x = x;
        this.y = y;
        this.validateButton = new JButton("Validate");
        this.undoButton = new JButton("Undo");
        this.restartButton = new JButton("Restart");
        this.playerOrder = new JLabel("Player 1");

        this.setLayout(null);
        this.setBounds((this.x - MENU_WIDTH) / 2, this.y - MENU_HEIGHT + Menu.MENU_MARGIN,
                MENU_WIDTH, MENU_HEIGHT);
        this.setOpaque(false);
        this.initialize();

        this.validateButton.addActionListener(new ValidateButtonListener(this.playerOrder));
    }

    public void initialize() {
        this.playerOrder.setBounds(0, Menu.LABEL_PADDING, Menu.MENU_WIDTH, Menu.LABEL_HEIGHT);
        this.playerOrder.setHorizontalAlignment(SwingConstants.CENTER);

        this.validateButton.setBounds(this.getWidth() / 2 - Menu.BUTTON_WIDTH - Menu.BUTTON_PADDING / 2,
                Menu.LABEL_PADDING + Menu.LABEL_PADDING + Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);

        this.undoButton.setBounds(this.getWidth() / 2 + Menu.BUTTON_PADDING / 2,
                Menu.LABEL_PADDING + Menu.LABEL_PADDING + Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);

        this.restartButton.setBounds(this.getWidth() - Menu.BUTTON_WIDTH - Menu.BUTTON_PADDING,
                this.getHeight() - Menu.BUTTON_HEIGHT - Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);

        this.add(this.playerOrder);
        this.add(this.validateButton);
        this.add(this.undoButton);
        this.add(this.restartButton);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Menu.MENU_COLOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, Menu.MENU_WIDTH, Menu.MENU_HEIGHT, Menu.ARC_PARAMETER,
                Menu.ARC_PARAMETER);
    }

}
