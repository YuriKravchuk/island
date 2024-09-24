package org.javarush.map;

import org.javarush.organism.animals.Animals;
import org.javarush.organism.plants.Plant;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class Field {
    private int width;
    private int height;
    private IslandCreator islandCreator = new IslandCreator();
    public static Cell[][] cells;
    private ExecutorService executorService;

    public Field() {
        this.executorService = Executors.newFixedThreadPool(8);
        try (FileReader fileReader = new FileReader("settings.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            width = Math.toIntExact((Long) jsonObject.get("width"));
            height = Math.toIntExact((Long) jsonObject.get("height"));
        } catch (Exception e) {
            System.out.println("Default size 2 x 2");
            width = 2;
            height = 2;
        }
    }

    public void initialize() {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ConcurrentLinkedDeque<Plant> plants = islandCreator.plantCreate();
                ConcurrentLinkedDeque<Animals> animals = islandCreator.animalCreate();
                cells[x][y] = new Cell(x, y, animals, plants);
            }
        }
    }
}


