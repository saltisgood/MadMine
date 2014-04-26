package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 23/04/2014.
 */
public class GenericItem extends GenericEntity{
    // TODO: Stevo make sure this is valid.

    public void gravitate(){  // Sounds fucking bad-ass.
        if (this.mPos.getRelPos(Direction.DOWN).emptySpace()) {
            move(this.mPos.getRelPos(Direction.DOWN));
        }
    }
}
