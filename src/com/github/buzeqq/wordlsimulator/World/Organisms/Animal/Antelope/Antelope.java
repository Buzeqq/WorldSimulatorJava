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

    public Antelope(final Coordinates coordinates, final World origin) {
        super(coordinates, 4, 4, origin);
    }
    public Antelope(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public final void move() {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(4)];

        while (this.validateMove(direction)) {
            direction = Direction.values()[random.nextInt(4)];
        }

        Coordinates newCoordinates = new Coordinates(this.getCoords(), direction);
        if (!this.validateMove(newCoordinates, direction)) {
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
    public final boolean sameType(final Animal other) {
        return other instanceof Antelope;
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonAntelope extends GUIField {
            public JButtonAntelope() {
                super("A", x, y, origin);
                this.setBackground(new Color(0xf4c587));
            }
        }

        return new JButtonAntelope();
    }

    @Override
    public final Organism getNew(final Coordinates coords) {
        return new Antelope(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Antelope: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " +
                this.getStrength() + " age: " + this.getAge();
    }
}
