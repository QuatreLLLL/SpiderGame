package org.example.view;

public enum GameColor {

    RED(236, 112, 99),
    BLUE(93, 173, 226),
    LIGHT_GRAY(214, 219, 223),
    DARK_GRAY(174, 182, 191),
    LIGHT_ASPAHLT(52, 73, 94),
    DARK_ASPHALT(46, 64, 83);

    private final int r, g, b;

    GameColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public java.awt.Color get() {
        return new java.awt.Color(this.r, this.g, this.b);
    }
}
