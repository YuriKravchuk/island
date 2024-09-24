package org.javarush.organism.plants;

import org.javarush.organism.animals.AnimalType;

public class Plant extends Plants{
    private AnimalType animalType = AnimalType.PLANT;
    @Override
    public AnimalType getAnimalType() {
        return animalType;
    }

    private double weight = AnimalType.PLANT.getAnimalWeight();

    public double getWeight() {
        return weight;
    }
}