package com.github.buzeqq.wordlsimulator.GUI.GUIWorld;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GUIWorld extends JPanel {

    public GUIWorld(final int x, final int y) {
        this.setLayout(new GridLayout(x, y));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.add(new GUIField(""));
            }
        }

        this.setVisible(true);
    }

    public final void printWorld(World world) {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }

        for (int y = 0; y < world.getBounds().getY(); y++) {
            for (int x = 0; x < world.getBounds().getX(); x++) {
                Organism organism = world.getOrganism(new Coordinates(x, y));
                GUIField field;
                if (organism != null) {
                    field = organism.print();
                    field.setBackground(Color.PINK);
                } else field = new GUIField("");
                //Random random = new Random();
                //field.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

                this.add(field);
            }
        }

        this.revalidate();
        this.repaint();
    }
}
