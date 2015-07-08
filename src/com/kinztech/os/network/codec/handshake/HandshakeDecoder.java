package com.kinztech.os.network.codec.handshake;

import com.kinztech.os.network.codec.js5.JS5Decoder;
import com.kinztech.os.network.codec.js5.JS5Encoder;
import com.kinztech.os.network.codec.login.LoginDecoder;
import com.kinztech.os.network.codec.login.LoginEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class HandshakeDecoder extends ByteToMessageDecoder {

    private final int JS5_HANDSHAKE = 15;
    private final int LOGIN = 14;

    private final Logger logger = Logger.getLogger(HandshakeDecoder.class.getName());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        if(in.isReadable()) {
            int handshake = in.readByte();
            switch(handshake) {
                case JS5_HANDSHAKE:
                    int revision = in.readInt();
                    logger.log(Level.INFO, "JS5 Connection: " + ctx.channel().localAddress() + " Revision: " + revision);
                    ctx.pipeline().addBefore("handler", JS5Decoder.class.getName(), new JS5Decoder());
                    ctx.pipeline().addBefore("handler", JS5Encoder.class.getName(), new JS5Encoder());
                    break;
                case LOGIN:
                    logger.log(Level.INFO, "Login Connection: " + ctx.channel().localAddress());
                    ctx.pipeline().addBefore("handler", LoginDecoder.class.getName(), new LoginDecoder());
                    ctx.pipeline().addBefore("handler", LoginEncoder.class.getName(), new LoginEncoder());
                    break;
                default:
                    logger.log(Level.SEVERE, "Unrecognized OPCode: " + handshake);
                    break;

            }
            list.add(HandshakeResult.CONTINUE);
            ctx.pipeline().remove(HandshakeDecoder.class);
            System.out.println("Channels: " + ctx.pipeline().names().toString());
        }
    }


}
