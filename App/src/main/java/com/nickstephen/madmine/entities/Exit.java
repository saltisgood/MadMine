package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.shapes.AnimatedSprite;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 18/05/2014.
 */
public class Exit extends GenericEntity {
    private boolean mOpen = false;

    Exit(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG,
                ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        ((AnimatedSprite)mShape).gotoFrame(2);
        onMove();
    }

    public void open() {
        if (!mOpen) {
            mOpen = true;

            ((AnimatedSprite) mShape).gotoFrame(6);
        }
    }

    public boolean isOpen() {
        return mOpen;
    }

    @Override
    public boolean canEntityMoveOnto(GenericEntity entity) {
        return true;
    }

    @Override
    public boolean collideWith(@NotNull GenericEntity entity) {
        if (mOpen && entity instanceof PlayerChar) {
            mMap.exit();
        }

        return false;
    }
}
