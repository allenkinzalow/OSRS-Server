package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.game.node.entity.player.varp.Varp;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 5/31/2015.
 */
public class VarpEncoder implements EncodedPacket {

    private Varp varp;

    public VarpEncoder(Varp varp) {
        this.varp = varp;
    }

    @Override
    public RSBuffer encode(Player player) {
        boolean big = varp.getValue() <= Byte.MIN_VALUE || varp.getValue() >= Byte.MAX_VALUE;

        RSBuffer buffer = new RSBuffer(Unpooled.buffer());
        buffer.packet(big ? 127 : 196);

        System.out.println("Writing Packet: " + buffer.packet() + " " + varp.getId() + " , " + varp.getValue());

        if(big) {
            buffer.writeLEInt(varp.getValue());
            buffer.writeLEShortA(varp.getId());
        } else {
            buffer.writeByteA(varp.getValue());
            buffer.writeLEShort(varp.getId());
        }

        return buffer;
    }

}
