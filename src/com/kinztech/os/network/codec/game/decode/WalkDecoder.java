package com.kinztech.os.network.codec.game.decode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class WalkDecoder implements DecodedPacket {
    @Override
    public void decode(Player player, RSBuffer in, PacketDefinition definition, int size) {

        int y = in.readULEShortA();
        boolean run = in.readByteN() == 1;
        int x = in.readShort();

        System.out.println("Walk: " + x + "," + y);

    }
}
