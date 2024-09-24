package org.javarush.organism.animals;

import org.javarush.Parameters;
import org.javarush.Statistic;
import org.javarush.map.Cell;
import org.javarush.map.Field;
import org.javarush.organism.Organism;
import org.javarush.organism.animals.herbivorous.Duck;
import org.javarush.organism.animals.predators.Predators;
import org.javarush.organism.plants.Plant;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animals implements Eatable, Reproducible, Organism {

    AnimalType animalType;
    private double weight;
    private boolean alreadyMoved = false;

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }

    public void setAlreadyMoved(boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public abstract Animals newAnimal();

    @Override
    public synchronized AnimalType getAnimalType() {
        return animalType;
    }

    public boolean isHungry (){
        boolean hungry;
        if (this.getWeight() < this.getAnimalType().getAnimalWeight()){
            hungry = true;
        } else {
            hungry = false;
            }
        return hungry;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWeightAfterEat(double weightFood) {
        if(this.getAnimalType().getAnimalWeight() >= this.getWeight() + weightFood) {
            setWeight(this.getWeight() + weightFood);
        }
        else {
            setWeight(this.getAnimalType().getAnimalWeight());
        }
    }



    @Override
    public void reproduce(ConcurrentLinkedDeque<Animals> animals) {
            for (Animals animal : animals) {
                if (this.getAnimalType() == animal.getAnimalType() && this != animal) {
                    int random = ThreadLocalRandom.current().nextInt( 100);
                    if (random < 5) {
                        animals.add(this.newAnimal());
                        Statistic.setBornAnimals();
                    }
                    break;
                }
            }
    }

    @Override
    public synchronized void eat (ConcurrentLinkedDeque<Animals> animalsForFood, ConcurrentLinkedDeque<Plant> plants) {
        ConcurrentLinkedDeque<Animals> animalCopy = new ConcurrentLinkedDeque<>(animalsForFood);
        for (Animals animalForFood : animalCopy) {
            if (this instanceof Predators || this instanceof Duck) {
                boolean eatChance = this.getEatChance(animalForFood);
                if (eatChance) {
                    this.setWeightAfterEat(animalForFood.getWeight());
                    animalsForFood.remove(animalForFood);
                    Statistic.setEatenAnimals();
                }
            } else {
                ConcurrentLinkedDeque<Plant> plantsCopy = new ConcurrentLinkedDeque<>(plants);
                for (Plant plant : plantsCopy) {
                    if(this.isHungry()) {
                        if (plant.getWeight()>0) {
                            this.setWeightAfterEat(plant.getWeight());
                            plants.remove(plant);
                            return;
                        }
                    }
                }
            }
        }
    }

    public boolean getEatChance(Animals animalForFood) {
        boolean result = false;
        int chanceToEat = Parameters.chanceEat[this.getAnimalType().getChanceToEat()][animalForFood.getAnimalType().getChanceToEat()];
        int random = ThreadLocalRandom.current().nextInt( 101);
        if (random < chanceToEat && this.isHungry()) {
            result = true;
        }
        return result;
    }

    public synchronized static void clearAnimalsMove (){
        for (Cell[] cells : Field.cells) {
            for (Cell cell : cells) {
                for (Animals animal:cell.getAnimals()){
                    animal.setAlreadyMoved(false);
                }
            }
        }
    }
}
