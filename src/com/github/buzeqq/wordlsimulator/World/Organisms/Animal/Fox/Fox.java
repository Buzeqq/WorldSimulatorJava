package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Fox extends Animal {

    public Fox(Coordinates coordinates, World origin) {
        super(coordinates, 7, 3, origin);
    }

    @Override
    protected final void checkIfCollides(Coordinates newCords) {
        if (this.getOrigin().getOrganism(newCords) == null) {
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        } else {
            if (this.getOrigin().getOrganism(newCords).getStrength() <= this.getStrength()) {
                this.getOrigin().getOrganism(newCords).collision(this);
            } else {
                this.getOrigin().getCommentator().foxSmell(this);
                this.getOrigin().changeOrganisms(this.getFreeCoordsNextTo(),this);
            }
        }
    }

    @Override
    public boolean sameType(Animal other) {
        return other instanceof Fox;
    }

    @Override
    public final GUIField print() {
        class JButtonFox extends GUIField {
            public JButtonFox() {
                super("F");
                this.setBackground(new Color(255,140,0));
            }
        }

        return new JButtonFox();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new Fox(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Fox: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }
}
