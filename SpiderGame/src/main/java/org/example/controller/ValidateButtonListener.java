package org.example.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ValidateButtonListener implements ActionListener {

    private final static String player1Turn = "Player 1";

    private final static String  player2Turn = "Player 2";

    private JLabel playerOrder;

    public ValidateButtonListener(JLabel playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(this.playerOrder.getText(), ValidateButtonListener.player1Turn)) {
            this.playerOrder.setText(ValidateButtonListener.player2Turn);
        } else {
            this.playerOrder.setText(ValidateButtonListener.player1Turn);
        }
    }
}
