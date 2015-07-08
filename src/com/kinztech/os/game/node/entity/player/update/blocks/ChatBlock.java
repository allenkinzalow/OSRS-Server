package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 6/5/2015.
 */
public class ChatBlock implements UpdateBlock {
    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.PUBLIC_CHAT;
    }

    @Override
    public void encode(RSBuffer buffer) {
        //shortA - chat effects
        //byteA - chat icon
        //byteA - message type (boolean)
        //Nbyte - text length

    }
}
