package org.javarush;

import org.javarush.map.Field;
import org.javarush.map.Cell;
import org.javarush.organism.animals.Animals;

import java.util.concurrent.*;

public class Starter {
    int workTimer;
        Field island = new Field();
        ExecutorService executor;


    public Starter() {
        this.workTimer = new Parameters().getWorkTimer();
        this.executor = Executors.newFixedThreadPool(8);

    }
    public void init() {
            long t = System.currentTimeMillis();
            long timer = t + workTimer;
            island.initialize();
            Statistic.printStatistic();
            int i=1;
            while (System.currentTimeMillis() < timer && Statistic.statisticAnimal() > 0) {
                for (Cell[] cells : Field.cells) {
                    for (Cell cell : cells) {

                        System.out.println(cell);
                        executor.submit(cell);
                    }
                }
                System.out.println("\n-------------------------------------------{           Кінець " + i++  + " дня           }-------------------------------------------\n");
                Animals.clearAnimalsMove();
            }
            executor.shutdown();
            Statistic.printStatistic();
            Statistic.printStatisticAfterAction();
            System.out.println("Кінець симуляції");
    }
}
