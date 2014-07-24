package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.shapes.Shape;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Position;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public abstract class GenericEntity {
    private static final short BLOCK = 0x1;
    private static final short PLAYER = 0x2;
    private static final short ENEMY = 0x4;
    private static final short ITEM = 0x8;
    private static final short EXIT = 0x10;

    public static GenericEntity create(@NotNull Context context, @NotNull Map map, int x, int y, short type, short subtype) {
        switch (type) {
            case BLOCK:
                switch (subtype) {
                    case Wall.SUBTYPE:
                        return new Wall(context, map, x, y);

                    case Dirt.SUBTYPE:
                    default:
                        return new Dirt(context, map, x, y);
                }

            case PLAYER:
                PlayerChar player = new PlayerChar(context, map, x, y);
                return map.setPlayer(player) ? player : null;

            case ENEMY:
                switch (subtype) {
                    case Spider.SUBTYPE:
                    default:
                        return new Spider(context, map, x, y);
                }

            case ITEM:
                switch (subtype) {
                    case Bomb.SUBTYPE:
                        return new Bomb(context, map, x, y);

                    case Diamond.SUBTYPE:
                        return new Diamond(context, map, x, y);

                    case Boulder.SUBTYPE:
                        return new Boulder(context, map, x, y);

                    case Gem.SUBTYPE:
                    default:
                        return new Gem(context, map, x, y);
                }

            case EXIT:
                Exit exit = new Exit(context, map, x, y);
                return map.setExit(exit) ? exit : null;
        }
        return null;
    }

    // TODO: Stevo, should this be protected or private?
    protected Position mPos;
    protected final Map mMap;
    protected Shape mShape;

    GenericEntity(@NotNull Map map, int x, int y) {
        mMap = map;
        mPos = new Position(x, y);
    }

    /**
     * Moves an entity to a new position by altering the position value of the entity and also by altering the map to account for its movement.
     * @param newPosition The position to which the entity will be moved.
     */
    public void move(Position newPosition){
        // Changes the entity's position.
        if (!mPos.equals(newPosition)) { // Only change positions if the position is different
            this.mPos = newPosition;
            onMove();
            // TODO: Interact with the map.
        }
    }

    public void moveX(int dx) {
        if (dx != 0) {
            mPos.xPos += dx;
            onMove();
        }
    }

    public void moveY(int dy) {
        if (dy != 0) {
            mPos.yPos += dy;
            onMove();
        }
    }

    protected void onMove() {
        mShape.moveTo(mPos.getPixelPositionX(mMap.getMapWidth()), mPos.getPixelPositionY(mMap.getMapHeight()));
    }

    public void onTick(){

    }

    /**
     * This is the canEntityMoveOnto for a generic entity.
     * It should be overridden for each actual entity so that they can move (other than walls, etc obviously because they don't move).
     * @return Whether the entity can move onto another entity type or not.
     */
    public boolean canEntityMoveOnto(GenericEntity entity){
        return false;
    }


    public boolean collideWith(@NotNull GenericEntity entity) {
        return false;
    }

    /**
     * A getter for the position of the entity.
     * @return The Position value of the entity.
     */
    public Position getPos(){
        return mPos;
    }

    public Shape getShape() {
        return mShape;
    }
}
