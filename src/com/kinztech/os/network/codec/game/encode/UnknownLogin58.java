package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/7/2015.
 */
public class UnknownLogin58 implements EncodedPacket {

    int i1, i2;

    public UnknownLogin58(int i1, int i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(58);
        buff.writeLEInt(i1);
        buff.writeLEInt(i2);
        return buff;
    }
}
