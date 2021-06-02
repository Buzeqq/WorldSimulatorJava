package com.github.buzeqq.wordlsimulator.World.Organisms;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.World;

public abstract class Organism {

    public Organism(final Coordinates coords, final int initiative, final int strength, final World origin) {
        this.initiative = initiative;
        this.strength = strength;
        this.coords = coords;
        this.origin = origin;
        this.age = 1;
        this.dead = false;
    }

    public final int getInitiative() {
        return this.initiative;
    }

    public final int getStrength() {
        return this.strength;
    }

    public final Coordinates getCoords() {
        return this.coords;
    }

    public final World getOrigin() {
        return this.origin;
    }

    public final int getAge() {
        return this.age;
    }

    public final void getOlder() {
        this.age++;
    }

    public final void die() {
        this.dead = true;
        this.getOrigin().die(this);
    }

    public final boolean isDead() {
        return this.dead;
    }

    public final int compareTo(Organism value) {
        if (this.getInitiative() > value.getInitiative()) return -1;
        else if (this.getInitiative() < value.getInitiative()) return 1;
        else {
            return Integer.compare(value.getAge(), this.getAge());
        }
    }

    public abstract void makeAction();

    public void collision(Organism other) {
        if (other.getStrength() >= this.getStrength()) {
            // this dies and other moves in place of this
            this.die();
            other.getOrigin().changeOrganisms(this.getCoords(), other);
            other.getCoords().setCoords(this.getCoords());
        } else {
            // other dies and this stays in place
            other.die();
        }
    }

    public abstract GUIField print();

    public abstract boolean sameType(Organism other);

    private final int initiative;
    private int strength;
    private int age;
    private Coordinates coords;
    private World origin;
    private boolean dead;
}
