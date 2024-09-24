package org.javarush.map;

import org.javarush.organism.animals.AnimalType;
import org.javarush.organism.animals.Animals;
import org.javarush.organism.animals.herbivorous.*;
import org.javarush.organism.animals.predators.*;
import org.javarush.organism.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;

public class IslandCreator {
    public ConcurrentLinkedDeque<Plant> plantCreate() {
        ConcurrentLinkedDeque<Plant> plants = new ConcurrentLinkedDeque<>();
        int random = ThreadLocalRandom.current().nextInt( AnimalType.PLANT.getMaxTypeInLocation());
            for (int i = 0; i < random; i++) {
                plants.add(new Plant());
            }
        return plants;
    }

    public ConcurrentLinkedDeque<Animals> animalCreate() {
        ConcurrentLinkedDeque<Animals> generatedAnimals = new ConcurrentLinkedDeque<>();
        int randomBuffalo = ThreadLocalRandom.current().nextInt( AnimalType.BUFFALO.getMaxTypeInLocation());
        for (int i = 0; i < randomBuffalo; i++) {
            generatedAnimals.add(new Buffalo());
        }
        int randomDeer = ThreadLocalRandom.current().nextInt( AnimalType.DEER.getMaxTypeInLocation());
        for (int i = 0; i < randomDeer; i++) {
            generatedAnimals.add(new Deer());
        }
        int randomDuck = ThreadLocalRandom.current().nextInt( AnimalType.DUCK.getMaxTypeInLocation());
        for (int i = 0; i < randomDuck; i++) {
            generatedAnimals.add(new Duck());
        }
        int randomGoat = ThreadLocalRandom.current().nextInt( AnimalType.GOAT.getMaxTypeInLocation());
        for (int i = 0; i < randomGoat; i++) {
            generatedAnimals.add(new Goat());
        }
        int randomHog = ThreadLocalRandom.current().nextInt( AnimalType.BOAR.getMaxTypeInLocation());
        for (int i = 0; i < randomHog; i++) {
            generatedAnimals.add(new Boar());
        }
        int randomHorse = ThreadLocalRandom.current().nextInt( AnimalType.HORSE.getMaxTypeInLocation());
        for (int i = 0; i < randomHorse; i++) {
            generatedAnimals.add(new Horse());
        }
        int randomMouse = ThreadLocalRandom.current().nextInt( AnimalType.MOUSE.getMaxTypeInLocation());
        for (int i = 0; i < randomMouse; i++) {
            generatedAnimals.add(new Mouse());
        }
        int randomRabbit = ThreadLocalRandom.current().nextInt( AnimalType.RABBIT.getMaxTypeInLocation());
        for (int i = 0; i < randomRabbit; i++) {
            generatedAnimals.add(new Rabbit());
        }
        int randomSheep = ThreadLocalRandom.current().nextInt( AnimalType.SHEEP.getMaxTypeInLocation());
        for (int i = 0; i < randomSheep; i++) {
            generatedAnimals.add(new Sheep());
        }
        int randomWorm = ThreadLocalRandom.current().nextInt( AnimalType.CATERPILLAR.getMaxTypeInLocation());
        for (int i = 0; i < randomWorm; i++) {
            generatedAnimals.add(new Caterpillar());
        }
        int randomBear = ThreadLocalRandom.current().nextInt( AnimalType.BEAR.getMaxTypeInLocation());
        for (int i = 0; i < randomBear; i++) {
            generatedAnimals.add(new Bear());
        }
        int randomEagle = ThreadLocalRandom.current().nextInt( AnimalType.EAGLE.getMaxTypeInLocation());
        for (int i = 0; i < randomEagle; i++) {
            generatedAnimals.add(new Eagle());
        }
        int randomFox = ThreadLocalRandom.current().nextInt( AnimalType.FOX.getMaxTypeInLocation());
        for (int i = 0; i < randomFox; i++) {
            generatedAnimals.add(new Fox());
        }
        int randomSnake = ThreadLocalRandom.current().nextInt( AnimalType.BOA.getMaxTypeInLocation());
        for (int i = 0; i < randomSnake; i++) {
            generatedAnimals.add(new Boa());
        }
        int randomWolf = ThreadLocalRandom.current().nextInt( AnimalType.WOLF.getMaxTypeInLocation());
        for (int i = 0; i < randomWolf; i++) {
            generatedAnimals.add(new Wolf());
        }
        return generatedAnimals;
    }
}
