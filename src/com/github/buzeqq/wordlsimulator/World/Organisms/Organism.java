package com.github.buzeqq.wordlsimulator.World.Organisms;

import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.World;

public abstract class Organism {

    public Organism(final Coordinates coords, final int initiative, final int strength, final World origin) {
        this.initiative = initiative;
        this.strength = strength;
        this.coords = coords;
        this.origin = origin;
        this.age = 1;
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

    public abstract void makeAction();

    public abstract void collision();

    private final int initiative;
    private int strength;
    private int age;
    private Coordinates coords;
    private World origin;
}
