package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/3/2015.
 */
public class UnknownLogin4 implements EncodedPacket {
    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(23);
        buff.writeByte(0);
        return buff;
    }
}
