package org.javarush.organism;

import org.javarush.organism.animals.AnimalType;

public interface Organism {

    public abstract AnimalType getAnimalType();
    double getWeight();
}
