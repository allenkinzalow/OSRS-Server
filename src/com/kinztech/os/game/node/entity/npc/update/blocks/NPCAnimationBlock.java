package com.kinztech.os.game.node.entity.npc.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.npc.update.NPCUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class NPCAnimationBlock implements UpdateBlock {
    @Override
    public UpdateMask getUpdateMask() {
        return NPCUpdateMask.ANIMATION;
    }

    @Override
    public void encode(RSBuffer buffer) {

    }
}
