package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.map.Map;
import com.nickstephen.gamelib.util.Direction;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public abstract class GenericChar extends GenericEntity {
    protected Direction mDirectionFacing = Direction.RIGHT;    // The direction in which the NPC/Character is facing.

    GenericChar(@NotNull Map map, int x, int y) {
        super(map, x, y);
    }

    /**
     * A getter for the direction that an NPC is facing.
     * @return The direction that the NPC is facing.
     */
    public Direction getDirectionFacing(){
        return mDirectionFacing;
    }

}
