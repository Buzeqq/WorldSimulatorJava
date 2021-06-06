package com.github.buzeqq.wordlsimulator.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.github.buzeqq.wordlsimulator.GUI.GUIComments.GUIComments;
import com.github.buzeqq.wordlsimulator.GUI.GUIMenu.GUIMenu;
import com.github.buzeqq.wordlsimulator.GUI.GUIWorld.GUIWorld;
import com.github.buzeqq.wordlsimulator.Utilities.Direction;
import com.github.buzeqq.wordlsimulator.World.World;

public class GUI extends JFrame implements ActionListener {

    public GUI(final int x, final int y) {
        this.setTitle("World simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel main = new JPanel(new GridBagLayout());

        this.lWorld = new JLabel("Turn: 0");
        main.add(this.lWorld, makeConstraints(0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START));

        final JLabel author = new JLabel("Miłosz Chojnacki, 184934");
        main.add(author, makeConstraints(1, 0, 1, 1, GridBagConstraints.FIRST_LINE_START));

        this.lDirection = new JLabel("Direction: ");
        main.add(this.lDirection, makeConstraints(2, 0, 1, 1, GridBagConstraints.FIRST_LINE_START));

        this.worldPane = new GUIWorld(x, y, world);
        this.setKeyBindings();
        this.guiMenu = new GUIMenu();
        this.setJMenuBar(guiMenu);
        guiMenu.setActionListener(this);

        this.setWorldPane(main);

        this.nextRound = new JButton("Next round!");
        this.nextRound.addActionListener(this);
        main.add(nextRound, makeConstraints(20, 1, 1, 1, GridBagConstraints.FIRST_LINE_END));

        this.setCommentSection(main);

        main.setBackground(Color.GRAY);
        main.setVisible(true);

        this.setContentPane(main);
        this.pack();
        this.setVisible(true);
    }

    private void setCommentSection(final JPanel main) {
        JLabel lCommentSection = new JLabel("Comment section:");
        main.add(lCommentSection, makeConstraints(20, 2, 1, 1, GridBagConstraints.FIRST_LINE_END));
        this.commentSection = new GUIComments();
        JScrollPane scrollCommentSection = new JScrollPane(commentSection);
        scrollCommentSection.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCommentSection.setPreferredSize(new Dimension(300, 600));
        main.add(scrollCommentSection, makeConstraints(20, 3, 1, 1, GridBagConstraints.FIRST_LINE_END));
    }

    private void setWorldPane(final JPanel main) {
        JScrollPane worldScroll = new JScrollPane(worldPane);
        worldScroll.setPreferredSize(new Dimension(600, 600));
        worldScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        worldScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        main.add(worldScroll, makeConstraints(0, 1, 20, 20, GridBagConstraints.FIRST_LINE_START));
    }

    private void setKeyBindings() {
        Action upAction = new UpAction();
        Action rightAction = new RightAction();
        Action downAction = new DownAction();
        Action leftAction = new LeftAction();
        Action specialAbility = new HumanSpecialAbility();
        Action nextTurn = new NextTurn();

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"), "upAction");
        worldPane.getActionMap().put("upAction", upAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        worldPane.getActionMap().put("rightAction", rightAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        worldPane.getActionMap().put("downAction", downAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        worldPane.getActionMap().put("leftAction", leftAction);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("X"), "specialAbility");
        worldPane.getActionMap().put("specialAbility", specialAbility);

        worldPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("SPACE"), "nextTurn");
        worldPane.getActionMap().put("nextTurn", nextTurn);
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

    public final void nextTurn() {
        if (this.turnCounter != 0) {
            this.world.makeTurn();
        }
        this.worldPane.printWorld(world);
        this.turnCounter++;
        this.lWorld.setText("Turn: " + this.turnCounter);
        this.commentSection.append("TURN: " + this.turnCounter + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.nextRound) {
            this.nextTurn();
        } else if (e.getSource() == this.guiMenu.getSave()) {
            File directory = null;
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Choose save directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                directory = chooser.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(null, "No directory was selected.");
            }

            this.world.save(directory);
        } else if (e.getSource() == this.guiMenu.getLoad()) {
            File saveFile = null;
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Choose save file");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                saveFile = chooser.getSelectedFile();
            } else {
                JOptionPane.showMessageDialog(null, "No save file was selected.");
            }

            this.world.load(saveFile);
            this.worldPane.printWorld(this.world);
            this.lWorld.setText("Turn: " + this.turnCounter);

        } else if (e.getSource() == this.guiMenu.getExit()) {
            System.exit(1);
        }
    }

    public final GUIWorld getWorldPane() {
        return this.worldPane;
    }

    public final GUIComments getCommentSection() {
        return this.commentSection;
    }

    public final int getTurn() {
        return this.turnCounter;
    }

    public final String getCommentSectionContent() {
        return this.commentSection.getText();
    }

    public void setTurn(final int parseInt) {
        this.turnCounter = parseInt;
    }

    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_UP);
            lDirection.setText("Direction: UP");
        }
    }

    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_RIGHT);
            lDirection.setText("Direction: RIGHT");
        }
    }

    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_DOWN);
            lDirection.setText("Direction: DOWN");
        }
    }

    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.setDirection(Direction.DIRECTION_LEFT);
            lDirection.setText("Direction: LEFT");
        }
    }

    public class HumanSpecialAbility extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            worldPane.activateAbility();
        }
    }

    public class NextTurn extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            nextTurn();
        }
    }

    private World world;
    private final GUIWorld worldPane;
    private GUIComments commentSection;
    private final GUIMenu guiMenu;
    private final JLabel lWorld;
    private int turnCounter = 0;
    private final JButton nextRound;
    private final JLabel lDirection;
}
