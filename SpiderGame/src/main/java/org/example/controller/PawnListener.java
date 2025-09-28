package org.example.controller;

import org.example.model.Pawn;
import org.example.view.CellView;
import org.example.view.GridView;
import org.example.view.PawnView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PawnListener extends MouseAdapter {

    private final static int PAWN_PADDING = 5;

    private final Pawn pawn;

    private final PawnView pawnView;

    private Point mouseOffset;

    private JLayeredPane rootLayer;

    private Container originalParent;
    private Point originalLocation;

    public PawnListener(Pawn pawn, PawnView pawnView) {
        this.pawn = pawn;
        this.pawnView = pawnView;
    }

    public void registerListeners() {
        this.pawnView.addMouseListener(this);
        this.pawnView.addMouseMotionListener(this);
    }

    public void unregisterListeners() {
        this.pawnView.removeMouseListener(this);
        this.pawnView.removeMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseOffset = e.getPoint();
        this.originalParent = this.pawnView.getParent();
        this.originalLocation = this.pawnView.getLocation();

        this.rootLayer = SwingUtilities.getRootPane(this.pawnView).getLayeredPane();
        Point p = SwingUtilities.convertPoint(this.originalParent, this.originalLocation, this.rootLayer);
        this.originalParent.remove(this.pawnView);
        this.rootLayer.add(this.pawnView, JLayeredPane.DRAG_LAYER);
        this.pawnView.setLocation(p);
        this.rootLayer.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(this.pawnView, e.getPoint(), this.rootLayer);
        this.pawnView.setLocation(mousePosition.x - this.mouseOffset.x, mousePosition.y - this.mouseOffset.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(pawnView, e.getPoint(), rootLayer);
        Container cell = null;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(pawnView);
        GridView gridView = (GridView) frame.getContentPane().getComponents()[0];

        for (CellView cellView : gridView.getCells()) {
            Rectangle bounds = SwingUtilities.convertRectangle(cellView.getParent(), cellView.getBounds(), rootLayer);
            if (bounds.contains(mousePosition)) {
                cell = cellView;
                break;
            }
        }

        rootLayer.remove(pawnView);
        rootLayer.repaint();

        if (cell != null) {
            pawnView.setLocation(PawnListener.PAWN_PADDING, PawnListener.PAWN_PADDING);
            cell.add(pawnView, JLayeredPane.PALETTE_LAYER);
            cell.revalidate();
            cell.repaint();
            originalParent = cell;
        } else {
            originalParent.add(pawnView);
            pawnView.setLocation(originalLocation);
            originalParent.revalidate();
            originalParent.repaint();
        }
    }
}
