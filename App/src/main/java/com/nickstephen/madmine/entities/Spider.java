package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.Position;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Spider extends GenericNPC{
    static final short SUBTYPE = 0x1;

    Spider(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG,
                ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(),
                4, 4);
        ((AnimatedSprite)mShape).gotoFrame(9);
        onMove();
    }

    @Override
    public void onTick(){
        // TODO: Kill player?  I think this is done elsewhere though so maybe not required here.
        moveAttempt();
    }

    void moveAttempt(){
        // TODO: Spider movement.
        /*
        The spider should move anticlockwise around a space.
        From where it is placed, the spider should walk one step forwards, then wait 2/3 sec.
        If the spider can't walk forwards, it should turn right, then wait 2/3 sec.
         */

        // Sets the desired position to be the space directly in front of the spider.
        Position desiredPos = mPos.getRelPos(mDirectionFacing);
        if (mMap.isSpaceEmpty(desiredPos)) {
            // Moves the spider forward one block if there is room.
            move(desiredPos);
        } else {
            // If there is no room for the spider to move forwards, it turns pi/2 clockwise.
            mDirectionFacing = mDirectionFacing.rotateClockwise();
        }
    }
}
