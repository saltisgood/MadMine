package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.shapes.AnimatedSprite;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Diamond extends GenericItem {
    static final short SUBTYPE = 0x2;
    // TODO: Diamond class.

    Diamond(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG, ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        ((AnimatedSprite)mShape).gotoFrame(1);
        onMove();
    }

    @Override
    public boolean canEntityMoveOnto(GenericEntity entity) {
        if (entity instanceof PlayerChar) {
            return true;
        }

        return super.canEntityMoveOnto(entity);
    }

    @Override
    public boolean collideWith(@NotNull GenericEntity entity) {
        if (entity instanceof PlayerChar) {
            mMap.collectDiamond();
            mShape.removeFromParent();
            mShape.dispose();

            return true;
        }

        return false;
    }
}
