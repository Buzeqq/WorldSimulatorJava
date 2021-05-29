package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;

import java.util.HashMap;

public class World {

    private GUIWorld worldMap;
    private HashMap<Integer, Organism> organisms;
    private final int x;
    private final int y;

    public World(final int x, final int y, GUIWorld guiMap) {
        this.x = x;
        this.y = y;
    }

    public void makeTurn() {

    }

    public void printWorld() {
        this.worldMap.repaint();
    }
}
