package com.github.buzeqq.wordlsimulator.App;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.World.World;

public class App {
    public static void main(final String[] args) {
        int X, Y;
        X = Y = 3;
        GUI gui = new GUI(X, Y);
        World world = new World(X, Y, gui.getWorldPane(), gui.getCommentSection());
        gui.setWorld(world);

    }
}
