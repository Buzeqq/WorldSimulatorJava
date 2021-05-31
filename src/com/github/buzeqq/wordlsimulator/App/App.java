package com.github.buzeqq.wordlsimulator.App;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.World.World;

public class App {
    public static void main(final String[] args) {
        int X = 5, Y = 5;
        GUI gui = new GUI(X, Y, new World(X, Y));
    }
}
