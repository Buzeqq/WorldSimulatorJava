package com.github.buzeqq.wordlsimulator.GUI.GUIMenu;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMenu extends JMenuBar {
    public GUIMenu() {
        JMenu gameMenu = new JMenu("Game");

        this.saveGame = new JMenuItem("Save");
        this.loadGame = new JMenuItem("Load");
        this.exitGame = new JMenuItem("Exit");

        gameMenu.add(saveGame);
        gameMenu.add(loadGame);
        gameMenu.add(exitGame);

        this.add(gameMenu);
    }

    public final void setActionListener(ActionListener frame) {
        this.saveGame.addActionListener(frame);
        this.loadGame.addActionListener(frame);
        this.exitGame.addActionListener(frame);
    }

    public final JMenuItem getSave() {
        return this.saveGame;
    }

    public final JMenuItem getLoad() {
        return this.loadGame;
    }

    public final JMenuItem getExit() {
        return this.exitGame;
    }

    private final JMenuItem saveGame;
    private final JMenuItem loadGame;
    private final JMenuItem exitGame;
}
