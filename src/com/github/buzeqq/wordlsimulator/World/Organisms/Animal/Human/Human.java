package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
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
        Direction direction = guiWorld.getDirection();
        if (direction == null || !this.validateMove(direction)) return;

        Coordinates newCords = new Coordinates(this.getCoords().getX(), this.getCoords().getY(), direction);
        if (this.getOrigin().getOrganism(newCords) == null) {
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        }
        // else perform collision

        this.getOlder();
    }

    @Override
    public final String toString() {
        return "Human: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength();
    }
}
