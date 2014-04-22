package com.nickstephen.madmine.util;

/**
 * Created by Ben on 22/04/2014.
 */
public enum Direction {
    UP, RIGHT, LEFT, DOWN;

    public Direction rotateClockwise(){
        // TODO: Make sure this works in the intended fashion.
        switch(this){
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
        }
        return null;
    }

    public Direction rotateAntiClockwise(){
        // TODO: Make sure this works in the intended fashion.
        switch(this){
            case UP:
                return LEFT;
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case RIGHT:
                return UP;
        }
        return null;
    }

    public Direction flip(){
        // TODO: Make sure this works in the intended fashion.
        switch (this){
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return null;
    }
}
