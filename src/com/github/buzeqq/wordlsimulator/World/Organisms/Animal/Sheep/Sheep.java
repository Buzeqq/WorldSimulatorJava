package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Sheep extends Animal {

    public Sheep(Coordinates coords, World origin) {
        super(coords, 4, 4, origin);
    }

    @Override
    public final GUIField print() {
        class JButtonSheep extends GUIField {
            public JButtonSheep() {
                super("S");
                this.setBackground(Color.WHITE);
            }
        }

        return new JButtonSheep();
    }

    @Override
    public final String toString() {
        return "Sheep: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength();
    }

    @Override
    public final boolean sameType(Organism other) {
        return other instanceof Sheep;
    }
}
