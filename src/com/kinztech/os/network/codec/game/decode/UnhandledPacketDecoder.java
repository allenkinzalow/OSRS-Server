package com.kinztech.os.network.codec.game.decode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class UnhandledPacketDecoder implements DecodedPacket {
    @Override
    public void decode(Player player, RSBuffer in, PacketDefinition definition, int size) {
        //if(definition.getPacketID() != 94 && definition.getPacketID() != 204)
            //System.out.println("Unhandled Packet: " + definition.getPacketID() + " Skipping: " + in.get().readableBytes());
        in.skip(size);
    }
}
