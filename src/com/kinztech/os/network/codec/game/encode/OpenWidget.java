package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 5/27/2015.
 */
public class OpenWidget implements EncodedPacket {

    private int id;
    private boolean walkable;
    private int target;
    private int targetChild;

    public OpenWidget(int id, int target, int targetChild, boolean walkable) {
        this.id = id;
        this.target = target;
        this.targetChild = targetChild;
        this.walkable = walkable;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());

        buff.packet(0);

        buff.writeByte(walkable ? 1 : 0);
        buff.writeShortA(id);
        buff.writeLEInt((target << 16) | targetChild);

        return buff;
    }

}
