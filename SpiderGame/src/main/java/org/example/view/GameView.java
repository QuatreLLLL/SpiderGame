package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameView extends JFrame {

    public static final int BORDER_RADIUS = 50;

    private static final int SCREEN_MARGIN = 50;
    private static final int LABEL_MARGIN = 20;

    private static final int TITLE_WIDTH = 500;
    private static final int TITLE_HEIGHT = 100;
    private static final int TITLE_FONT_SIZE = 64;
    private static final int DIALOG_TITLE_FONT_SIZE = 32;
    private static final int BODY_FONT_SIZE = 20;

    private static final int DIALOG_WIDTH = 500;
    private static final int DIALOG_HEIGHT = 300;

    private static final double GAMEBOARD_COEFFICIENT = 0.7;
    private static final double MENU_COEFFICIENT = 0.8;

    private final int width;
    private final int height;

    private final JPanel panel;
    private final GridView gridView;
    private final PlayerView pawnBox1;
    private final PlayerView pawnBox2;
    private final Menu menu;
    private final JLabel gameTitle;
    private final JDialog dialog;
    private final JLabel titleLabel;
    private final JLabel bodyLabel;

    public GameView(GridView gridView, PlayerView pawnBox1, PlayerView pawnBox2) {
        this.width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - GameView.SCREEN_MARGIN;
        this.panel = (JPanel) this.getContentPane();
        this.gridView = gridView;
        this.pawnBox1 = pawnBox1;
        this.pawnBox2 = pawnBox2;
        this.gameTitle = new JLabel("Spider Game");
        this.menu = new Menu(this.width, (int) (GameView.MENU_COEFFICIENT * this.height));
        this.menu.getPlayerOrder().setForeground(GameColor.BLUE.get());
        this.dialog = new JDialog(this);
        this.titleLabel = new JLabel("Player wins!");
        this.bodyLabel = new JLabel();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public JDialog getDialog() {
        return this.dialog;
    }

    public JLabel getTitleLabel() {
        return this.titleLabel;
    }

    public JLabel getBodylabel() {
        return this.bodyLabel;
    }

    public void initialize() {

        this.panel.setBounds(0, 0, this.width, this.height);
        this.panel.setLayout(null);
        this.panel.setBackground(GameColor.LIGHT_GRAY.get());

        this.gridView.initialize(this.panel.getWidth(),
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.gridView, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox1.initialize(PlayerView.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.pawnBox1, JLayeredPane.DEFAULT_LAYER);

        this.pawnBox2.initialize(this.panel.getWidth() - PlayerView.BOX_WIDTH - PlayerView.BOX_MARGIN,
                (int) (GameView.GAMEBOARD_COEFFICIENT * this.panel.getHeight()));
        this.panel.add(this.pawnBox2, JLayeredPane.DEFAULT_LAYER);

        this.gameTitle.setBounds((this.panel.getWidth() - GameView.TITLE_WIDTH) / 2, GameView.LABEL_MARGIN,
                GameView.TITLE_WIDTH, GameView.TITLE_HEIGHT);
        this.gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        this.gameTitle.setFont(GameView.getFont(GameView.TITLE_FONT_SIZE));

        this.dialog.setBounds((this.width - GameView.DIALOG_WIDTH) / 2,
                (this.height - GameView.DIALOG_HEIGHT) / 2, GameView.DIALOG_WIDTH, GameView.DIALOG_HEIGHT);
        this.dialog.getContentPane().setBackground(GameColor.LIGHT_GRAY.get());
        this.titleLabel.setBounds((GameView.DIALOG_WIDTH - 200) / 2, (GameView.DIALOG_HEIGHT - 50) / 2 - 80, 200, 50);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, GameView.DIALOG_TITLE_FONT_SIZE));
        this.dialog.add(this.titleLabel);
        this.bodyLabel.setBounds((GameView.DIALOG_WIDTH - 200) / 2, (GameView.DIALOG_HEIGHT - 50) / 2 + 80, 200, 50);
        this.bodyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.bodyLabel.setFont(new Font("Arial", Font.PLAIN, GameView.BODY_FONT_SIZE));
        this.dialog.add(this.bodyLabel);
        this.dialog.setVisible(false);

        this.panel.add(this.gameTitle);
        this.panel.add(this.menu);
    }

    public void restart() {
        this.pawnBox1.restart();
        this.pawnBox2.restart();
    }

    public void showComponent(Component component, boolean show) {
        component.setVisible(show);
    }

    public void updateLabelColor(JLabel label, PlayerView pawnBox) {
        label.setForeground(pawnBox.getPawns().get(0).getColor());
    }

    public void display() {
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setResizable(false);
        this.setLayout(null);
        this.initialize();
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Font getFont(int size) {
        Font font = new Font("Arial", Font.PLAIN, size);
        try (InputStream inputStream = GameView.class.getResourceAsStream("/fonts/Overpass-Bold.ttf")) {
            if (inputStream != null) {
                font = Font.createFont(Font.TRUETYPE_FONT, inputStream)
                        .deriveFont((float) size);
            }
        } catch (IOException | FontFormatException ignored) {
        }
        return font;
    }
}

