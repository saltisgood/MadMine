package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.map.Map;
import com.nickstephen.gamelib.util.Direction;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 23/04/2014.
 */
public class GenericItem extends GenericEntity{
    // TODO: Stevo make sure this is valid.

    GenericItem(@NotNull Map map, int x, int y) {
        super(map, x, y);
    }

    public void gravitate(){  // Sounds fucking bad-ass.
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.DOWN))) {
            move(this.mPos.getRelPos(Direction.DOWN));
        }
    }
}
