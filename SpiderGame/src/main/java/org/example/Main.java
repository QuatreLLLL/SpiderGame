package org.example;

import org.example.view.GamePage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        GamePage gamePage = new GamePage();

        gamePage.display();
/*        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get screen size

        GridView gridView = new GridView(3, 3);

        frame.add(gridView);

        frame.pack();

        frame.setVisible(true);
        gridView.repaint();*/


        //GamePage gamePage = new GamePage(width, height);
        //gamePage.display();

        // créer un objet qui hérite de Jframe
        // appeler les methodes create dans les constructeurs
/*
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(Color.BLUE); // background instead of foreground

        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        f.add(panel, gbc);
        f.setVisible(true);*/
    }
}