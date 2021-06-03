package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Antelope;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {

    public Antelope(Coordinates coordinates, World origin) {
        super(coordinates, 4, 4, origin);
    }

    @Override
    public final void move() {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(4)];

        // while not possible to move by one, choose new direction
        // if possible check if move by two position is possible, if not move by one
        // if possible move by 2

        while (this.validateMove(direction)) {
            direction = Direction.values()[random.nextInt(4)];
        }

        Coordinates newCoordinates = new Coordinates(this.getCoords(), direction);
        if (!this.validateMove(newCoordinates, direction)) {
            // move by two
            newCoordinates = new Coordinates(newCoordinates, direction);
        }
        this.checkIfCollides(newCoordinates);
    }

    @Override
    public final void collision(Animal other) {
        Random chance = new Random();
        if (chance.nextInt(2) == 0 && !this.sameType(other)) {
            Coordinates coordinates = this.getFreeCoordsNextTo();
            if (coordinates == null) super.collision(other);
            else {
                this.getOrigin().getCommentator().escape(other);
                this.getOrigin().getCommentator().move(this, coordinates);
                other.getOrigin().getCommentator().move(other, this.getCoords());

                this.getOrigin().changeOrganisms(coordinates, this);
                other.getOrigin().changeOrganisms(new Coordinates(this.getCoords().getX(), this.getCoords().getY()), other);

                other.getCoords().setCoords(new Coordinates(this.getCoords().getX(), this.getCoords().getY()));
                this.getCoords().setCoords(coordinates);
            }
        } else {
            super.collision(other);
        }
    }

    @Override
    public final boolean sameType(Animal other) {
        return other instanceof Antelope;
    }

    @Override
    public final GUIField print() {
        class JButtonAntelope extends GUIField {
            public JButtonAntelope() {
                super("A");
                this.setBackground(new Color(0xf4c587));
            }
        }

        return new JButtonAntelope();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new Antelope(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Antelope: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " +
                this.getStrength() + " age: " + this.getAge();
    }


}
