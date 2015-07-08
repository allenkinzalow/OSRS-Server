package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/4/2015.
 */
public class SendChatMessage implements EncodedPacket {

    private int type;
    private String username;
    private String message;

    public SendChatMessage(String message) {
        this(0, null, message);
    }

    public SendChatMessage(int type, String message) {
        this(type, null, message);
    }

    public SendChatMessage(int type, String username, String message) {
        this.type = type;
        this.username = username;
        this.message = message;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(218).writeSize(RSBuffer.SizeType.BYTE);
        buff.writeSmart(type);
        boolean hasUser = username != null && username != "";
        buff.writeByte(hasUser? 1 : 0);
        if(hasUser)
            buff.writeString(username);
        buff.writeString(message);
        return buff;
    }

}
