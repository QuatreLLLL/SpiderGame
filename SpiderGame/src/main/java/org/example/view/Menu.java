package org.example.view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    private static final int MENU_WIDTH = 400;
    private static final int MENU_HEIGHT = 200;

    private static final int MENU_MARGIN = 100;
    private static final int LABEL_PADDING = 20;
    private static final int BUTTON_PADDING = 20;

    private static final int LABEL_HEIGHT = 25;
    private static final int LABEL_FONT_SIZE = 20;

    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 50;

    private static final Color MENU_COLOR = GameColor.LIGHT_ASPHALT.get();
    private final JButton validateButton;
    private final JButton undoButton;
    private final JButton restartButton;
    private final JLabel playerOrder;

    private Runnable onValidateButtonPressed;
    private Runnable onRestartButtonPressed;
    private Runnable onUndoButtonPressed;


    public Menu(int x, int y) {
        this.validateButton = new JButton("Validate");
        this.validateButton.setForeground(GameColor.GREEN.get());
        this.undoButton = new JButton("Undo");
        this.undoButton.setForeground(GameColor.RED.get());
        this.restartButton = new JButton("Restart");
        this.playerOrder = new JLabel("Player's turn");

        this.setLayout(null);
        this.setBounds((x - Menu.MENU_WIDTH) / 2, y - Menu.MENU_HEIGHT + Menu.MENU_MARGIN,
                Menu.MENU_WIDTH, Menu.MENU_HEIGHT);
        this.setOpaque(false);
        this.initialize();

        this.validateButton.addActionListener(event -> {
            if (Menu.this.onValidateButtonPressed != null) {
                Menu.this.onValidateButtonPressed.run();
            }
        });

        this.restartButton.addActionListener(event -> {
            if (Menu.this.onRestartButtonPressed != null) {
                Menu.this.onRestartButtonPressed.run();
            }
        });

        this.undoButton.addActionListener(event -> {
            if (Menu.this.onUndoButtonPressed != null) {
                Menu.this.onUndoButtonPressed.run();
            }
        });
    }

    public JButton getValidateButton() {
        return this.validateButton;
    }

    public JButton getUndoButton() {
        return this.undoButton;
    }

    public JButton getRestartButton() {
        return this.restartButton;
    }

    public JLabel getPlayerOrder() {
        return this.playerOrder;
    }

    public void setOnValidateButtonPressed(Runnable handler) {
        this.onValidateButtonPressed = handler;
    }

    public void setOnRestartButtonPressed(Runnable handler) {
        this.onRestartButtonPressed = handler;
    }

    public void setOnUndoButtonPressed(Runnable handler) {
        this.onUndoButtonPressed = handler;
    }

    public void initialize() {
        this.playerOrder.setBounds(0, Menu.LABEL_PADDING, Menu.MENU_WIDTH, Menu.LABEL_HEIGHT);
        this.playerOrder.setHorizontalAlignment(SwingConstants.CENTER);

        this.validateButton.setBounds(this.getWidth() / 2 - Menu.BUTTON_WIDTH - Menu.BUTTON_PADDING / 2,
                Menu.LABEL_PADDING + Menu.LABEL_PADDING + Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        this.validateButton.setEnabled(false);

        this.undoButton.setBounds(this.getWidth() / 2 + Menu.BUTTON_PADDING / 2,
                Menu.LABEL_PADDING + Menu.LABEL_PADDING + Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        this.undoButton.setEnabled(false);

        this.restartButton.setBounds((this.getWidth() - Menu.BUTTON_WIDTH) / 2,
                this.getHeight() - Menu.BUTTON_HEIGHT - Menu.BUTTON_PADDING,
                Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
        this.restartButton.setVisible(false);

        this.playerOrder.setFont(new Font("Arial", Font.BOLD, Menu.LABEL_FONT_SIZE));

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
        g2d.fillRoundRect(0, 0, Menu.MENU_WIDTH, Menu.MENU_HEIGHT, GameView.BORDER_RADIUS,
                GameView.BORDER_RADIUS);
    }

}
