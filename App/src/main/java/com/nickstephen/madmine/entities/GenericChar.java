package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericChar extends GenericEntity {
    protected Direction directionFacing;    // The direction in which the NPC is facing.


    /**
     * A getter for the direction that an NPC is facing.
     * @return The direction that the NPC is facing.
     */
    public Direction getDirectionFacing(){
        return directionFacing;
    }

}
