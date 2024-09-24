package org.javarush;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

@Getter
@Setter
public class Parameters {
    int width;
    int height;
    int workTimer;
    public Parameters(){
        int millisecondsToSeconds = 1000;
        try (FileReader fileReader = new FileReader("settings.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            width = Math.toIntExact((Long) jsonObject.get("width"));
            height = Math.toIntExact((Long) jsonObject.get("height"));
            workTimer = Math.toIntExact((Long) jsonObject.get("workTimer")) * millisecondsToSeconds;
        } catch (Exception e) {
            System.out.println("Default size 2 x 2");
            width = 2;
            height = 2;
            workTimer = 10;
        }
    }


    public static final int[][] chanceEat =
                    {{0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0},         //wolf
                    {0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0, 0},               //boa
                    {0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40, 0},               //fox
                    {0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0, 0},         //bear
                    {0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0, 0},               //eagle
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //horse
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //deer
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //rabbit
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},                //mouse
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //goat
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //sheep
                    {0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 90, 100},               //boar
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100},                 //buffalo
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 100},                //duck
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100}};                //caterpillar

}
//      wolf   fox    eagle     deer      mouse    sheep    buffalo    caterpillar
//          boa   bear     horse    rabbit     goat     boar       duck           plant