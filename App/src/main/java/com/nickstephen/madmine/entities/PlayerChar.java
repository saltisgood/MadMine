package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.gamelib.opengl.Quadrilateral;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class PlayerChar extends GenericChar {
    protected Shape mShape;

    // TODO: Player class.
    PlayerChar(@NotNull Context context, @NotNull Container parent, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, parent, Constants.Textures.IMG, ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        onMove();
    }

    @Override
    public Shape getShape() {
        return mShape;
    }

    @Override
    protected void onMove() {
        mShape.moveTo(mPos.getPixelPositionX(mMap.getMapWidth()), mPos.getPixelPositionY(mMap.getMapHeight()));
    }
}
