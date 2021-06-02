package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human.Human;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep.Sheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Wolf.Wolf;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;

import java.util.*;

public class World {
    private final HashMap<Coordinates, Organism> organisms;
    private final Coordinates bounds;

    public World(final int x, final int y, GUIWorld guiWorld) {
        bounds = new Coordinates(x, y);
        this.organisms = new HashMap<>();

        // Human
        // this.born(new Human(this.getRandomFreeCoords(), this, guiWorld));

        // Animals
        this.born(new Sheep(this.getRandomFreeCoords(), this));
        this.born(new Sheep(this.getRandomFreeCoords(), this));
        this.born(new Sheep(this.getRandomFreeCoords(), this));

        this.born(new Wolf(this.getRandomFreeCoords(), this));
        this.born(new Wolf(this.getRandomFreeCoords(), this));
        this.born(new Wolf(this.getRandomFreeCoords(), this));

        this.printOrganisms();
    }

    public void makeTurn() {

        List<Map.Entry<Coordinates, Organism>> list = new LinkedList<>(organisms.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for (Map.Entry<Coordinates, Organism> organism : list) {
            if (organism.getValue().isDead()) continue; // not sure if dead organisms are really dead, because we change the hashmap not the list
            organism.getValue().makeAction();
        }

        this.printOrganisms();
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

    public final void die(Organism organism) {
        this.organisms.remove(organism.getCoords());
    }

    public final void changeOrganisms(final Coordinates newCoords, final Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.organisms.put(newCoords, organism);
    }

    private Coordinates getRandomFreeCoords() {
        Coordinates coords = Coordinates.getRandomCoordinates(bounds.getX(), bounds.getY());
        if (this.getOrganism(coords) != null) return this.getRandomFreeCoords();
        else {
            //System.out.print("coords: " + coords.getX() + ", " + coords.getY() + "\n");
            return coords;
        }
    }

    public final boolean validateCoords(Coordinates coords) {
        if (coords.getX() >= this.bounds.getX() || coords.getX() < 0) return false;
        return coords.getY() < this.bounds.getY() && coords.getY() >= 0;
    }

    private void printOrganisms() {
        System.out.println("-----------------------------");
        for (Organism organism : this.organisms.values()) {
            System.out.println(organism);
        }
        System.out.println("-----------------------------");
    }
}
