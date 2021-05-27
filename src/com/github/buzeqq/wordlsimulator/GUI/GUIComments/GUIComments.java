package com.github.buzeqq.wordlsimulator.GUI.GUIComments;

import javax.swing.*;

public class GUIComments extends JTextArea {
    public GUIComments() {
        this.setEditable(false);
        this.setLineWrap(true);
        this.setText("Comments will land here!\n");
    }
}
