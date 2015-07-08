package com.kinztech.os.network.codec.game;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.NetworkChannelHandler;
import com.kinztech.os.network.codec.game.encode.EncodedPacket;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 * Credit to Bart
 */
public class GamePacketEncoder extends MessageToByteEncoder<EncodedPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, EncodedPacket ep, ByteBuf out) throws Exception {
        try {
            Player player = ctx.channel().attr(NetworkChannelHandler.ATTRIB_PLAYER).get();
            RSBuffer buffer = ep.encode(player);
            buffer.finish();
            //System.out.println("Encoding Packet: " + buffer.packet() + " Size: " + buffer.get().readableBytes());
            out.writeByte((byte) buffer.packet() + player.getOutrand().nextInt());
            out.writeBytes(buffer.get());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
