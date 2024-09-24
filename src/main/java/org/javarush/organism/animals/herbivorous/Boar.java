package org.javarush.organism.animals.herbivorous;

import org.javarush.organism.animals.AnimalType;
import org.javarush.organism.animals.Animals;

public class Boar extends Herbivorous{
    private AnimalType animalType = AnimalType.BOAR;
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
        return new Boar();
    }

    public Animals killAnimal(){
        return null;
    }
}
