package org.example.view.listeners;

import org.example.view.CellView;
import org.example.view.GridView;
import org.example.view.PawnView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PawnListener extends MouseAdapter {

    private final PawnView target;
    private Point mouseOffset;
    private JLayeredPane rootLayer;
    private Container originalParent;
    private Point originalLocation;

    public PawnListener(PawnView target) {
        this.target = target;
        target.addMouseListener(this);
        target.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseOffset = e.getPoint();
        this.originalParent = this.target.getParent();
        this.originalLocation = this.target.getLocation(); // sauvegarde position initiale

        this.rootLayer = (JLayeredPane) SwingUtilities.getRootPane(this.target).getLayeredPane();
        Point p = SwingUtilities.convertPoint(this.originalParent, this.originalLocation, this.rootLayer);
        this.originalParent.remove(this.target);
        this.rootLayer.add(this.target, JLayeredPane.DRAG_LAYER);
        this.target.setLocation(p);
        this.rootLayer.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(this.target, e.getPoint(), this.rootLayer);
        this.target.setLocation(mousePosition.x - this.mouseOffset.x, mousePosition.y - this.mouseOffset.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point mousePosition = SwingUtilities.convertPoint(target, e.getPoint(), rootLayer);
        Container cell = null;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(target);
        GridView gridView = (GridView) ((Container) frame.getContentPane().getComponents()[0]).getComponents()[0];

        for (CellView cellView : gridView.getCells()) {
            Rectangle bounds = SwingUtilities.convertRectangle(cellView.getParent(), cellView.getBounds(), rootLayer);
            if (bounds.contains(mousePosition)) {
                cell = cellView;
                break;
            }
        }

        rootLayer.remove(target);
        rootLayer.repaint();

        if (cell != null) {
            target.setLocation(5, 5);
            cell.add(target, JLayeredPane.PALETTE_LAYER);
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
