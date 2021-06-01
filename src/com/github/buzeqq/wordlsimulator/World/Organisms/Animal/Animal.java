package com.github.buzeqq.wordlsimulator.World.Organisms.Animal;

import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
import java.util.Random;

public abstract class Animal extends Organism {
    public Animal(final Coordinates coords, final int initiative, final int strength, final World origin) {
        super(coords, initiative, strength, origin);
    }

    public void move() {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(3)];

        while (!this.validateMove(direction)) {
            direction = Direction.values()[random.nextInt(3)];
        }

        Coordinates newCords = new Coordinates(this.getCoords().getX(), this.getCoords().getY(), direction);
        if (this.getOrigin().getOrganism(newCords) != null) {
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        }
        // else perform collision

        this.getOlder();
    }

    @Override
    public void makeAction() {
        this.move();
    }

    @Override
    public void collision() {
    }

    protected boolean validateMove(Direction direction) {
        Coordinates bounds = this.getOrigin().getBounds();

        if ((this.getCoords().getX() == 0 && direction == Direction.DIRECTION_LEFT) ||
                (this.getCoords().getX() == bounds.getX() - 1 && direction == Direction.DIRECTION_RIGHT)) return false;

        return (this.getCoords().getY() != 0 || direction != Direction.DIRECTION_UP) &&
                (this.getCoords().getY() != bounds.getY() - 1 || direction != Direction.DIRECTION_DOWN);
    }
}
