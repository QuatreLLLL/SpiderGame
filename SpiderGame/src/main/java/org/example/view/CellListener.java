package org.example.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellListener extends MouseAdapter {

    private CellView cellView;

    public CellListener(CellView cellView) {
        this.cellView =  cellView;
    }

    public boolean isMouseEntered(MouseEvent e) {
        return e.getComponent().contains(e.getPoint());
    }

}
