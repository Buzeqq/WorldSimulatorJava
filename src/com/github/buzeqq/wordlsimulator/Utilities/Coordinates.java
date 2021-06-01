package com.github.buzeqq.wordlsimulator.Utilities;

import java.util.Random;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(final int x, final int y, final Direction direction) {
        switch (direction) {
            case DIRECTION_UP -> {
                this.x = x;
                this.y = y - 1;
            }

            case DIRECTION_RIGHT -> {
                this.x = x + 1;
                this.y = y;
            }

            case DIRECTION_DOWN -> {
                this.x = x;
                this.y = y + 1;
            }

            case DIRECTION_LEFT -> {
                this.x = x - 1;
                this.y = y;
            }
        }
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

    public final void setCoords(Coordinates coords) {
        this.x = coords.x;
        this.y = coords.y;
    }

    static public Coordinates getRandomCoordinates(final int upperboundX, final int upperboundY) {
        Random random = new Random();
        return new Coordinates(random.nextInt(upperboundX), random.nextInt(upperboundY));
    }

    @Override
    public final String toString() {
        return "Coordinates (" + this.x + ", " + this.y + ")";
    }

    @Override
    public final int hashCode() {
        return 31 * Integer.hashCode(this.x) + Integer.hashCode(this.y) + 19;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            Coordinates coordinates = (Coordinates)obj;
            return this.x == coordinates.x && this.y == coordinates.y;
        } else return false;
    }
}
