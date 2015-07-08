package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class AnimationBlock implements UpdateBlock {

    int animationId;

    public AnimationBlock(int animationId) {
        this.animationId = animationId;
    }

    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.ANIMATION;
    }

    @Override
    public void encode(RSBuffer buffer) {
        buffer.writeShortA(animationId);
        buffer.writeByte(0);
    }

}
