package com.nickstephen.madmine.util;

/**
 * Created by Ben on 22/04/2014.
 */
public enum Direction {
    UP, RIGHT, LEFT, DOWN;

    public Direction rotateClockwise(){
        // TODO: Does this work in the way I think it does?
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
        // TODO: Does this work in the way I think it does?
        switch(this){
            case UP:
                return LEFT;
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT:
            case RIGHT:
                return UP;
        }
        return null;
    }

    public Direction flip(){
        // TODO: Does this work in the way I think it does?
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
