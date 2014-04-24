package com.nickstephen.madmine.map;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 24/04/2014.
 */
public class Map {
    // TODO: Complete the map class.

    private final int mapWidth;       // Number of blocks wide the map is.
    private final int mapHeight;      // Number of blocks high the map is.
    private final int scoreDoorOpen;  // Score required to open the door.
    private final int scoreTrophy;    // Score required to get the trophy for this map.

    private int[][][][] layout;

    // Constructor for the map class.
    public Map(int width, int height, int scoreFinish, int scoreGoal){
        // Set the width, height, and score thresholds for the map.
        mapWidth = width;
        mapHeight = height;
        scoreDoorOpen = scoreFinish;
        scoreTrophy = scoreGoal;

        // Create the correctly sized map array.
        layout = new int[mapWidth][mapHeight][3][3];
    }

    // Getters for the map class.
    public int getMapWidth(){
        return mapWidth;
    }
    public int getMapHeight(){
        return mapHeight;
    }
    public int getScoreDoorOpen(){
        return scoreDoorOpen;
    }
    public int getScoreTrophy(){
        return scoreTrophy;
    }

}
