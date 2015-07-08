package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class PlayerUpdateEncoder implements EncodedPacket {

    public RSBuffer buffer;

    public PlayerUpdateEncoder(RSBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public RSBuffer encode(Player player) {
        return buffer;
    }

}
