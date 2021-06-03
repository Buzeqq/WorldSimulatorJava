package com.github.buzeqq.wordlsimulator.World.Organisms.Plant;

import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.util.Random;

public abstract class Plant extends Organism {

    public Plant(Coordinates coords, int strength, World origin) {
        super(coords, 0, strength, origin);
    }

    @Override
    public void makeAction() {
        Random chance = new Random();
        if (chance.nextInt(4) == 0) this.spread();
        this.getOlder();
    }

    @Override
    public final Coordinates getFreeCoordsNextTo() {
        Random random = new Random();
        Direction randomDirection = Direction.values()[random.nextInt(4)];

        Coordinates coords = new Coordinates(this.getCoords(), randomDirection);
        if (this.getOrigin().validateCoords(coords)) return null;
        if (this.getOrigin().getOrganism(coords) == null) {
            return coords;
        } else return null;
    }

    protected void spread() {
        Coordinates coords = this.getFreeCoordsNextTo();
        if (coords == null) return;

        this.getOrigin().getCommentator().spread(this);
        this.getOrigin().born(this.getNew(coords));
    }

}
