package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.map.Map;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Diamond extends GenericItem {
    static final short SUBTYPE = 0x2;
    // TODO: Diamond class.

    Diamond(@NotNull Map map, int x, int y) {
        super(map, x, y);
    }
}
