package org.javarush.organism.animals;
public enum AnimalType {
    WOLF(0,3,30,8,50, "🐺"), BOA(1,3,30,3,15, "🐍"),
    FOX(2,2,30,2,8, "🦊"), BEAR(3,2,5,80,500, "🐻"),
    EAGLE(4,3,20,1,6, "🦅"), HORSE(5,2,20,60,400, "🐎"),
    DEER(6,2,20,50,300, "🦌"), RABBIT(7,2,150,0.45,2, "🐇"),
    MOUSE(8,2,500,0.01,0.05, "🐁"), GOAT(9,3,140,10,60, "🐐"),
    SHEEP(10,3,140, 15,70, "🐑"), BOAR(11,2,50,50,400, "🐗"),
    BUFFALO(12,3,10,100,700, "🐃"), DUCK(13,4,200,0.15,1, "🦆"),
    CATERPILLAR(14,0,1000,0,0.01, "🐛") , PLANT(15,0,200,0,1,"🌿");

    private final int chanceToEat;
    private final int speed;
    private final int maxTypeInLocation;
    private final double needFoods;
    private final double animalWeight;
    private final String icon;

    public String getIcon() {
        return icon;
    }

    AnimalType(int chanceToEat, int speed, int maxTypeInLocation, double needFoods, double animalWeight, String icon) {
        this.chanceToEat = chanceToEat;
        this.speed = speed;
        this.maxTypeInLocation = maxTypeInLocation;
        this.needFoods = needFoods;
        this.animalWeight = animalWeight;
        this.icon = icon;
    }
    public int getChanceToEat() {
        return chanceToEat;
    }

    public synchronized int getSpeed() {
        return speed;
    }

    public int getMaxTypeInLocation() {
        return maxTypeInLocation;
    }

    public double getNeedFoods() {
        return needFoods;
    }

    public double getAnimalWeight() {
        return animalWeight;
    }
}