package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Turtle;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {

    public Turtle(Coordinates coordinates, World origin) {
        super(coordinates, 1, 2, origin);
    }

    public Turtle(String data, World origin) {
        super(data, origin);
    }

    @Override
    public final void move() {
        Random random = new Random();
        if (random.nextInt(4) == 0) {
            super.move();
        }
    }

    @Override
    public final void collision(Animal other) {
        if (!this.sameType(other) && other.getStrength() < 5) {
            this.getOrigin().getCommentator().block(other);
        } else {
            super.collision(other);
        }
    }

    @Override
    public final boolean sameType(Animal other) {
        return other instanceof Turtle;
    }

    @Override
    public final GUIField print() {
        class JButtonTurtle extends GUIField {
            public JButtonTurtle() {
                super("T");
                this.setBackground(new Color(124,252,0));
            }
        }

        return new JButtonTurtle();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new Turtle(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Turtle: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " +
                this.getStrength() + " age: " + this.getAge();
    }
}
