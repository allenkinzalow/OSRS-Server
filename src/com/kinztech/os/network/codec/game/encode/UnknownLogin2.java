package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 5/27/2015.
 */
public class UnknownLogin2 implements EncodedPacket {

    int id;

    public UnknownLogin2(int id) {
        this.id = id;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(247);
        buff.writeInt(id);
        return buff;
    }
}
