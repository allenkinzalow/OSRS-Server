package com.kinztech.os.network.codec.game;

import com.kinztech.os.GameServer;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.NetworkChannelHandler;
import com.kinztech.os.network.codec.game.decode.DecodedPacket;
import com.kinztech.os.network.codec.game.decode.UnhandledPacketDecoder;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 * Credit to Bart for some guidance ;)
 */
public class GamePacketDecoder extends ByteToMessageDecoder {

    /**
     * packetID
     */
    int packetId;

    /**
     * Size
     */
    int size;

    /**
     * Current def
     */
    PacketDefinition currentDefinition;

    /**
     * The package for incomming packet decoders defined by the packet definition
     */
    private final static String DECODER_PACKAGE = "com.kinztech.os.network.codec.game.decode";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        try {
            if (in.readableBytes() < 1) {
                System.out.println("Not enough readable bytes...");
                return;
            }
            in.resetReaderIndex();

            Player player = ctx.channel().attr(NetworkChannelHandler.ATTRIB_PLAYER).get();
            RSBuffer buff = new RSBuffer(in);

            while(buff.get().readableBytes() > 1) {
                int encodedPacketId = buff.readByte();
                packetId = encodedPacketId /*- player.getInrand().nextInt()*/& 0xFF;
                //System.out.println("Decoding Packet: ID " + packetId + " Size: " + in.readableBytes());

                currentDefinition = GameServer.getSingleton().getProtocolDefinition().getPacketDefinitions().get(packetId);
                if (currentDefinition == null) {
                    //DecodedPacket packet = new UnhandledPacketDecoder();
                    size = in.readableBytes();
                    // packet.decode(player, buff, new PacketDefinition(packetId, size, "", 0));
                    buff.skip(size);
                } else {
                    int required = currentDefinition.getPacketSize();
                    if (required == -1) {
                        if (in.readableBytes() < 1) {
                            in.resetReaderIndex();
                            return;
                        }

                        size = buff.readUByte();
                    } else if (required == -2) {
                        if (in.readableBytes() < 2) {
                            in.resetReaderIndex();
                            return;
                        }

                        size = buff.readUShort();
                    } else {
                        size = required;
                    }

                    if(currentDefinition.getPacketID() != 204 && currentDefinition.getPacketID() != 94 && currentDefinition.getPacketID() != 78)
                        System.out.println("Decoding Packet[Incomming]: " + currentDefinition.getPacketID() + " Size: " + size);
                    DecodedPacket packet = ((Class<DecodedPacket>) Class.forName(DECODER_PACKAGE + "." + currentDefinition.getPacketClass())).newInstance();
                    packet.decode(player, buff, currentDefinition, size);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
