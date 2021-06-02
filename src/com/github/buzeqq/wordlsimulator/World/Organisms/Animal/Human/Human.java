package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Human extends Animal {

    private final GUIWorld guiWorld;

    public Human(Coordinates coords, World origin, GUIWorld guiWorld) {
        super(coords, 4, 5, origin);
        this.guiWorld = guiWorld;
    }

    @Override
    public final GUIField print() {
        class JButtonHuman extends GUIField {
            public JButtonHuman() {
                super("H");
                this.setBackground(Color.PINK);
            }
        }

        return new JButtonHuman();
    }

    @Override
    public final void move() {
        Direction direction = this.guiWorld.getDirection();
        this.guiWorld.setDirection(null);
        if (direction == null || this.validateMove(direction)) return;

        this.checkIfCollides(new Coordinates(this.getCoords(), direction));
    }

    @Override
    public final boolean sameType(Organism other) {
        return other instanceof Human;
    }

    @Override
    public final String toString() {
        return "Human: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() + " age: " + this.getAge();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new Human(coords, this.getOrigin(), this.guiWorld);
    }
}
