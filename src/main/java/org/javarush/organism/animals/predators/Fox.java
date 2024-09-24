package org.javarush.organism.animals.predators;

import org.javarush.organism.animals.AnimalType;
import org.javarush.organism.animals.Animals;

public class Fox extends Predators{
    private AnimalType animalType = AnimalType.FOX;
    private double weight= animalType.getAnimalWeight();


    @Override
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public AnimalType getAnimalType() {
        return this.animalType;
    }

    @Override
    public Animals newAnimal() {
        return new Fox();
    }
}
