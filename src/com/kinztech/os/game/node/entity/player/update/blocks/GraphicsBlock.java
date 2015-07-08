package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class GraphicsBlock implements UpdateBlock {

    int id;
    int height;
    int delay;

    public GraphicsBlock(int id, int height, int delay) {
        this.id = id;
        this.height = height;
        this.delay = delay;
    }

    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.GFX;
    }

    @Override
    public void encode(RSBuffer buffer) {
        buffer.writeShortA(id);
        buffer.writeInt(height << 16 | delay);
    }

}
