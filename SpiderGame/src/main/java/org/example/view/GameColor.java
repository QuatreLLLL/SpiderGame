package org.example.view;

import java.awt.*;

public enum GameColor {

    RED(236, 112, 99),
    BLUE(93, 173, 226),
    GREEN(40, 180, 99),
    LIGHT_GRAY(214, 219, 223),
    LIGHT_ASPHALT(52, 73, 94),
    DARK_ASPHALT(46, 64, 83);

    private final int r, g, b;

    GameColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color get() {
        return new Color(this.r, this.g, this.b);
    }
}
