package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Dandelion;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;
import java.util.Random;

public class Dandelion extends Plant {

    public Dandelion(final Coordinates coordinates, final World origin) {
        super(coordinates, 0, origin);
    }

    @Override
    public final GUIField print() {
        class JButtonDandelion extends GUIField {
            public JButtonDandelion() {
                super("D");
                this.setBackground(Color.YELLOW);
            }
        }

        return new JButtonDandelion();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new Dandelion(coords, this.getOrigin());
    }

    @Override
    public final void makeAction() {
        int chances = 3;
        Random chance = new Random();
        while (chances > 0) {
            if (chance.nextInt(4) == 0) {
                this.spread();
                break;
            }

            chances--;
        }
    }

    @Override
    public final String toString() {
        return "Dandelion: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }
}
