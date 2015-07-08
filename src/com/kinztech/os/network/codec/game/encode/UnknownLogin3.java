package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/3/2015.
 */
public class UnknownLogin3 implements EncodedPacket {

    private int i1, i2, i3,i4;

    public UnknownLogin3(int i1, int i2, int i3, int i4) {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(159);
        buff.writeInt(i1);
        buff.writeLEShortA(i2);
        buff.writeIntV1(i3);
        buff.writeLEShort(i4);
        return buff;
    }

}
