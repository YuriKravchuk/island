package org.javarush.organism.animals;
import org.javarush.organism.plants.Plant;
import java.util.concurrent.ConcurrentLinkedDeque;


public interface Eatable {
    public void eat (ConcurrentLinkedDeque<Animals> animals, ConcurrentLinkedDeque<Plant> plants);
}
