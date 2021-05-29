package com.github.buzeqq.wordlsimulator.App;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.World.World;

public class App {
    public static void main(final String[] args) {
        GUI gui = new GUI(20, 20);
        World world = new World(20, 20, gui.getWorldPane());

    }
}
