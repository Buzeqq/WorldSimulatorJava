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

        this.checkIfCollides(new Coordinates(this.getCoords(), direction));
    }

    @Override
    public void makeAction() {
        this.move();
        this.getOlder();
    }

    @Override
    public void collision(Animal other) {
        if (this.sameType(other)) {
            this.getOrigin().getCommentator().breed(this, other);
            this.breed(other);
        } else {
            super.collision(other);
        }
    }

    protected void breed(Animal other) {
        Coordinates coordsNextTo = this.getFreeCoordsNextTo();
        if (coordsNextTo == null) coordsNextTo = other.getFreeCoordsNextTo();
        if (coordsNextTo == null) return;
        this.getOrigin().born(this.getNew(coordsNextTo));
    }

    protected boolean validateMove(Direction direction) {
        return this.validateMove(this.getCoords(), direction);
    }

    protected boolean validateMove( Coordinates coordinates, Direction direction) {
        Coordinates bounds = this.getOrigin().getBounds();

        if ((coordinates.getX() == 0 && direction == Direction.DIRECTION_LEFT) ||
                coordinates.getX() == bounds.getX() - 1 && direction == Direction.DIRECTION_RIGHT) return true;

        return ((coordinates.getY() == 0 && direction == Direction.DIRECTION_UP) ||
                (coordinates.getY() == bounds.getY() - 1 && direction == Direction.DIRECTION_DOWN));
    }

    public abstract boolean sameType(Animal other);

    public abstract Organism getNew(Coordinates coords);

    protected void checkIfCollides(Coordinates newCords) {
        if (this.getOrigin().getOrganism(newCords) == null) {
            this.getOrigin().getCommentator().move(this, newCords);
            this.getOrigin().changeOrganisms(newCords, this);
            this.getCoords().setCoords(newCords);
        } else {
            this.getOrigin().getOrganism(newCords).collision(this);
        }
    }

    public final void buff(final int buff) {
        this.getOrigin().getCommentator().buff(this);
        this.setStrength(this.getStrength() + buff);
    }
}
