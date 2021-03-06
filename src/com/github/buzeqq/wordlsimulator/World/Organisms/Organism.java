package com.github.buzeqq.wordlsimulator.World.Organisms;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
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

    public Organism(final String data, final World origin) {
        this.coords = Coordinates.parseCoordinates(data
                .substring(data.lastIndexOf("Coordinates (") + 13, data.indexOf(")")));

        this.initiative = Integer.parseInt(data.substring(data
                .indexOf("initiative: ") + 12, data.indexOf(" strength:")));

        this.strength = Integer.parseInt(data.substring(data
                .indexOf("strength: ") + 10, data.indexOf(" age:")));

        this.age = Integer.parseInt(data.substring(data.lastIndexOf("age: ") + 5));
        this.origin = origin;
        this.dead = false;
    }

    public final int getInitiative() {
        return this.initiative;
    }

    public final int getStrength() {
        return this.strength;
    }

    public final void setStrength(final int strength) {
        this.strength = strength;
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

    public void collision(Animal other) {
        this.getOrigin().getCommentator().confrontation(this, other);
        if (other.getStrength() >= this.getStrength()) {
            this.die();
            this.getOrigin().getCommentator().move(other, this.getCoords());
            other.getOrigin().changeOrganisms(this.getCoords(), other);
            other.getCoords().setCoords(this.getCoords());
        } else {
            other.die();
        }
    }

    public abstract GUIField print(final int x, final int y, final World origin);

    public abstract Organism getNew(final Coordinates coords);

    protected Coordinates getFreeCoordsNextTo() {
        for (Direction direction : Direction.values()) {
            Coordinates coords = new Coordinates(this.getCoords(), direction);
            if (this.getOrigin().validateCoords(coords)) continue;
            if (this.getOrigin().getOrganism(coords) == null) return coords;
        }
        return null;
    }

    private final int initiative;
    private int strength;
    private int age;
    private final Coordinates coords;
    private final World origin;
    private boolean dead;

}
