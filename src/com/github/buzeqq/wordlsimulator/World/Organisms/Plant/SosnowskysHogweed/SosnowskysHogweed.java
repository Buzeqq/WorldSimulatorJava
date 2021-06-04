package com.github.buzeqq.wordlsimulator.World.Organisms.Plant.SosnowskysHogweed;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.CyberSheep.CyberSheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;
import com.github.buzeqq.wordlsimulator.World.World;

import java.awt.*;

public class SosnowskysHogweed extends Plant {

    public SosnowskysHogweed(Coordinates coordinates, World origin) {
        super(coordinates, 10, origin);
    }

    public SosnowskysHogweed(String data, World origin) {
        super(data, origin);
    }

    @Override
    public final void makeAction() {
        this.killAllNeighbours();
        super.makeAction();
    }

    @Override
    public final void collision(Animal other) {
        this.die();
        if (!(other instanceof CyberSheep)) {
            other.die();
            return;
        }

        other.getOrigin().changeOrganisms(this.getCoords(), other);
        other.getCoords().setCoords(this.getCoords());
    }

    @Override
    public final GUIField print() {
        class JButtonSosnowskysHogweed extends GUIField {
            public JButtonSosnowskysHogweed() {
                super("S");
                this.setBackground(new Color(0,100,0));
            }
        }
        return new JButtonSosnowskysHogweed();
    }

    @Override
    public final Organism getNew(Coordinates coords) {
        return new SosnowskysHogweed(coords, this.getOrigin());
    }

    @Override
    public final String toString() {
        return "Sosnowskys hogweed: " + this.getCoords() + " initiative: " + this.getInitiative() + " strength: " +
                this.getStrength() + " age: " + this.getAge();
    }

    private void killAllNeighbours() {
        for (Direction direction : Direction.values()) {
            Coordinates coordinates = new Coordinates(this.getCoords(), direction);
            Organism organism = this.getOrigin().getOrganism(coordinates);

            if (organism == null) return;
            if (organism instanceof Animal && !(organism instanceof CyberSheep)) {
                this.getOrigin().getCommentator().killNeighbours((Animal)organism);
                organism.die();
            }
        }
    }
}
