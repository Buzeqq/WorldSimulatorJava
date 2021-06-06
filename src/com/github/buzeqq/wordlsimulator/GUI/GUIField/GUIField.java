package com.github.buzeqq.wordlsimulator.GUI.GUIField;

import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Antelope.Antelope;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.CyberSheep.CyberSheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox.Fox;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep.Sheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Turtle.Turtle;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Wolf.Wolf;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Dandelion.Dandelion;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Grass.Grass;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Guarana.Guarana;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.SosnowskysHogweed.SosnowskysHogweed;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.WolfBerries.WolfBerries;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIField extends JButton {

    public static final int SIZE = 30;
    final Coordinates coordinates;
    final World world;
    final JPopupMenu menu;

    public GUIField(final String string, final int x, final int y, final World origin) {
        super(string);
        coordinates = new Coordinates(x, y);
        world = origin;
        this.setBackground(Color.cyan);
        this.setFont(new Font("Arial", Font.PLAIN, 0));
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setVisible(true);
        this.setBorderPainted(false);

        this.menu = new JPopupMenu();
        this.menu.add(new JMenuItem(new AbstractAction("Sheep") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Sheep(coordinates, world));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Wolf") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Wolf(coordinates, world));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Antelope") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Antelope(coordinates, world));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Cyber sheep") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new CyberSheep(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Fox") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Fox(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Turtle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Turtle(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Dandelion") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Dandelion(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Grass") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Grass(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Guarana") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new Guarana(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Sosnowskys hogweed") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new SosnowskysHogweed(coordinates, origin));
            }
        }));

        this.menu.add(new JMenuItem(new AbstractAction("Wolf berries") {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.born(new WolfBerries(coordinates, origin));
            }
        }));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
