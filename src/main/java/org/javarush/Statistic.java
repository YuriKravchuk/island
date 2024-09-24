package org.javarush;
import org.javarush.map.Cell;
import org.javarush.map.Field;
public class Statistic {

    public synchronized static void setEatenAnimals() {
        Statistic.eatenAnimals = eatenAnimals + 1;
    }

    public synchronized static void setBornAnimals() {
        Statistic.bornAnimals = bornAnimals + 1;
    }

    public synchronized static void setMigratedAnimals() {
        Statistic.migratedAnimals = migratedAnimals + 1;
    }
    public synchronized static void setDeadAnimals() {
        Statistic.deadAnimals = deadAnimals + 1;
    }

    public static void printStatisticAfterAction() {
        System.out.println("Статистика симуляції:\n" +
                "Померло з голоду " + deadAnimals + " тварин\n" +
                "Народилось " + bornAnimals + " тварин\n" +
                "Було впольовано " + eatenAnimals + " тварин\n" +
                "Перемістилось " + migratedAnimals + " тварин\n");
    }

    private static int eatenAnimals;
    private static int bornAnimals;
    private static int migratedAnimals;
    private static int deadAnimals;

    public static void printStatistic() {
        System.out.println("****************************************************************************************************************************\n" +
                "На острові " + statisticAnimal() + " тварин, " + statisticPlants() + " рослин.\n");
    }

    public static int statisticAnimal() {
        int count = 0;
        for (Cell[] location : Field.cells) {
            for (Cell cell : location) {
                count+=cell.getAnimals().size();
            }
        }return count;
    }

    private static int statisticPlants() {
        int count = 0;
        for (Cell[] location : Field.cells) {
            for (Cell cell : location) {
                count+=cell.getPlants().size();
            }
        }return count;
    }
}
