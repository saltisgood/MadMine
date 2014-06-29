package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.ViewScaling;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Boulder extends GenericItem {
    static final short SUBTYPE = 0x8;
    // TODO: Boulder class.

    Boulder(@NotNull Context context, @NotNull Map map, int x, int y) {
        super(map, x, y);

        mShape = new AnimatedSprite(context, map.getContainer(), Constants.Textures.IMG,
                ViewScaling.getBlockPixelSize(), ViewScaling.getBlockPixelSize(), 4, 4);
        ((AnimatedSprite)mShape).gotoFrame(14);
        onMove();
    }
}
