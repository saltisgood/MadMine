package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.madmine.map.Map;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Wall extends GenericEntity {
    static final short SUBTYPE = 0x2;

    // TODO: Wall class.

    Wall(@NotNull Context context, @NotNull Container parent, @NotNull Map map, int x, int y) {
        super(map, x, y);
    }
}
