package com.nickstephen.madmine.entities;


import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class Dirt extends GenericEntity {
    boolean spaceUp = false;
    boolean spaceRight = false;
    boolean spaceDown = false;
    boolean spaceLeft = false;

    // TODO: Dirt class.

    /**
     * Checks to see if the dirt image should be adjusted based on a change in the blocks surrounding the dirt.
     * For example, if the player digs out the block underneath, the dirt
     * image should change to be rough on the underside.
     */
    public void refreshDirt(){
        // TODO: Stevo check that this is valid.
        if ((this.pos.getRelPos(Direction.UP).emptySpace() != spaceUp))
            spaceUp = !spaceUp;
        if ((this.pos.getRelPos(Direction.RIGHT).emptySpace() != spaceRight))
            spaceRight = !spaceRight;
        if ((this.pos.getRelPos(Direction.DOWN).emptySpace() != spaceDown))
            spaceDown = !spaceDown;
        if ((this.pos.getRelPos(Direction.LEFT).emptySpace() != spaceLeft))
            spaceLeft = !spaceLeft;

        // TODO: Add some sort of refresh for the dirt sprite - only after all four of those if statements have been executed.
    }
}
