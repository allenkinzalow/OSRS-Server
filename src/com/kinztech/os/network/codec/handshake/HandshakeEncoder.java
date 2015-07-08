package com.kinztech.os.network.codec.handshake;

import com.kinztech.os.network.codec.js5.JS5Decoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class HandshakeEncoder extends MessageToByteEncoder<HandshakeResult> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HandshakeResult hs, ByteBuf out) throws Exception {
        //System.out.println("Writing handshake result: " + hs.getResult());
        out.writeByte(hs.getResult());
        ctx.pipeline().remove(HandshakeEncoder.class);
    }

}
