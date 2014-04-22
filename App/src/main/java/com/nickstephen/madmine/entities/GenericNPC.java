package com.nickstephen.madmine.entities;


/**
 * Created by Ben on 22/04/2014.
 */
public class GenericNPC extends GenericChar{
    protected int waitTime;                 // The ticks remaining for the NPC to wait before its next move.

    /**
     * A getter for the number of ticks that an NPC will be inactive before it attempts its next action.
     * @return Number of ticks to remain inactive.
     */
    public int getWaitTime(){
        return waitTime;
    }
}
