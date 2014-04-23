package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Direction;

/**
 * Created by Ben on 22/04/2014.
 */
public class Bomb extends GenericItem {
    private boolean primed = false;

    /**
     * Executes on every tick.
     */
    public void onTick(){
        // TODO: Stevo make sure this is valid.
        // Explodes if it has fallen onto something.
        if(primed && !(pos.getRelPos(Direction.DOWN).emptySpace())){
            // TODO: Explosion
            // Explode 3x3 area.

            // TODO: Destroy bomb instance.
            // Bomb gets destroyed.

        }
        // Falls down into holes if required.
        this.gravity();
    }

    @Override
    public void gravity(){
        // TODO: Stevo make sure this is valid.
        if (this.pos.getRelPos(Direction.DOWN).emptySpace()) {
            primed = true;
            moveEntity(this, this.pos.getRelPos(Direction.DOWN));
        }
    }
}
