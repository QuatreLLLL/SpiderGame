package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PawnSelectedListener extends MouseAdapter {

    private final PawnView target;
    private Point mouseOffset;
    private JLayeredPane rootLayer;
    private Container originalParent;
    private Point originalLocation;

    public PawnSelectedListener(PawnView target) {
        this.target = target;
        target.addMouseListener(this);
        target.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseOffset = e.getPoint();
        originalParent = target.getParent();
        originalLocation = target.getLocation(); // sauvegarde position initiale

        // Récupère le rootLayer de la JFrame
        rootLayer = (JLayeredPane) SwingUtilities.getRootPane(target).getLayeredPane();

        // Convertir la position du pion dans les coordonnées du rootLayer
        Point p = SwingUtilities.convertPoint(originalParent, originalLocation, rootLayer);

        // Retirer du parent et ajouter dans le rootLayer pour drag libre
        originalParent.remove(target);
        rootLayer.add(target, JLayeredPane.DRAG_LAYER);
        target.setLocation(p);
        rootLayer.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Déplacement libre dans le rootLayer
        Point p = SwingUtilities.convertPoint(target, e.getPoint(), rootLayer);
        target.setLocation(p.x - mouseOffset.x, p.y - mouseOffset.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Coordonnées de la souris dans le rootLayer
        Point mousePos = SwingUtilities.convertPoint(target, e.getPoint(), rootLayer);

        Container cell = null;

        // Récupère le GridView depuis la JFrame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(target);
        GridView gridView = (GridView) ((Container) frame.getContentPane().getComponents()[0]).getComponents()[0];

        // Vérifie si la souris est sur une CellView
        for (CellView cellView : gridView.getCells()) {
            Rectangle bounds = SwingUtilities.convertRectangle(cellView.getParent(), cellView.getBounds(), rootLayer);
            if (bounds.contains(mousePos)) {
                cell = cellView;
                break;
            }
        }

        // Retire le pion du rootLayer
        rootLayer.remove(target);
        rootLayer.repaint();

        if (cell != null) {
            target.setLocation(5, 5);
            cell.add(target, JLayeredPane.PALETTE_LAYER);  // ajouter d'abord

            System.out.println(target.getBounds());
            System.out.println(cell.getBounds());
            // maintenant c'est relatif au parent

            cell.revalidate();
            cell.repaint();
            originalParent = cell;
        } else {
            originalParent.add(target);
            target.setLocation(originalLocation);
            originalParent.revalidate();
            originalParent.repaint();
        }
    }
}
