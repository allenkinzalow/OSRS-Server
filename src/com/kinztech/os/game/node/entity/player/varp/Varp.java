package com.kinztech.os.game.node.entity.player.varp;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 *
 * TODO: may change this to an enum and define varps, but it'd become messy
 */
public class Varp {

    /**
     * varp ID
     */
    int id;

    /**
     * varp value
     */
    int value;

    public Varp(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
