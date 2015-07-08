package com.kinztech.os.network.codec.game.decode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class ActionButtonDecoder implements DecodedPacket {

    @Override
    public void decode(Player player, RSBuffer in, PacketDefinition definition, int size) {
        try {
            int widgetHash = in.readInt();
            int item = in.readShort();
            int slot = in.readShort();

            if (item == 0xFFFF)
                item = -1;
            if (slot == 0xFFFF)
                slot = -1;

            System.out.println("Action button: " + (widgetHash >> 16) + "," + (widgetHash & 0xFFFF) + " Index: " + definition.getIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
