package com.github.buzeqq.wordlsimulator.App;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.World.World;

import java.io.IOException;

public class App {
    public static void main(final String[] args) {
        int X, Y;
        X = Y = 20;
        GUI gui = new GUI(X, Y);
        World world = new World(X, Y, gui);
        gui.setWorld(world);

    }
}
