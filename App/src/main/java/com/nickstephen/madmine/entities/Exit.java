package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 18/05/2014.
 */
public class Exit extends GenericEntity {
    Exit(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG,
                ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        ((AnimatedSprite)mShape).gotoFrame(2);
        onMove();
    }
}
