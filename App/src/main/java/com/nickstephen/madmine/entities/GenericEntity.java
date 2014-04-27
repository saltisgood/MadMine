package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Position;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericEntity {
    private static final short PLAYER = 0x1;


    public static GenericEntity create(int x, int y, short type, short subtype) {
        return null;
    }

    public static PlayerChar createPlayer(int x, int y, short type) {
        return null;
    }

    public static boolean isPlayer(short type) {
        return type == PLAYER;
    }

    // TODO: Stevo, should this be protected or private?
    protected Position mPos;

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
}
