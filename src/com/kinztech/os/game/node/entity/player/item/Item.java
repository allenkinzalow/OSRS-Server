package com.kinztech.os.game.node.entity.player.item;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class Item {

    /**
     * ID
     */
    private int itemID;

    /**
     * Amount
     */
    private int amount;

    public Item(int itemID, int amount) {
        this.itemID = itemID;
        this.amount = amount;
    }

    public Item(int itemID) {
        this(itemID, 1);
    }

    public int getID() {
        return itemID;
    }

    public int getAmount() {
        return amount;
    }

}
