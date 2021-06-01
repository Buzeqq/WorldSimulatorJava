package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human.Human;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;

import java.util.HashMap;

public class World {
    private GUIWorld guiWorld;
    private final HashMap<Coordinates, Organism> organisms;
    private final Coordinates bounds;

    public World(final int x, final int y, GUIWorld guiWorld) {
        bounds = new Coordinates(x, y);
        this.organisms = new HashMap<>();
        this.guiWorld = guiWorld;

        this.born(new Human(this.getRandomFreeCoords(), this, this.guiWorld));
        this.printOrganisms();
    }

    public void makeTurn() {
        for (Organism organism : this.organisms.values()) {
            organism.makeAction();
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

    public final void changeOrganisms(final Coordinates newCoords, final Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.organisms.put(newCoords, organism);
    }

    public final void setGUI(GUIWorld guiWorld) {
        this.guiWorld = guiWorld;
    }

    private Coordinates getRandomFreeCoords() {
        Coordinates coords = Coordinates.getRandomCoordinates(bounds.getX(), bounds.getY());
        if (this.getOrganism(coords) != null) return this.getRandomFreeCoords();
        else {
            //System.out.print("coords: " + coords.getX() + ", " + coords.getY() + "\n");
            return coords;
        }
    }

    private void printOrganisms() {
        for (Organism organism : this.organisms.values()) {
            System.out.println(organism);
        }
    }
}
