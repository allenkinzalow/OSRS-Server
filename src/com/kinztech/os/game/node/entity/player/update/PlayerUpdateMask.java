package com.kinztech.os.game.node.entity.player.update;

import com.kinztech.os.game.node.entity.UpdateMask;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public enum PlayerUpdateMask implements UpdateMask {

    APPEARANCE(0x32, 5),
    ANIMATION(0x8, 0),
    ENTITY_INTERACT(0x2, 1),
    FORCE_MOVEMENT(0x256, 8),
    FORCE_TEXT(0x16, 4),
    GFX(0x512, 7),
    HIT_PRIMARY(0x1, 9),
    HIT_SECONDARY(0x1024, 3),
    PUBLIC_CHAT(0x4, 2),
    TURN_TO(0x128, 6);

    private int mask;

    private int order;

    PlayerUpdateMask(int mask, int order) {
        this.mask = mask;
        this.order = order;
    }

    public int getMask(){
        return mask;
    }

    public int getOrder() { return order; }

}
