package com.nickstephen.madmine.entities;


import android.content.Context;

import com.nickstephen.gamelib.opengl.shapes.AnimatedSprite;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.gamelib.util.Direction;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Dirt extends GenericEntity {
    static final short SUBTYPE = 0x1;

    protected boolean mSpaceUp = false;
    protected boolean mSpaceRight = false;
    protected boolean mSpaceDown = false;
    protected boolean mSpaceLeft = false;

    // TODO: Dirt class.

    Dirt(@NotNull Context context, @NotNull Map map, int x, int y) {
        super (map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG, ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        ((AnimatedSprite)mShape).gotoFrame(5);
        onMove();
    }

    @Override
    public void onTick(){
        refreshDirt();
    }

    /**
     * Checks to see if the dirt image should be adjusted based on a change in the blocks surrounding the dirt.
     * For example, if the player digs out the block underneath, the dirt
     * image should change to be rough on the underside.
     */
    private void refreshDirt(){
        // TODO: Maybe just put this inside onTick() in dirt.java given that it might be all it does as per Stevo.
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.UP)) != mSpaceUp) {
            mSpaceUp = !mSpaceUp;
        }
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.RIGHT)) != mSpaceRight) {
            mSpaceRight = !mSpaceRight;
        }
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.DOWN)) != mSpaceDown) {
            mSpaceDown = !mSpaceDown;
        }
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.LEFT)) != mSpaceLeft) {
            mSpaceLeft = !mSpaceLeft;
        }

        // TODO: Add some sort of refresh for the dirt sprite - only after all four of those if statements have been executed.
    }

    @Override
    public boolean canEntityMoveOnto(GenericEntity entity) {
        if (entity instanceof PlayerChar) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean collideWith(@NotNull GenericEntity entity) {
        if (entity instanceof PlayerChar) {
            mShape.removeFromParent();
            mShape.dispose();
            return true;
        }
        return super.collideWith(entity);
    }
}
