package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class ForceTextBlock implements UpdateBlock {

    private String text;

    public ForceTextBlock(String text) {
        this.text = text;
    }

    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.FORCE_TEXT;
    }

    @Override
    public void encode(RSBuffer buffer) {
        buffer.writeString(text);
    }

}
