package com.kinztech.os.game.node.entity.npc.update;

import com.kinztech.os.game.node.entity.UpdateMask;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public enum NPCUpdateMask implements UpdateMask {

    ANIMATION(1,1);

    private int mask;

    private int order;

    NPCUpdateMask(int mask, int order) {
        this.mask = mask;
        this.order = order;
    }

    public int getMask() {
        return mask;
    }

    public int getOrder() {
        return order;
    }

}
