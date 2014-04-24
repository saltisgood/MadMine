package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Position;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericEntity {
    // TODO: Stevo, should this be protected or private?
    protected Position pos;

    /**
     * Moves an entity to a new position by altering the position value of the entity and also by altering the map to account for its movement.
     * @param movingEntity The entity that is going to be moved.
     * @param newPosition The position to which the entity will be moved.
     */
    public void moveEntity(GenericEntity movingEntity, Position newPosition){
        // Changes the entity's position.
        movingEntity.pos = newPosition; // TODO: Stevo, I think this should use a setter instead of the way that I have it right now?
        // TODO: Interact with the map.
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
        return pos;
    }
}
