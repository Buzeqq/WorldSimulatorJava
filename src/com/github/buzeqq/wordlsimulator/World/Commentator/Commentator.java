package com.github.buzeqq.wordlsimulator.World.Commentator;

import com.github.buzeqq.wordlsimulator.GUI.GUIComments.GUIComments;
import com.github.buzeqq.wordlsimulator.Utilities.Coordinates;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Animal;
import com.github.buzeqq.wordlsimulator.World.Organisms.Animal.Fox.Fox;
import com.github.buzeqq.wordlsimulator.World.Organisms.Organism;
import com.github.buzeqq.wordlsimulator.World.Organisms.Plant.Plant;

import java.util.LinkedList;
import java.util.Queue;

public class Commentator {

    public Commentator(final GUIComments commentSection) {
        this.commentSection = commentSection;
    }

    private void addComment(final String comment) {
        this.comments.add(comment);
    }

    public final void updateCommentSection() {
        while (!this.comments.isEmpty()) {
            this.commentSection.append(this.comments.remove() + "\n");
        }
    }

    public void born(final Organism organism) {
        this.addComment("New organism was born: " + organism);
    }

    public void die(final Organism organism) {
        this.addComment("Organism died: " + organism);
    }

    public void confrontation(final Organism organism, final Animal other) {
        this.addComment(organism + " was attacked by " + other);
    }

    public void breed(final Animal mother, final Animal father) {
        this.addComment(mother + " and " + father + " will breed");
    }

    public void move(final Animal animal, final Coordinates coordinates) {
        this.addComment(animal + " is moving to " + coordinates);
    }

    public void spread(final Plant plant) {
        this.addComment(plant + " has spread");
    }

    public void buff(final Animal animal) {
        this.addComment(animal + " has buffed its strength");
    }

    public void foxSmell(final Fox fox) {
        this.addComment(fox + " missed the fight and moved to the next free space");
    }

    public void poisoned(final Animal other) {
        this.addComment(other + " was poisoned by wolf berries");
    }

    public void block(final Animal other) {
        this.addComment("Turtle managed to block the attack from " + other);
    }

    public void killNeighbours(final Animal animal) {
        this.addComment(animal + " was in the field of fire Sosnowskys hogweed");
    }

    public void escape(final Animal other) {
        this.addComment("Antelope managed to escape from fight with " + other);
    }

    public void targetLocked(final Coordinates targetLocked) {
        this.addComment("Cyber sheep found its target at " + targetLocked);
    }

    public void fullBurn() {
        this.addComment("Human is burning everything around him down...");
    }

    private final Queue<String> comments = new LinkedList<>();
    private final GUIComments commentSection;
}
