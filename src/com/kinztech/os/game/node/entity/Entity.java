package com.kinztech.os.game.node.entity;

import com.kinztech.os.game.node.Node;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public abstract class Entity extends Node {

    /**
     * The entity's index
     */
    private int index = -1;

    /**
     * The entity's walking queue.
     */
    private WalkingQueue walkingQueue = new WalkingQueue();

    /**
     * Set the entity index in the world.
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retrieve the entity index in the world.
     * @return
     */
    public int getIndex() {
        return this.index;
    }

    public WalkingQueue getWalkingQueue() {
        return walkingQueue;
    }

    /**
     * Retrieve the entity's update container.
     * @return
     */
    public abstract UpdateContainer<? extends Entity> getUpdateContainer();

}
