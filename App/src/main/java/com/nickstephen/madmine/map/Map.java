package com.nickstephen.madmine.map;

import com.nickstephen.madmine.entities.GenericEntity;
import com.nickstephen.madmine.util.Position;

/**
 * Created by Ben on 24/04/2014.
 */
public class Map {
    // NOTE: The matrix for the map is in the form [height][width] for calculation speed benefit.
    // TODO: Complete the map class.

    private final int mapWidth;         // Number of blocks wide the map is.
    private final int mapHeight;        // Number of blocks high the map is.
    private final int scoreDoorOpen;    // Score required to open the door.
    private final int scoreTrophy;      // Score required to get the trophy for this map.
    private final int blockDivisor = 3; // The number of squares up and across that the blocks will be divided into.
                                        // NOTE: This is also the number of steps in the animation to move an entity.

    private GenericEntity[][][][] layout;

    // Constructor for the map class.
    public Map(int width, int height, int scoreFinish, int scoreGoal){
        // Set the width, height, and score thresholds for the map.
        mapWidth = width;
        mapHeight = height;
        scoreDoorOpen = scoreFinish;
        scoreTrophy = scoreGoal;

        // Create the correctly sized map array as per above note.
        layout = new GenericEntity[mapHeight][mapWidth][blockDivisor][blockDivisor];
    }

    // Returns the class of entity present at a location if it is full - null otherwise.

    /**
     * Checks if there is an entity that fully spans a block.
     * @param position The position to check.
     * @return The entity that spans the block, or null if no single entity spans the full block (or if it is empty).
     */
    public GenericEntity whatIsHere(Position position){
        GenericEntity[][] block = this.layout[position.yPos][position.xPos];
        GenericEntity topLeft = block[0][0];
        for (int i = 0; i < blockDivisor; i++){
            for (int j = 0; j < blockDivisor; j++){
                if (topLeft != block[i][j])
                    return null;
            }
        }
        return topLeft;
    }



    // Checks if a space is completely empty, mainly for NPC AI purposes.
    public boolean isSpaceEmpty(Position position){
        for (int i = 0; i < blockDivisor; i++){
            for (int j = 0; j < blockDivisor; j++){
                if (this.layout[position.yPos][position.xPos][i][j] != null)
                    return false;
            }
        }
        return true;
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
