package com.github.buzeqq.wordlsimulator.GUI.Field;

import javax.swing.*;
import java.awt.*;

public class GUIField extends JButton {
    public GUIField(final String string) {
        super(string);
        //this.setBackground(Color.cyan);
        this.setSize(5, 5);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
    }
}
