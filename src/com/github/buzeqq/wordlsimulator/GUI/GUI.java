package com.github.buzeqq.wordlsimulator.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import com.github.buzeqq.wordlsimulator.GUI.Field.GUIField;

public class GUI extends JFrame {
    private final JPanel world;
    private final JButton nextRound;

    public GUI(Dimension frameSize, Dimension worldSize) {
        this.setTitle("World simulator");
        this.setSize(frameSize);

        JPanel mainPanel = new JPanel(null);

        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        world = new JPanel();
        world.setBounds(0, 0, worldSize.width, worldSize.height);
        world.setLayout(new GridLayout(20, 20));
        world.setBackground(Color.GRAY);

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 20; y++) {
                world.add(new GUIField("{" + x + ", " + y + "}"));
            }
        }

        nextRound = new JButton("Next round!");

        mainPanel.add(world, makeConstraints(0, 0, 5));
        mainPanel.add(nextRound, makeConstraints(0, 10, 5));

        this.setContentPane(mainPanel);
        this.setVisible(true);
        //this.setResizable(false);
        world.setVisible(true);
    }

    public static GridBagConstraints makeConstraints(final int x, final int y, final int width) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5,5,5,5);

        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = 1;

        return gridBagConstraints;
    }
}
