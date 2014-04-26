package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class Bomb extends GenericItem {
    private boolean mPrimed = false;

    /**
     * Executes on every tick.
     */
    @Override
    public void onTick(){
        // TODO: Stevo make sure this is valid.
        // Explodes if it has fallen onto something.
        if(mPrimed && !(mPos.getRelPos(Direction.DOWN).emptySpace())){
            // TODO: Explosion
            // Explode 3x3 area.

            // TODO: Destroy bomb instance.
            // Bomb gets destroyed.

        }
        // Falls down into holes if required.
        this.gravitate();
    }

    @Override
    public void gravitate(){
        // TODO: Stevo make sure this is valid.
        if (this.mPos.getRelPos(Direction.DOWN).emptySpace()) {
            mPrimed = true;
            move(this.mPos.getRelPos(Direction.DOWN));
        }
    }
}
