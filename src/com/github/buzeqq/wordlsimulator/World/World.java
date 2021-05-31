package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human.Human;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;

import java.util.HashMap;

public class World {
    private HashMap<Coordinates, Organism> organisms;
    private final Coordinates bounds;

    public World(final int x, final int y) {
        bounds = new Coordinates(x, y);
        this.organisms = new HashMap<>();

        this.born(new Human(this.getRandomFreeCoords(), this));
        this.printOrganisms();
    }

    public void makeTurn() {

    }

    public final Coordinates getBounds() {
        return bounds;
    }

    public final Organism getOrganism(Coordinates coords) {
        return this.organisms.get(coords);
    }

    public final void born(Organism organism) {
        this.organisms.put(organism.getCoords(), organism);
    }

    private Coordinates getRandomFreeCoords() {
        Coordinates coords = Coordinates.getRandomCoordinates(bounds.getX(), bounds.getY());
        if (this.getOrganism(coords) != null) return this.getRandomFreeCoords();
        else {
            System.out.print("coords: " + coords.getX() + ", " + coords.getY());
            return coords;
        }
    }

    private void printOrganisms() {
        for (Organism organism : this.organisms.values()) {
            System.out.print(organism);
        }
    }
}
