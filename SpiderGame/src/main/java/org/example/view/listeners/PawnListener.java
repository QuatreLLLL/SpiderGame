package org.example.view.listeners;

import org.example.view.CellView;
import org.example.view.GridView;
import org.example.view.PawnView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PawnListener extends MouseAdapter {

    private final static int PAWN_PADDING = 5;

    private final PawnView pawn;

    private Point mouseOffset;

    private JLayeredPane rootLayer;

    private Container originalParent;
    private Point originalLocation;

    public PawnListener(PawnView pawn) {
        this.pawn = pawn;
        pawn.addMouseListener(this);
        pawn.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseOffset = e.getPoint();
        this.originalParent = this.pawn.getParent();
        this.originalLocation = this.pawn.getLocation(); // sauvegarde position initiale

        this.rootLayer = (JLayeredPane) SwingUtilities.getRootPane(this.pawn).getLayeredPane();
        Point p = SwingUtilities.convertPoint(this.originalParent, this.originalLocation, this.rootLayer);
        this.originalParent.remove(this.pawn);
        this.rootLayer.add(this.pawn, JLayeredPane.DRAG_LAYER);
        this.pawn.setLocation(p);
        this.rootLayer.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(this.pawn, e.getPoint(), this.rootLayer);
        this.pawn.setLocation(mousePosition.x - this.mouseOffset.x, mousePosition.y - this.mouseOffset.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(pawn, e.getPoint(), rootLayer);
        Container cell = null;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(pawn);
        GridView gridView = (GridView) frame.getContentPane().getComponents()[0];

        for (CellView cellView : gridView.getCells()) {
            Rectangle bounds = SwingUtilities.convertRectangle(cellView.getParent(), cellView.getBounds(), rootLayer);
            if (bounds.contains(mousePosition)) {
                cell = cellView;
                break;
            }
        }

        rootLayer.remove(pawn);
        rootLayer.repaint();

        if (cell != null) {
            pawn.setLocation(PawnListener.PAWN_PADDING, PawnListener.PAWN_PADDING);
            cell.add(pawn, JLayeredPane.PALETTE_LAYER);
            cell.revalidate();
            cell.repaint();
            originalParent = cell;
        } else {
            originalParent.add(pawn);
            pawn.setLocation(originalLocation);
            originalParent.revalidate();
            originalParent.repaint();
        }
    }
}
