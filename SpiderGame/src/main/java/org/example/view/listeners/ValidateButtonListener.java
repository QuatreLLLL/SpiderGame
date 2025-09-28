package org.example.view.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidateButtonListener implements ActionListener {

    private JLabel playerOrder;

    public ValidateButtonListener(JLabel playerOrder) {
        this.playerOrder = playerOrder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playerOrder.setText("PlayerX");
    }
}
