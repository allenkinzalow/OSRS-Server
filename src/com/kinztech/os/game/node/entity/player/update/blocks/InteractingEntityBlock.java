package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 6/22/2015.
 */
public class InteractingEntityBlock implements UpdateBlock {

    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.ENTITY_INTERACT;
    }

    @Override
    public void encode(RSBuffer buffer) {

    }

}
