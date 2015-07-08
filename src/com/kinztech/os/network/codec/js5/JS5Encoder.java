package com.kinztech.os.network.codec.js5;

import com.kinztech.os.GameServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 * Credit for 4 methods in this class to Bart Pelle
 */
public class JS5Encoder extends MessageToByteEncoder<JS5FileRequest> {

    private final Logger logger = Logger.getLogger(JS5Encoder.class.getName());

    private static byte[] cachedIndexInfo;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, JS5FileRequest request, ByteBuf out) throws Exception {
        try {
            int index = request.getIndex();
            int file = request.getFile();

            logger.log(Level.INFO, "Encoding JS5 Request: " + index + "," + file);
            out.writeByte(index);
            out.writeShort(file);

            byte[] fileData;

            if (index == 255 && file == 255)
                fileData = getIndexInfo();
            else if (index == 255)
                fileData = getDescriptorData(file);
            else
                fileData = getFileData(index, file);

            for (byte b : fileData) {
                if (out.writerIndex() % 512 == 0) {
                    out.writeByte(-1);
                }

                out.writeByte(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private byte[] getDescriptorData(int index) {
        return trim(GameServer.getSingleton().getFileStore().getDescriptorIndex().getArchive(index), index);
    }

    private byte[] getFileData(int index, int file) {
        return trim(GameServer.getSingleton().getFileStore().getIndex(index).getArchive(file), index);
    }

    private byte[] trim(byte[] b, int index) {
        ByteBuffer buffer = ByteBuffer.wrap(b);
        int compression = buffer.get();
        int size = buffer.getInt();

        if(compression != 0)
            System.out.println("index: " + index);

        byte[] n = new byte[size + (compression == 0 ? 5 : 9)];
        System.arraycopy(b, 0, n, 0, size + (compression == 0 ? 5 : 9));
        return n;
    }

    private byte[] getIndexInfo() {
        if (cachedIndexInfo != null)
            return cachedIndexInfo;

        cachedIndexInfo = new byte[5 + GameServer.getSingleton().getFileStore().getIndexCount() * 8];
        ByteBuffer buffer = ByteBuffer.wrap(cachedIndexInfo);
        buffer.put((byte) 0);
        buffer.putInt(GameServer.getSingleton().getFileStore().getIndexCount() * 8);

        for (int index = 0; index < GameServer.getSingleton().getFileStore().getIndexCount(); index++) {
            buffer.putInt(GameServer.getSingleton().getFileStore().getIndex(index).getCRC());
            buffer.putInt(GameServer.getSingleton().getFileStore().getIndex(index).getDescriptor().getRevision());
        }

        return cachedIndexInfo;
    }

}
