package com.nickstephen.madmine.entities;

import android.content.Context;

import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.interfaces.IDraw;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.util.Position;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 22/04/2014.
 */
public abstract class GenericEntity implements IDraw {
    private static final short BLOCK = 0x1;
    private static final short PLAYER = 0x2;
    private static final short ENEMY = 0x4;
    private static final short ITEM = 0x8;

    public static GenericEntity create(@NotNull Context context, @NotNull Container parent, @NotNull Map map, int x, int y, short type, short subtype) {
        switch (type) {
            case BLOCK:
                switch (subtype) {
                    case Wall.SUBTYPE:
                        return new Wall(map, x, y);

                    case Dirt.SUBTYPE:
                    default:
                        return new Dirt(map, x, y);
                }

            case PLAYER:
                PlayerChar player = new PlayerChar(context, parent, map, x, y);
                if (map.setPlayer(player)) {
                    return player;
                } else {
                    return null;
                }

            case ENEMY:
                switch (subtype) {
                    case Spider.SUBTYPE:
                    default:
                        return new Spider(map, x, y);
                }

            case ITEM:
                switch (subtype) {
                    case Bomb.SUBTYPE:
                        return new Bomb(map, x, y);

                    case Diamond.SUBTYPE:
                        return new Diamond(map, x, y);

                    case Boulder.SUBTYPE:
                        return new Boulder(map, x, y);

                    case Gem.SUBTYPE:
                    default:
                        return new Gem(map, x, y);
                }

        }
        return null;
    }

    // TODO: Stevo, should this be protected or private?
    protected Position mPos;
    protected final Map mMap;

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
        this.mPos = newPosition;
        // TODO: Interact with the map.
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


    /**
     * A getter for the position of the entity.
     * @return The Position value of the entity.
     */
    public Position getPos(){
        return mPos;
    }

    @Override
    public void draw(@NotNull float[] vpMatrix) {
        // TODO: Remove this and override in subclasses to make abstract, or do something else
    }

    // TODO: Make abstract
    public Shape getShape() { return null; }
}
