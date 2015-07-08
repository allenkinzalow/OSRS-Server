package com.kinztech.os.game.node.entity.player.item.container;

import com.kinztech.os.game.node.entity.player.item.Item;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public abstract class ItemContainer {

    /**
     * Container size
     */
    private int size;

    /**
     * The items held within the container.
     */
    private Item[] items;

    public ItemContainer(int size) {
        this.size = size;
        items = new Item[size];
    }



}
