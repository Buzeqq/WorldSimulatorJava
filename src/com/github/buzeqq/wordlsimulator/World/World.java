package com.github.buzeqq.wordlsimulator.World;

import com.github.buzeqq.wordlsimulator.GUI.GUI;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Commentator.Commentator;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Antelope.Antelope;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.CyberSheep.CyberSheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox.Fox;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Human.Human;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Sheep.Sheep;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Turtle.Turtle;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Wolf.Wolf;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Dandelion.Dandelion;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Grass.Grass;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Guarana.Guarana;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.SosnowskysHogweed.SosnowskysHogweed;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.WolfBerries.WolfBerries;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class World {

    public World(final int x, final int y, GUI gui) {
        this.bounds = new Coordinates(x, y);
        this.organisms = new HashMap<>();
        this.commentator = new Commentator(gui.getCommentSection());
        this.gui = gui;

        this.born(new Human(this.getRandomFreeCoords(), this, gui.getWorldPane()));
        for (int i = 0; i < 3; i++) {
            this.born(new Sheep(this.getRandomFreeCoords(), this));
            this.born(new Fox(this.getRandomFreeCoords(), this));
            this.born(new Wolf(this.getRandomFreeCoords(), this));
            this.born(new Turtle(this.getRandomFreeCoords(), this));
            this.born(new Antelope(this.getRandomFreeCoords(), this));
            this.born(new CyberSheep(this.getRandomFreeCoords(), this));
            this.born(new Grass(this.getRandomFreeCoords(), this));
            this.born(new Dandelion(this.getRandomFreeCoords(), this));
            this.born(new Guarana(this.getRandomFreeCoords(), this));
            this.born(new WolfBerries(this.getRandomFreeCoords(), this));
            this.born(new SosnowskysHogweed(this.getRandomFreeCoords(), this));
        }
    }

    public void makeTurn() {
        final List<Map.Entry<Coordinates, Organism>> list = new LinkedList<>(organisms.entrySet());
        list.sort((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        for (Map.Entry<Coordinates, Organism> organism : list) {
            if (organism.getValue().isDead()) continue;
            organism.getValue().makeAction();
        }

        this.commentator.updateCommentSection();
    }

    public final Coordinates getBounds() {
        return bounds;
    }

    public final Organism getOrganism(final Coordinates coordinates) {
        return this.organisms.get(coordinates);
    }

    public final Commentator getCommentator() {
        return this.commentator;
    }

    public final void born(final Organism organism) {
        if (this.organisms.get(organism.getCoords()) != null) return;
        this.organisms.put(organism.getCoords(), organism);
        this.commentator.born(organism);
    }

    public final void die(Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.commentator.die(organism);
    }

    public final void changeOrganisms(final Coordinates newCoords, final Organism organism) {
        this.organisms.remove(organism.getCoords());
        this.organisms.put(newCoords, organism);
    }

    public final boolean validateCoords(final Coordinates coords) {
        if (coords.getX() >= this.bounds.getX() || coords.getX() < 0) return true;
        return coords.getY() >= this.bounds.getY() || coords.getY() < 0;
    }

    public final Coordinates getClosestHogweed(final Coordinates coords) {
       List<Organism> potentialTargets = this.organisms.values().stream()
               .filter(organism -> organism instanceof SosnowskysHogweed)
               .collect(Collectors.toList());

       Organism minOrganism = null;
       int min = Integer.MAX_VALUE;
       for (Organism organism : potentialTargets) {
           System.out.println(organism);
           final int distance = Coordinates.calculateDistance(organism.getCoords(), coords);
           if (distance < min) {
               min = distance;
               minOrganism = organism;
           }
       }

       return minOrganism == null ? null : minOrganism.getCoords();
    }

    public final void save(final File directory) {
        if (directory == null) return;

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        String fileName = "Save:" + dateFormat.format(date.getTime()) + ".txt";
        try {
            String file = directory + File.separator + fileName;
            File save = new File(file);
            int errorCode;
            if (save.createNewFile()) {
                errorCode = 1;
            } else {
                JOptionPane.showMessageDialog(null, "File already exists.");
                errorCode = 2;
            }

            this.writeSave(file);
            if (errorCode != 1) throw new IOException();
            else {
                JOptionPane.showMessageDialog(null, "Successfully saved game!");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "An error occurred. Try to chose another location.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

    public final void load(final File path) {
        this.organisms.clear();

        try {
            Scanner loadReader = new Scanner(path);

            while (loadReader.hasNextLine()) {
                String data = loadReader.nextLine();
                if (data.startsWith("TURN:")) {
                    this.setTurn(data);
                } else if (data.startsWith("X:")) {
                    this.setX(data);
                } else if (data.startsWith("Y:")) {
                    this.setY(data);
                } else if (data.startsWith("o:")) {
                    this.addOrganism(data);
                } else if (data.startsWith("CommentSection:")) {
                    this.readCommentSection(loadReader);
                    break;
                }
            }

            loadReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "An error occurred while reading save file.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    private void writeSave(final String path) throws IOException {
        System.out.println(path);
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write( "TURN:" + this.gui.getTurn() + "\n");
        fileWriter.write("X:" + this.getBounds().getX() + "\nY:" + this.getBounds().getY() + "\n");

        StringBuilder stringBuilder = new StringBuilder();
        for (Organism organism : this.organisms.values()) {
            if (organism.isDead()) continue;
            stringBuilder.append("o:").append(organism).append("\n");
        }

        stringBuilder.append("CommentSection:\n");
        stringBuilder.append(this.gui.getCommentSectionContent());
        fileWriter.write(String.valueOf(stringBuilder));
        fileWriter.close();
    }

    private void readCommentSection(final Scanner loadReader) {
        this.gui.getCommentSection().setText("");
        while (loadReader.hasNextLine()) {
            this.gui.getCommentSection().append(loadReader.nextLine() + "\n");
        }
    }

    private void addOrganism(String data) throws FileNotFoundException {
        data = data.substring(2);
        System.out.println(data);
        switch (data.substring(0, data.indexOf(":"))) {
            case "Antelope" -> this.born(new Antelope(data, this));
            case "Cyber sheep" -> this.born(new CyberSheep(data, this));
            case "Fox"-> this.born(new Fox(data, this));
            case "Human" -> this.born(new Human(data, this, this.gui.getWorldPane()));
            case "Sheep" -> this.born(new Sheep(data, this));
            case "Turtle" -> this.born(new Turtle(data, this));
            case "Wolf" -> this.born(new Wolf(data, this));
            case "Dandelion" -> this.born(new Dandelion(data, this));
            case "Grass" -> this.born(new Grass(data, this));
            case "Guarana" -> this.born(new Guarana(data, this));
            case "Sosnowskys hogweed" -> this.born(new SosnowskysHogweed(data, this));
            case "Wolf berries" -> this.born(new WolfBerries(data, this));
            default -> throw new FileNotFoundException();
        }
    }

    private void setY(final String data) {
        this.bounds.setY(Integer.parseInt(data.substring(2)));
    }

    private void setX(final String data) {
        this.bounds.setX(Integer.parseInt(data.substring(2)));
    }

    private void setTurn(final String data) {
        this.gui.setTurn(Integer.parseInt(data.substring(5)));
    }

    private Coordinates getRandomFreeCoords() {
        Coordinates coords = Coordinates.getRandomCoordinates(bounds.getX(), bounds.getY());
        if (this.getOrganism(coords) != null) return this.getRandomFreeCoords();
        else return coords;
    }

    private final HashMap<Coordinates, Organism> organisms;
    private final Coordinates bounds;
    private final Commentator commentator;
    private final GUI gui;
}
