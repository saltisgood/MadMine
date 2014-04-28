package com.nickstephen.madmine.map;

import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.entities.GenericEntity;
import com.nickstephen.madmine.entities.PlayerChar;
import com.nickstephen.madmine.util.Position;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Ben on 24/04/2014.
 */
public final class Map {
    // NOTE: The matrix for the map is in the form [height][width] for clarity.
    // TODO: Complete the map class.

    static final int MAP_MAGIC_NO = 0x54474D50; // TGMP //

    private static final int BLOCK_DIVISOR = 3;

    private final int mMapWidth;         // Number of blocks wide the map is.
    private final int mMapHeight;        // Number of blocks high the map is.
    private final int mScoreDoorOpen;    // Score required to open the door.
    private final int mScoreTrophy;      // Score required to get the trophy for this map.
                                        // The number of squares up and across that the blocks will be divided into.
                                        // NOTE: This is also the number of steps in the animation to move an entity.

    private final char mMapVersionNo;
    private List<GenericEntity> mEntities;
    private PlayerChar mPlayer;

    GenericEntity[][][][] mLayout;

    // Constructor for the map class.
    Map(int width, int height, int scoreFinish, int scoreGoal, char version){
        // Set the width, height, and score thresholds for the map.
        mMapWidth = width;
        mMapHeight = height;
        mScoreDoorOpen = scoreFinish;
        mScoreTrophy = scoreGoal;
        mMapVersionNo = version;

        // Create the correctly sized map array as per above note.
        mLayout = new GenericEntity[mMapHeight][mMapWidth][BLOCK_DIVISOR][BLOCK_DIVISOR];
    }

    // Returns the class of entity present at a location if it is full - null otherwise.

    /**
     * Checks if there is an entity that fully spans a block.
     * @param position The position to check.
     * @return The entity that spans the block, or null if no single entity spans the full block (or if it is empty).
     */
    public GenericEntity whatIsHere(Position position){
        // TODO: Work out if this is even necessary or if it is a waste of space.
        GenericEntity[][] block = this.mLayout[position.yPos][position.xPos];
        GenericEntity topLeft = block[0][0];
        for (int i = 0; i < BLOCK_DIVISOR; i++){
            for (int j = 0; j < BLOCK_DIVISOR; j++){
                if (topLeft != block[i][j])
                    return null;
            }
        }
        return topLeft;
    }

    public boolean moveEntity(GenericEntity entity, Position oldPos, Position newPos){
        // Check to make sure the entity on the map corresponds with the entity being moved.
        if(whatIsHere(oldPos) == entity){
            // Check the direction it should move to.
            // Put the 3 new entity references into the side of the new block.
            // Remove the 3 old entity references from the side of the old block.
            // Return true to indicate that it's happened.
            return true;
        }
        return false;
    }



    // Checks if a space is completely empty, mainly for NPC AI purposes.
    // Even if we end up having spiders able to collide and reverse - consider the case of
    // having rocks falling into each other and bouncing off.
    public boolean isSpaceEmpty(Position position){
        for (int i = 0; i < BLOCK_DIVISOR; i++){
            for (int j = 0; j < BLOCK_DIVISOR; j++){
                if (this.mLayout[position.yPos][position.xPos][i][j] != null)
                    return false;
            }
        }
        return true;
    }

    void setEntityAtPosition(GenericEntity entity, int x, int y) {
        for (int i = 0; i < BLOCK_DIVISOR; i++) {
            for (int j = 0; j < BLOCK_DIVISOR; j++) {
                mLayout[y][x][i][j] = entity;
            }
        }
    }

    public final boolean setPlayer(@NotNull PlayerChar player) {
        if (mPlayer != null) {
            return false;
        } else {
            mPlayer = player;
            return true;
        }
    }

    boolean setup(@NotNull List<GenericEntity> entities) {
        mEntities = entities;
        if (mPlayer == null) {
            Twig.debug("MapLoader", "No player character detected!");
            return false;
        }
        return true;
    }

    // Getters for the map class.
    public final int getMapWidth(){
        return mMapWidth;
    }

    public final int getMapHeight(){
        return mMapHeight;
    }

    public final int getScoreDoorOpen(){
        return mScoreDoorOpen;
    }

    public final int getScoreTrophy(){
        return mScoreTrophy;
    }

}
