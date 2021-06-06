package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.WolfBerries;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class WolfBerries extends Plant {

    public WolfBerries(final Coordinates coordinates, final World origin) {
        super(coordinates, 99, origin);
    }

    public WolfBerries(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonWolfBerries extends GUIField {
            public JButtonWolfBerries() {
                super("W", x, y, origin);
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
    public final Organism getNew(final Coordinates coords) {
        return new WolfBerries(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Wolf berries: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }
}
