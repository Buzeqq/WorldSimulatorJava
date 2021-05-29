package com.github.buzeqq.wordlsimulator.GUI.GUIWorld;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;

import javax.swing.*;
import java.awt.*;

public class GUIWorld extends JPanel {

    public GUIWorld(Dimension worldSize, final int x, final int y) {
        this.setLayout(new GridLayout(x, y));

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.add(new GUIField(""));
            }
        }

        this.setVisible(true);
    }
}
