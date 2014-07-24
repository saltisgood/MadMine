package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.shapes.AnimatedSprite;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.gamelib.util.Direction;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Bomb extends GenericItem {
    static final short SUBTYPE = 0x1;

    private boolean mPrimed = false;

    Bomb(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG,
                ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(),
                4, 4);
        ((AnimatedSprite)mShape).gotoFrame(8);
        onMove();
    }

    /**
     * Executes on every tick.
     */
    @Override
    public void onTick(){
        // TODO: Stevo make sure this is valid.
        // Explodes if it has fallen onto something.
        if (mPrimed && !mMap.isSpaceEmpty(mPos.getRelPos(Direction.DOWN))) {
            // TODO: Explosion
            // Explode 3x3 area.

            // TODO: Destroy bomb instance.
            // Bomb gets destroyed.
        }

        // Falls down into holes if required.
        gravitate();
    }

    @Override
    public void gravitate(){
        // TODO: Stevo make sure this is valid.
        if (mMap.isSpaceEmpty(mPos.getRelPos(Direction.DOWN))) {
            mPrimed = true;
            move(this.mPos.getRelPos(Direction.DOWN));
        }
    }
}
