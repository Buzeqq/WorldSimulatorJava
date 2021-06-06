package com.github.buzeqq.wordlsimulator.GUI.GUIWorld;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
import java.awt.*;

public class GUIWorld extends JPanel {

    public GUIWorld(final int x, final int y, final World world) {
        this.setLayout(new GridLayout(x, y));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.add(new GUIField("", x, y, world));
            }
        }

        this.setVisible(true);
    }

    public final void printWorld(final World world) {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }

        for (int y = 0; y < world.getBounds().getY(); y++) {
            for (int x = 0; x < world.getBounds().getX(); x++) {
                Organism organism = world.getOrganism(new Coordinates(x, y));
                GUIField field;

                if (organism != null) {
                    field = organism.print(x, y, world);
                } else field = new GUIField("", x, y, world);

                this.add(field);
            }
        }

        this.revalidate();
        this.repaint();
    }

    public final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public final Direction getDirection() {
        return this.direction;
    }

    public final void activateAbility() {
        activated = true;
    }

    public final boolean getActivated() {
        return this.activated;
    }

    public final void resetActivated() {
        this.activated = false;
    }

    private Direction direction;
    private boolean activated = false;
}
