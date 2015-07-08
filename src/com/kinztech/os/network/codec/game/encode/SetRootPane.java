package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class SetRootPane implements EncodedPacket {

    private int paneId;

    public SetRootPane(int paneId) {
        this.paneId = paneId;
    }

    @Override
    public RSBuffer encode(Player player) {
        System.out.println("Setting Root Pane: " + paneId);
        RSBuffer buffer = new RSBuffer(Unpooled.buffer());

        buffer.packet(248);
        buffer.writeShortA(paneId);

        return buffer;
    }

}
