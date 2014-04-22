package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Position;

/**
 * Created by Ben on 22/04/2014.
 */
public class Spider extends GenericNPC{
    // TODO: Spider class.
    void moveAttempt(){
        // TODO: Spider movement.
        /*
        The spider should move anticlockwise around a space.
        From where it is placed, the spider should walk one step forwards, then wait 2/3 sec.
        If the spider can't walk forwards, it should turn right, then wait 2/3 sec.
         */

        Position desiredPos = this.pos.getRelPos(directionFacing);
        if (spaceAvailable(desiredPos)) { // This is meant to check to see if the space can be moved into.
            // moveEntity(this, desiredPos);
        }
        else {
            // TODO: Will this turn the spider, or do I have to go this.directionFacing = this.directionFacing.rotateClockwise()?
            this.directionFacing.rotateClockwise();
        }


    }

    boolean spaceAvailable(Position newPos){
        // TODO: This is a placeholder method.
        return true;
    }

}
