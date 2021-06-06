package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Guarana;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Guarana extends Plant {

    public Guarana(final Coordinates coordinates, final World origin) {
        super(coordinates, 0, origin);
    }

    public Guarana(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonGuarana extends GUIField {
            public JButtonGuarana() {
                super("G", x, y, origin);
                this.setBackground(new Color(255,99,71));
            }
        }

        return new JButtonGuarana();
    }

    @Override
    public final Organism getNew(final Coordinates coords) {
        return new Guarana(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Guarana: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }

    @Override
    public final void collision(Animal other) {
        this.die();
        other.buff(3);
        other.getOrigin().changeOrganisms(this.getCoords(), other);
        other.getCoords().setCoords(this.getCoords());
    }
}
