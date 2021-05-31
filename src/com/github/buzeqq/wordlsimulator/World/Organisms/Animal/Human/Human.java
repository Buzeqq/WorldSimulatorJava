package com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.World;

import javax.swing.*;
import java.awt.*;

public class Human extends Animal {

    public Human(Coordinates coords, World origin) {
        super(coords, 4, 5, origin);
    }

    @Override
    public GUIField print() {
        class JButtonHuman extends GUIField {
            public JButtonHuman() {
                super("H");
                this.setBackground(Color.PINK);
            }
        }

        return new JButtonHuman();
    }
}
