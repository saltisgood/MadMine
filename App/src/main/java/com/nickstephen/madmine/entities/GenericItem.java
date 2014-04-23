package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 23/04/2014.
 */
public class GenericItem extends GenericEntity{
    // TODO: Stevo make sure this is valid.

    public void gravity(){
        if (this.pos.getRelPos(Direction.DOWN).emptySpace()) {
            moveEntity(this, this.pos.getRelPos(Direction.DOWN));
        }
    }
}
