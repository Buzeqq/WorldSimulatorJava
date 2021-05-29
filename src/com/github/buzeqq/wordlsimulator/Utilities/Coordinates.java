package com.github.buzeqq.wordlsimulator.Utilities;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final void setX(final int x) {
        this.x = x;
    }

    public final void setY(final int y) {
        this.y = y;
    }

    public final Coordinates getCoords() {
        return new Coordinates(this.x, this.y);
    }

    public final void setCoords(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
