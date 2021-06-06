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

    public Human(final Coordinates coordinates, final World origin, final GUIWorld guiWorld) {
        super(coordinates, 4, 5, origin);
        this.guiWorld = guiWorld;
    }

    public Human(final String data, final World origin, final GUIWorld worldPane) {
        super(data, origin);
        this.guiWorld = worldPane;
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonHuman extends GUIField {
            public JButtonHuman() {
                super("H", x, y, origin);
                this.setBackground(Color.PINK);
            }
        }

        return new JButtonHuman();
    }

    @Override
    public final void makeAction() {
        if (this.guiWorld.getActivated()) this.fullBurn();
        this.move();
        this.getOlder();
    }

    @Override
    public final void move() {
        Direction direction = this.guiWorld.getDirection();
        this.guiWorld.setDirection(null);
        if (direction == null || this.validateMove(direction)) return;

        this.checkIfCollides(new Coordinates(this.getCoords(), direction));
    }

    @Override
    public final boolean sameType(final Animal other) {
        return other instanceof Human;
    }

    @Override
    public final String toString() {
        return "Human: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() + " age: " + this.getAge();
    }

    @Override
    public final Organism getNew(final Coordinates coordinates) {
        return new Human(coordinates, this.getOrigin(), this.guiWorld);
    }

    private void fullBurn() {
        this.getOrigin().getCommentator().fullBurn();
        for (Direction direction : Direction.values()) {
            Organism organism = this.getOrigin().getOrganism(new Coordinates(this.getCoords(), direction));
            if (organism != null) organism.die();
        }
        this.guiWorld.resetActivated();
    }
}
