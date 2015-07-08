package com.kinztech.os.network.codec.game.decode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 6/3/2015.
 */
public class MouseRecordingDecoder implements DecodedPacket {

    @Override
    public void decode(Player player, RSBuffer in, PacketDefinition definition, int size) {
        in.skip(size > in.get().readableBytes() ? in.get().readableBytes() : size);
    }

}
