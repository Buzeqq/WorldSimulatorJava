package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Sheep extends Animal {

    public Sheep(final Coordinates coordinates, final World origin) {
        super(coordinates, 4, 4, origin);
    }

    public Sheep(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonSheep extends GUIField {
            public JButtonSheep() {
                super("S", x, y, origin);
                this.setBackground(Color.WHITE);
            }
        }

        return new JButtonSheep();
    }

    @Override
    public final String toString() {
        return "Sheep: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() + " age: " + this.getAge();
    }

    @Override
    public final boolean sameType(final Animal other) {
        return other instanceof Sheep;
    }

    @Override
    public Organism getNew(final Coordinates coordinates) {
        return new Sheep(coordinates, this.getOrigin());
    }
}
