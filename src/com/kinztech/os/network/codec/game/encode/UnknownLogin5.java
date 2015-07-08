package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/7/2015.
 */
public class UnknownLogin5 implements EncodedPacket {

    int u1;
    int u2;

    public UnknownLogin5(int u1, int u2) {
        this.u1 = u1;
        this.u2 = u2;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(220);
        buff.writeByteA(u1);
        buff.writeByte(u2);
        return buff;
    }

}
