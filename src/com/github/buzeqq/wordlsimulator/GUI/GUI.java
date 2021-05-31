package com.github.buzeqq.wordlsimulator.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.GUI.GUIComments.GUIComments;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.World.World;

public class GUI extends JFrame implements ActionListener {
    private World world;
    private GUIWorld worldPane;
    private GUIComments commentSection;
    private JLabel lWorld;
    private int turnCounter = 0;

    private final JButton nextRound;

    public GUI(final int x, final int y, final World world) {
        this.world = world;

        this.setTitle("World simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridBagLayout());

        lWorld = new JLabel("Turn: 0");
        main.add(lWorld, makeConstraints(0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START));
        Dimension worldSize = new Dimension(x * GUIField.SIZE, y * GUIField.SIZE);
        worldPane = new GUIWorld(x, y);

        JScrollPane worldScroll = new JScrollPane(worldPane);
        worldScroll.setPreferredSize(new Dimension(600, 600));
        worldScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        worldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        main.add(worldScroll, makeConstraints(0, 1, 20, 20, GridBagConstraints.FIRST_LINE_START));

        nextRound = new JButton("Next round!");
        nextRound.addActionListener(this);
        main.add(nextRound, makeConstraints(20, 1, 1, 1, GridBagConstraints.FIRST_LINE_END));

        JLabel lCommentSection = new JLabel("Comment section:");
        main.add(lCommentSection, makeConstraints(20, 2, 1, 1, GridBagConstraints.FIRST_LINE_END));
        commentSection = new GUIComments();
        JScrollPane scrollCommentSection = new JScrollPane(commentSection);
        scrollCommentSection.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCommentSection.setPreferredSize(new Dimension(300, 600));
        main.add(scrollCommentSection, makeConstraints(20, 3, 1, 1, GridBagConstraints.FIRST_LINE_END));

        main.setBackground(Color.GRAY);
        main.setVisible(true);

        this.setContentPane(main);
        this.pack();
        this.setVisible(true);
    }

    public static GridBagConstraints makeConstraints(final int x, final int y, final int width, final int height, final int anchor) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = new Insets(5,5,5,5);

        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = height;

        return gridBagConstraints;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextRound) {
            // world.makeTurn()
            // world.print()
            // commentSectionPane.repaint(world.getComments())
            worldPane.printWorld(world);
            turnCounter++;
            lWorld.setText("Turn: " + turnCounter);
            commentSection.append("TURN: " + turnCounter + "\n");
        }
    }

    public final GUIWorld getWorldPane() {
        return this.worldPane;
    }
}
