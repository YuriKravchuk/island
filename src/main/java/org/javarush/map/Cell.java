package org.javarush.map;

import lombok.Getter;
import lombok.Setter;
import org.javarush.Statistic;
import org.javarush.organism.Organism;
import org.javarush.organism.animals.AnimalType;
import org.javarush.organism.animals.Animals;
import org.javarush.organism.animals.MoveAnimal;
import org.javarush.organism.plants.Plant;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Cell implements Runnable {
    private int x;
    private int y;
    private ConcurrentLinkedDeque<Animals> animals;
    private ConcurrentLinkedDeque<Plant> plants;

    private final Lock lock = new ReentrantLock();
    public Cell(int x, int y, ConcurrentLinkedDeque<Animals> animals, ConcurrentLinkedDeque<Plant> plants) {
        this.x = x;
        this.y = y;
        this.animals = animals;
        this.plants = plants;
    }

    public void addAnimal(ConcurrentLinkedDeque<Animals> animals, Animals animal) {
        if(this.animals == null){
            this.animals = new ConcurrentLinkedDeque<>();
        }
        animals.add(animal);
    }

    public boolean getCountTypeAnimals(Animals animal) {
        boolean result = false;
        int count = 0;
        if(animal != null){
            for (Animals animal1 : this.animals) {
                if(animal1.getAnimalType() == animal.getAnimalType()){
                    count++;
                }
            }
            if(count < animal.getAnimalType().getMaxTypeInLocation()){
                result = true;
            }}
        return result;
    }

    public void plantsGrowUp(){
        if(!plants.isEmpty()) {
            lock.lock();
            try{
                for (int i = 0; i < plants.size()*0.5; i++) {
                    if (plants.size() < AnimalType.PLANT.getMaxTypeInLocation()){
                        plants.add(new Plant());
                    } else {
                        return;
                    }
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public synchronized void migration(Animals animal) {
        int moveSpeed = ThreadLocalRandom.current().nextInt( animal.getAnimalType().getSpeed() + 1);
        int random = ThreadLocalRandom.current().nextInt( 4);
        if (!animal.isAlreadyMoved()) {
            Cell locationNext = null;
            switch (MoveAnimal.values()[random]) {
                case RIGHT:
                    if ((x + moveSpeed) < (Field.cells.length - 1)) {
                        locationNext = Field.cells[x + moveSpeed][y];
                    }
                    break;

                case LEFT:
                    if (x - moveSpeed >= 0) {
                        locationNext = Field.cells[x - moveSpeed][y];
                    }
                    break;
                case UP:
                    if (y + moveSpeed < Field.cells[Field.cells.length - 1].length) {
                        locationNext = Field.cells[x][y + moveSpeed];
                    }
                    break;
                case DOWN:
                    if (y - moveSpeed >= 0) {
                        locationNext = Field.cells[x][y - moveSpeed];
                    }
                    break;
            }
            if (locationNext != null && locationNext.getCountTypeAnimals(animal) && locationNext != this) {
                locationNext.addAnimal(locationNext.getAnimals(), animal);
                this.animals.remove(animal);
                Statistic.setMigratedAnimals();
                animal.setAlreadyMoved(true);
            }
        }
    }

        public synchronized void animalsDoSomething () {
            lock.lock();
            try {
                ConcurrentLinkedDeque<Animals> animalList = this.getAnimals();
                ConcurrentLinkedDeque<Plant> plantsCopy = this.getPlants();
                ConcurrentLinkedDeque<Animals> animalsCopy = new ConcurrentLinkedDeque<>(animalList);
                if (!animalList.isEmpty()) {
                    for (Animals animal : animalsCopy) {
                        animal.setWeight(animal.getWeight() - animal.getAnimalType().getAnimalWeight() * 0.1);
                        if (animal.getWeight() < animal.getAnimalType().getAnimalWeight() * 0.2) {
                            animalList.remove(animal);
                            Statistic.setDeadAnimals();
                        } else {
                            animal.eat(animalList, plantsCopy);
                            animal.reproduce(animalList);
                            this.migration(animal);
                        }
                    }
                }

            } finally {
                lock.unlock();
            }
        }

    public String countAnimalsTypes(){
        lock.lock();
        try {
            int count = 0;
            AnimalType[] values = AnimalType.values();
            StringBuilder types = new StringBuilder();
            ConcurrentLinkedDeque<Organism> animalsCopy = new ConcurrentLinkedDeque<>(animals);
            ConcurrentLinkedDeque<Plant> plantsCopy = new ConcurrentLinkedDeque<>(plants);
            animalsCopy.addAll(plantsCopy);
            for (AnimalType value : values) {
                for (Organism organism : animalsCopy) {
                    if (organism != null && value.getChanceToEat() == organism.getAnimalType().getChanceToEat()) {
                        count++;
                    }
                }
                types.append(value.getIcon()).append(" ").append(count).append(" ");
                count = 0;
            }
            return types.toString();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public synchronized String toString() {
        return "----------------------------------------------------------------------------------------------------------------------------" + "\n"
                + "Тварини в локації [" + this.x + "][" + this.y + "]: " + countAnimalsTypes();
    }

        @Override
    public void run() {
        this.plantsGrowUp();
        this.animalsDoSomething();
    }
}
