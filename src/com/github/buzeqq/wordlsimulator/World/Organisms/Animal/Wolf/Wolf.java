package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Wolf;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(final Coordinates coordinates, final World origin) {
        super(coordinates, 5, 9, origin);
    }

    public Wolf(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonWolf extends GUIField {
            public JButtonWolf() {
                super("W", x, y, origin);
                this.setBackground(Color.GRAY);
            }
        }

        return new JButtonWolf();
    }

    @Override
    public final boolean sameType(final Animal other) {
        return other instanceof Wolf;
    }

    @Override
    public final Organism getNew(final Coordinates coordinates) {
        return new Wolf(coordinates, this.getOrigin());
    }

    @Override
    public String toString() {
        return "Wolf: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() + " age: " + this.getAge();
    }
}
