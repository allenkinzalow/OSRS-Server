package com.kinztech.os.network.codec.js5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class JS5Decoder extends ByteToMessageDecoder {

    private final Logger logger = Logger.getLogger(JS5Decoder.class.getName());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        try {
            logger.log(Level.INFO, "Decoding JS5 Request... ");
            System.out.println("JS5 Channel: " + ctx.pipeline().names());
            if (!in.isReadable()) {
                System.out.println("unradable");
                return;
            }
            in.markReaderIndex();
            int opcode = in.readByte();
            logger.log(Level.INFO, "OPcode: " + opcode + " Readable: " + in.readableBytes());

            if (opcode == 0 || opcode == 1) {
                if (in.readableBytes() < 3) {
                    logger.log(Level.WARNING, "Readable Bytes < 3 : " + in.readableBytes());
                    in.resetReaderIndex();
                    return;
                }
                int index = in.readUnsignedByte();
                int file = in.readUnsignedShort();
                boolean priority = opcode == 0;
                logger.log(Level.INFO, "File Request: [" + index + "," + file + "] Priority: " + priority);
                list.add(new JS5FileRequest(index, file, priority));
            } else if (opcode == 2 || opcode == 3 || opcode == 6) {
                in.readByte();
                in.readShort();
            } else {
                logger.log(Level.WARNING, "Unknown JS5 OPC: " + opcode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
