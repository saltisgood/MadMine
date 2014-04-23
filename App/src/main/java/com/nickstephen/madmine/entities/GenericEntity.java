package com.nickstephen.madmine.entities;

import com.nickstephen.madmine.util.Position;

/**
 * Created by Ben on 22/04/2014.
 */
public class GenericEntity {
    protected Position pos;

    /**
     * Moves an entity to a new position by altering the position value of the entity and also by altering the map to account for its movement.
     * @param movingEntity The entity that is going to be moved.
     * @param newPosition The position to which the entity will be moved.
     */
    public void moveEntity(GenericEntity movingEntity, Position newPosition){
        // Changes the entity's position.
        movingEntity.pos = newPosition;
        // TODO: Interact with the map.
    }


    /**
     * A getter for the position of the entity.
     * @return The Position value of the entity.
     */
    public Position getPos(){
        return pos;
    }
}
