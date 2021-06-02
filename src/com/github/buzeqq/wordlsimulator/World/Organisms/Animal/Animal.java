package com.github.buzeqq.wordlsimulator.World.Organisms.Animal;

import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import java.util.Random;

public abstract class Animal extends Organism {
    public Animal(final Coordinates coords, final int initiative, final int strength, final World origin) {
        super(coords, initiative, strength, origin);
    }

    public void move() {
        Random random = new Random();
        Direction direction = Direction.values()[random.nextInt(4)];

        while (this.validateMove(direction)) {
            direction = Direction.values()[random.nextInt(4)];
        }

        this.checkIfCollides(new Coordinates(this.getCoords().getX(), this.getCoords().getY(), direction));
    }

    @Override
    public void makeAction() {
        this.move();
        this.getOlder();
    }

    @Override
    public void collision(Organism other) {
        if (this.sameType(other)) {
            // breed
            return;
        } else {
            super.collision(other);
        }
    }

    protected boolean validateMove(Direction direction) {
        Coordinates bounds = this.getOrigin().getBounds();

        if ((this.getCoords().getX() == 0 && direction == Direction.DIRECTION_LEFT) ||
                (this.getCoords().getX() == bounds.getX() - 1 && direction == Direction.DIRECTION_RIGHT)) return true;

        return (this.getCoords().getY() == 0 && direction == Direction.DIRECTION_UP) ||
                (this.getCoords().getY() == bounds.getY() - 1 && direction == Direction.DIRECTION_DOWN);
    }

    public abstract boolean sameType(Organism other);

    protected void checkIfCollides(Coordinates newCords) {
        if (this.getOrigin().getOrganism(newCords) == null) {
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        } else {
            this.getOrigin().getOrganism(newCords).collision(this);
        }
    }
}
