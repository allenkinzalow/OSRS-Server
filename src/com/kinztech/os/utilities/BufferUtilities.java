package com.kinztech.os.utilities;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class BufferUtilities {

    public static void writeString(String s, ByteBuffer buffer) {
        for (char c : s.toCharArray()) {
            buffer.put((byte) c);
        }
        buffer.put((byte) 0);
    }

    public static String readString(ByteBuf buffer) {
        int start = buffer.readerIndex();
        while (buffer.readByte() != 0);
        int len = buffer.readerIndex() - start;

        byte[] str = new byte[len];
        buffer.readerIndex(start);
        buffer.readBytes(str);

        return new String(str, 0, len - 1); /* Do not include null terminator */
    }

    public static String readJAGString(ByteBuf buffer) {
        int b = buffer.readByte();
        int start = buffer.readerIndex();
        while (buffer.readByte() != 0);
        int len = buffer.readerIndex() - start;

        byte[] str = new byte[len];
        buffer.readerIndex(start);
        buffer.readBytes(str);

        return new String(str, 0, len - 1); /* Do not include null terminator */
    }

    public static int readTriByte(ByteBuf buffer) {
        return ((buffer.readUnsignedByte() << 16) + (buffer.readUnsignedByte() << 8) + buffer.readUnsignedByte());
    }

}
