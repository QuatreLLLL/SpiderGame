package org.example.controller;

import org.example.model.Pawn;
import org.example.view.CellView;
import org.example.view.PawnView;

import java.awt.Point;

public class PawnController {

    private final Pawn pawn;

    private final PawnView pawnView;

    public PawnController(Pawn pawn, PawnView pawnView) {
        this.pawn = pawn;
        this.pawnView = pawnView;

        this.pawnView.setOnMousePressed(this::handleMousePressed);
        this.pawnView.setOnMouseReleased(this::handleMouseReleased);
        this.pawnView.setOnMouseDragged(this::handleMouseDragged);
    }

    public PawnView getPawnView() {
        return this.pawnView;
    }

    public void handleMousePressed(Point point) {
        this.pawnView.setMouseOffset(point);
        this.pawnView.setOriginalParent(this.pawnView.getParent());
        this.pawnView.setOriginalLocation(this.pawnView.getLocation());

        this.pawnView.startDrag();
    }

    public void handleMouseReleased(Point point) {
        Point mousePosition = this.pawnView.convertPointToRootLayer(point);
        CellView cell = this.pawnView.findTargetCell(mousePosition);

        this.pawnView.getRootLayer().remove(this.pawnView);
        this.pawnView.getRootLayer().repaint();

        if (cell != null) {
            this.pawnView.snapToCell(cell);
        } else {
            this.pawnView.cancelMove();
        }
    }

    public void handleMouseDragged(Point point) {
        Point mousePosition = this.pawnView.convertPointToRootLayer(point);
        this.pawnView.setLocation(mousePosition.x - this.pawnView.getMouseOffset().x, mousePosition.y - this.pawnView.getMouseOffset().y);
    }
}
