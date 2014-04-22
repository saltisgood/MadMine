package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericNPC extends GenericEntity{
    protected Direction directionFacing;    // The direction in which the NPC is facing.
    protected int waitTime;                 // The ticks remaining for the NPC to wait before its next move.


    /**
     * A getter for the direction that an NPC is facing.
     * @return The direction that the NPC is facing.
     */
    public Direction getDirectionFacing(){
        return directionFacing;
    }

    /**
     * A getter for the number of ticks that an NPC will be inactive before it attempts its next action.
     * @return Number of ticks to remain inactive.
     */
    public int getWaitTime(){
        return waitTime;
    }
}
