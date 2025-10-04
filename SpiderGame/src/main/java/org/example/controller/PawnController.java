package org.example.controller;

import org.example.model.Pawn;
import org.example.view.CellView;
import org.example.view.PawnView;

import java.awt.Point;
import java.util.function.Consumer;

public class PawnController {

    private final Pawn pawn;

    private final PawnView pawnView;

    private Consumer<PawnController> legalMovesUpdater;
    private Consumer<PawnController> pawnPlacementHandler;

    public PawnController(Pawn pawn, PawnView pawnView) {
        this.pawn = pawn;
        this.pawnView = pawnView;

        this.pawnView.setOnMousePressed(this::handleMousePressed);
        this.pawnView.setOnMouseReleased(this::handleMouseReleased);
        this.pawnView.setOnMouseDragged(this::handleMouseDragged);
    }

    public Pawn getPawn() {
        return this.pawn;
    }

    public PawnView getPawnView() {
        return this.pawnView;
    }

    public void setLegalMovesUpdater(Consumer<PawnController> legalMovesUpdater) {
        this.legalMovesUpdater = legalMovesUpdater;
    }

    public void setPawnPlacementHandler(Consumer<PawnController> pawnPlacementHandler) {
        this.pawnPlacementHandler = pawnPlacementHandler;
    }

    public void handleMousePressed(Point point) {
        this.pawnView.setMouseOffset(point);
        this.pawnView.setCurrentParent(this.pawnView.getParent());
        this.pawnView.setCurrentLocation(this.pawnView.getLocation());

        this.pawnView.startDrag();
        this.legalMovesUpdater.accept(this);
    }

    public void handleMouseReleased(Point point) {
        Point mousePosition = this.pawnView.convertPointToRootLayer(point);
        CellView cell = this.pawnView.findTargetCell(mousePosition);

        if (cell != null && cell.getStatus()) {
            this.pawnView.snapToCell(cell);
            this.pawnPlacementHandler.accept(this);
        } else {
            this.pawnView.cancelMove();
            this.legalMovesUpdater.accept(null);
        }
    }

    public void handleMouseDragged(Point point) {
        Point mousePosition = this.pawnView.convertPointToRootLayer(point);
        this.pawnView.setLocation(mousePosition.x - this.pawnView.getMouseOffset().x, mousePosition.y - this.pawnView.getMouseOffset().y);
    }
}
