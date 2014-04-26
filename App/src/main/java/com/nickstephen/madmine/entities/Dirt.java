package com.nickstephen.madmine.entities;


import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class Dirt extends GenericEntity {
    protected boolean mSpaceUp = false;
    protected boolean mSpaceRight = false;
    protected boolean mSpaceDown = false;
    protected boolean mSpaceLeft = false;

    // TODO: Dirt class.

    @Override
    public void onTick(){
        refreshDirt();
    }

    /**
     * Checks to see if the dirt image should be adjusted based on a change in the blocks surrounding the dirt.
     * For example, if the player digs out the block underneath, the dirt
     * image should change to be rough on the underside.
     */
    private void refreshDirt(){
        // TODO: Maybe just put this inside onTick() in dirt.java given that it might be all it does as per Stevo.
        if ((this.mPos.getRelPos(Direction.UP).emptySpace() != mSpaceUp))
            mSpaceUp = !mSpaceUp;
        if ((this.mPos.getRelPos(Direction.RIGHT).emptySpace() != mSpaceRight))
            mSpaceRight = !mSpaceRight;
        if ((this.mPos.getRelPos(Direction.DOWN).emptySpace() != mSpaceDown))
            mSpaceDown = !mSpaceDown;
        if ((this.mPos.getRelPos(Direction.LEFT).emptySpace() != mSpaceLeft))
            mSpaceLeft = !mSpaceLeft;

        // TODO: Add some sort of refresh for the dirt sprite - only after all four of those if statements have been executed.
    }
}
