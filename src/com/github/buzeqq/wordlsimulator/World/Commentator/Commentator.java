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
    Queue<String> comments = new LinkedList<>();
    private final GUIComments commentSection;

    public Commentator(GUIComments commentSection) {
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

    public void born(Organism organism) {
        this.addComment("New organism was born: " + organism);
    }

    public void die(Organism organism) {
        this.addComment("Organism died: " + organism);
    }

    public void confrontation(Organism organism, Animal other) {
        this.addComment(organism + " was attacked by " + other);
    }

    public void breed(Animal mother, Animal father) {
        this.addComment(mother + " and " + father + " will try to breed");
    }

    public void move(Animal animal, Coordinates coordinates) {
        this.addComment(animal + " is moving to " + coordinates);
    }

    public void spread(Plant plant) {
        this.addComment(plant + " has spread");
    }

    public void buff(Animal animal) {
        this.addComment(animal + " has buffed its strength");
    }

    public void foxSmell(Fox fox) {
        this.addComment(fox + " missed the fight and moved to the next free space");
    }

    public void poisoned(Animal other) {
        this.addComment(other + " was poisoned by wolf berries");
    }

    public void block(Animal other) {
        this.addComment("Turtle managed to block the attack from " + other);
    }
}
