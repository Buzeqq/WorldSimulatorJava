package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.WolfBerries;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class WolfBerries extends Plant {

    public WolfBerries(Coordinates coordinates, World origin) {
        super(coordinates, 99, origin);
    }

    @Override
    public final GUIField print() {
        class JButtonWolfBerries extends GUIField {
            public JButtonWolfBerries() {
                super("W");
                this.setBackground(Color.BLACK);
                this.setForeground(Color.WHITE);
            }
        }

        return new JButtonWolfBerries();
    }

    @Override
    public final void collision(Animal other) {
        this.getOrigin().getCommentator().poisoned(other);
        this.die();
        other.die();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new WolfBerries(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Wolf berries: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }
}
