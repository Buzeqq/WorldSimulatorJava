package com.github.buzeqq.wordlsimulator.GUI.GUIField;

import javax.swing.*;
import java.awt.*;

public class GUIField extends JButton {

    public static final int SIZE = 30;

    public GUIField(final String string) {
        super(string);
        this.setBackground(Color.cyan);
        //this.setSize(10, 10);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setVisible(true);
        this.setBorderPainted(false);
    }
}
