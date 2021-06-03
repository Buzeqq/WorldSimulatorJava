package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.CyberSheep;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;
import java.util.Random;

public class CyberSheep extends Animal {

    public CyberSheep(Coordinates coordinates, World origin) {
        super(coordinates, 11, 4, origin);
    }

    @Override
    public final void move() {
        Coordinates targetLocked = this.getOrigin().getClosestHogweed(this.getCoords());
        if (targetLocked == null) super.move();
        else {
            this.getOrigin().getCommentator().targetLocked(targetLocked);
            Direction direction = this.determineDirection(targetLocked);
            this.checkIfCollides(new Coordinates(this.getCoords(), direction));
        }
    }

    @Override
    public final boolean sameType(Animal other) {
        return other instanceof CyberSheep;
    }

    @Override
    public final GUIField print() {
        class JButtonCyberSheep extends GUIField {
            public JButtonCyberSheep() {
                super("CS");
                this.setBackground(Color.MAGENTA);
            }
        }

        return new JButtonCyberSheep();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new CyberSheep(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Cyber sheep: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " +
                this.getStrength() + " age: " + this.getAge();
    }

    private Direction determineDirection(Coordinates target) {

        if (this.getCoords().getX() == target.getX()) {
            // move only within Y axis
            if (this.getCoords().getY() < target.getY()) return Direction.DIRECTION_DOWN;
            else return Direction.DIRECTION_UP;
        }

        if (this.getCoords().getY() == target.getY()) {
            // move only within X axis
            if (this.getCoords().getX() < target.getX()) return Direction.DIRECTION_RIGHT;
            else return Direction.DIRECTION_LEFT;
        }

        // in other case choose axis randomly
        Random xOrY = new Random();
        if (xOrY.nextInt(2) == 0) {
            // move within Y axis
            if (this.getCoords().getY() < target.getY()) return Direction.DIRECTION_DOWN;
            else return Direction.DIRECTION_UP;
        } else {
            // move within X axis
            if (this.getCoords().getX() < target.getX()) return Direction.DIRECTION_RIGHT;
            else return Direction.DIRECTION_LEFT;
        }
    }
}
