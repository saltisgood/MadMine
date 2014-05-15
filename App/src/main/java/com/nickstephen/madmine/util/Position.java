package com.nickstephen.madmine.util;

import com.nickstephen.gamelib.util.Direction;

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

    private float getPixelPosition(int currentPosition, int maxPosition) {
        int mid = maxPosition / 2;
        float dist;
        int diff = currentPosition - mid;
        if (maxPosition % 2 == 0) {
            dist = diff + 0.5f;
        } else {
            if (diff == 0) {
                dist = 0.f;
            } else {
                dist = diff + ((diff < 0) ? -0.5f : 0.5f);
            }
        }

        return dist * ViewScaling.getBlockPixelSize();
    }

    public float getPixelPositionX(int mapWidth) {
        return getPixelPosition(xPos, mapWidth);
    }

    public float getPixelPositionY(int mapHeight) {
        return -getPixelPosition(yPos, mapHeight);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + xPos;
        result = result * 23 + yPos;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position)o;
            if (xPos == p.xPos && yPos == p.yPos) {
                return true;
            }
        }
        return false;
    }
}
