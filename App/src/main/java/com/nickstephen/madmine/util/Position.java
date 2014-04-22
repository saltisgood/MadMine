package com.nickstephen.madmine.util;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public class Position {
    public int xPos;
    public int yPos;

    public Position(int x, int y){
        xPos = x;
        yPos = y;
    }

    /**
     * Provides a way of finding out what is contained in the
     * @param dir
     * @return
     */
    public Position getRelPos(@NotNull Direction dir){
        switch (dir){
            case UP:
                return new Position(xPos, yPos + 1);
            case DOWN:
                return new Position(xPos, yPos - 1);
            case LEFT:
                return new Position(xPos - 1, yPos);
            case RIGHT:
                return new Position(xPos + 1, yPos);
        }
        return null;
    }
}
