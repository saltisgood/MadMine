package com.nickstephen.madmine.entities;


import com.nickstephen.madmine.map.Map;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericNPC extends GenericChar{
    protected int mWaitTime;                 // The ticks remaining for the NPC to wait before its next move.

    GenericNPC(@NotNull Map map, int x, int y) {
        super(map, x, y);
    }

    /**
     * A getter for the number of ticks that an NPC will be inactive before it attempts its next action.
     * @return Number of ticks to remain inactive.
     */
    public int getWaitTime(){
        return mWaitTime;
    }
}
