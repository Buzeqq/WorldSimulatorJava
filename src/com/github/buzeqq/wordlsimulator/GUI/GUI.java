package com.github.buzeqq.wordlsimulator.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.buzeqq.wordlsimulator.GUI.GUIField.GUIField;
import com.github.buzeqq.wordlsimulator.GUI.GUIComments.GUIComments;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.World;

public class GUI extends JFrame implements ActionListener {
    private World world;
    private final GUIWorld worldPane;
    private final GUIComments commentSection;
    private final JLabel lWorld;
    private int turnCounter = 0;
    private final JButton nextRound;
    private final Action upAction;
    private final Action rightAction;
    private final Action downAction;
    private final Action leftAction;

    public GUI(final int x, final int y) {
        this.setTitle("World simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridBagLayout());

        lWorld = new JLabel("Turn: 0");
        main.add(lWorld, makeConstraints(0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START));
        worldPane = new GUIWorld(x, y);

        this.upAction = new UpAction();
        this.rightAction = new RightAction();
        this.downAction = new DownAction();
        this.leftAction = new LeftAction();

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upAction");
        worldPane.getActionMap().put("upAction", upAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        worldPane.getActionMap().put("rightAction", rightAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        worldPane.getActionMap().put("downAction", downAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        worldPane.getActionMap().put("leftAction", leftAction);

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

    public final void setWorld(final World world) {
        this.world = world;
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
            if (turnCounter != 0) {
                world.makeTurn();
                // commentSectionPane.repaint(world.getComments())
            }
            worldPane.printWorld(world);
            turnCounter++;
            lWorld.setText("Turn: " + turnCounter);
            commentSection.append("TURN: " + turnCounter + "\n");
        }
    }

    public final GUIWorld getWorldPane() {
        return this.worldPane;
    }

    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_UP);
        }
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_RIGHT);
        }
    }

    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_DOWN);
        }
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_LEFT);
        }
    }
}
