package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUIComments.GUIComments;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Commentator.Commentator;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Antelope.Antelope;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.CyberSheep.CyberSheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox.Fox;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human.Human;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep.Sheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Turtle.Turtle;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Wolf.Wolf;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Dandelion.Dandelion;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Grass.Grass;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Guarana.Guarana;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.SosnowskysHogweed.SosnowskysHogweed;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.WolfBerries.WolfBerries;

import java.util.*;
import java.util.stream.Collectors;

public class World {
    private final HashMap<Coordinates, Organism> organisms;
    private final Coordinates bounds;
    private final Commentator commentator;

    public World(final int x, final int y, GUIWorld guiWorld, GUIComments commentSection) {
        this.bounds = new Coordinates(x, y);
        this.organisms = new HashMap<>();
        this.commentator = new Commentator(commentSection);

        // Human
        this.born(new Human(this.getRandomFreeCoords(), this, guiWorld));

        // Animals
        this.born(new Sheep(this.getRandomFreeCoords(), this));
        this.born(new Sheep(this.getRandomFreeCoords(), this));
        this.born(new Sheep(this.getRandomFreeCoords(), this));
        this.born(new Fox(this.getRandomFreeCoords(), this));
        this.born(new Fox(this.getRandomFreeCoords(), this));
        this.born(new Fox(this.getRandomFreeCoords(), this));
        this.born(new Wolf(this.getRandomFreeCoords(), this));
        this.born(new Wolf(this.getRandomFreeCoords(), this));
        this.born(new Wolf(this.getRandomFreeCoords(), this));
        this.born(new Turtle(this.getRandomFreeCoords(), this));
        this.born(new Turtle(this.getRandomFreeCoords(), this));
        this.born(new Turtle(this.getRandomFreeCoords(), this));
        this.born(new Antelope(this.getRandomFreeCoords(), this));
        this.born(new Antelope(this.getRandomFreeCoords(), this));
        this.born(new Antelope(this.getRandomFreeCoords(), this));
        this.born(new CyberSheep(this.getRandomFreeCoords(), this));
        this.born(new CyberSheep(this.getRandomFreeCoords(), this));
        this.born(new CyberSheep(this.getRandomFreeCoords(), this));

        // Plants
        this.born(new Grass(this.getRandomFreeCoords(), this));
        this.born(new Grass(this.getRandomFreeCoords(), this));
        this.born(new Grass(this.getRandomFreeCoords(), this));
        this.born(new Grass(this.getRandomFreeCoords(), this));
        this.born(new Dandelion(this.getRandomFreeCoords(), this));
        this.born(new Dandelion(this.getRandomFreeCoords(), this));
        this.born(new Dandelion(this.getRandomFreeCoords(), this));
        this.born(new Dandelion(this.getRandomFreeCoords(), this));
        this.born(new Guarana(this.getRandomFreeCoords(), this));
        this.born(new Guarana(this.getRandomFreeCoords(), this));
        this.born(new Guarana(this.getRandomFreeCoords(), this));
        this.born(new Guarana(this.getRandomFreeCoords(), this));
        this.born(new WolfBerries(this.getRandomFreeCoords(), this));
        this.born(new WolfBerries(this.getRandomFreeCoords(), this));
        this.born(new WolfBerries(this.getRandomFreeCoords(), this));
        this.born(new WolfBerries(this.getRandomFreeCoords(), this));
        this.born(new SosnowskysHogweed(this.getRandomFreeCoords(), this));
        this.born(new SosnowskysHogweed(this.getRandomFreeCoords(), this));
        this.born(new SosnowskysHogweed(this.getRandomFreeCoords(), this));
        this.born(new SosnowskysHogweed(this.getRandomFreeCoords(), this));

        this.printOrganisms();
    }

    public void makeTurn() {

        List<Map.Entry<Coordinates, Organism>> list = new LinkedList<>(organisms.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for (Map.Entry<Coordinates, Organism> organism : list) {
            if (organism.getValue().isDead()) continue;
            organism.getValue().makeAction();
        }

        this.commentator.updateCommentSection();
        this.printOrganisms();
    }

    public final Coordinates getBounds() {
        return bounds;
    }

    public final Organism getOrganism(Coordinates coords) {
        return this.organisms.get(coords);
    }

    public final Commentator getCommentator() {
        return this.commentator;
    }

    public final void born(Organism organism) {
        this.organisms.put(organism.getCoords(), organism);
        this.commentator.born(organism);
    }

    public final void die(Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.commentator.die(organism);
    }

    public final void changeOrganisms(final Coordinates newCoords, final Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.organisms.put(newCoords, organism);
    }

    private Coordinates getRandomFreeCoords() {
        Coordinates coords = Coordinates.getRandomCoordinates(bounds.getX(), bounds.getY());
        if (this.getOrganism(coords) != null) return this.getRandomFreeCoords();
        else return coords;
    }

    public final boolean validateCoords(Coordinates coords) {
        if (coords.getX() >= this.bounds.getX() || coords.getX() < 0) return true;
        return coords.getY() >= this.bounds.getY() || coords.getY() < 0;
    }

    private void printOrganisms() {
        System.out.println("-----------------------------");
        for (Organism organism : this.organisms.values()) {
            System.out.println(organism);
        }
        System.out.println("-----------------------------");
    }

    public final Coordinates getClosestHogweed(Coordinates coords) {
       List<Organism> potentialTargets = this.organisms.values().stream()
               .filter(organism -> organism instanceof SosnowskysHogweed)
               .collect(Collectors.toList());

       Organism minOrganism = null;
       int min = Integer.MAX_VALUE;
       for (Organism organism : potentialTargets) {
           System.out.println(organism);
           final int distance = Coordinates.calculateDistance(organism.getCoords(), coords);
           if (distance < min) {
               min = distance;
               minOrganism = organism;
           }
       }

       return minOrganism == null ? null : minOrganism.getCoords();
    }
}
