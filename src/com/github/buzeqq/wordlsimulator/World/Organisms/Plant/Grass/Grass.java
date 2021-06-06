package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Grass;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Grass extends Plant {

    public Grass(final Coordinates coordinates, final World origin) {
        super(coordinates, 0, origin);
    }

    public Grass(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public GUIField print(final int x, final int y, final World origin) {
        class JButtonGrass extends GUIField {
            public JButtonGrass() {
                super("G", x, y, origin);
                this.setBackground(new Color(34, 139, 34));
            }
        }

        return new JButtonGrass();
    }

    @Override
    public Organism getNew(final Coordinates coords) {
        return new Grass(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Grass: " + this.getCoords() + " initiative: " + this.getInitiative() +
                " strength: " + this.getStrength() + " age: " + this.getAge();
    }
}
