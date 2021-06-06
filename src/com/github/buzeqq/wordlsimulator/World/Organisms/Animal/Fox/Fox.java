package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class Fox extends Animal {

    public Fox(final Coordinates coordinates, final World origin) {
        super(coordinates, 7, 3, origin);
    }

    public Fox(final String data, final World origin) {
        super(data, origin);
    }

    @Override
    public boolean sameType(final Animal other) {
        return other instanceof Fox;
    }

    @Override
    public final GUIField print(final int x, final int y, final World origin) {
        class JButtonFox extends GUIField {
            public JButtonFox() {
                super("F", x, y, origin);
                this.setBackground(new Color(255,140,0));
            }
        }

        return new JButtonFox();
    }

    @Override
    public final Organism getNew(final Coordinates coordinates) {
        return new Fox(coordinates, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Fox: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " + this.getStrength() +
                " age: " + this.getAge();
    }

    @Override
    protected final void checkIfCollides(final Coordinates newCords) {
        if (this.getOrigin().getOrganism(newCords) == null) {
            this.getOrigin().getCommentator().move(this, newCords);
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        } else {
            if (this.getOrigin().getOrganism(newCords).getStrength() <= this.getStrength()) {
                this.getOrigin().getOrganism(newCords).collision(this);
            } else {
                this.getOrigin().getCommentator().foxSmell(this);
                Coordinates coordinates = this.getFreeCoordsNextTo();
                if (coordinates == null) return;
                this.getOrigin().changeOrganisms(coordinates,this);
                this.getCoords().setCoords(coordinates);
            }
        }
    }
}
