package com.kinztech.os.utilities;

import io.netty.buffer.ByteBuf;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by Allen Kinzalow on 5/24/2015.
 * Credits to Bart Pelle
 */
public class RSBuffer {

    private ByteBuf backing;
    private int sizeIndicator = -1;
    private SizeType type;
    private int bitPosition;
    private int opcode = -1;

    private static final int[] BIT_MASK = new int[32];

    public RSBuffer(ByteBuf backing) {
        this.backing = backing;
    }

    public ByteBuf get() {
        return backing;
    }

    public RSBuffer packet(int id) {
        opcode = id;
        return this;
    }

    public int packet() {
        return opcode;
    }

    public SizeType getType() {
        return type;
    }

    public RSBuffer writeSize(SizeType type) {
        this.type = type;
        sizeIndicator = backing.writerIndex();

        if (type == SizeType.BYTE)
            backing.writeByte(0);
        else
            backing.writeShort(0);

        return this;
    }

    public RSBuffer writeByte(int v) {
        backing.writeByte(v);
        return this;
    }

    public byte readByte() {
        return backing.readByte();
    }

    public byte readByteN() {
        return (byte) -backing.readByte();
    }

    public short readUByte() {
        return backing.readUnsignedByte();
    }

    public byte readByteS() {
        return (byte) (128 - backing.readByte());
    }

    public short readUByteS() {
        return (short) (128 - backing.readUnsignedByte());
    }

    public RSBuffer writeByteA(int v) {
        backing.writeByte(v + 128);
        return this;
    }

    public RSBuffer writeByteS(int v) {
        backing.writeByte(128 - v);
        return this;
    }

    public RSBuffer writeByteN(int v) {
        backing.writeByte(-v);
        return this;
    }

    public RSBuffer writeShort(int v) {
        backing.writeShort(v);
        return this;
    }

    public RSBuffer writeLEShort(int v) {
        backing.writeByte(v);
        backing.writeByte(v >> 8);
        return this;
    }

    public RSBuffer writeLEShortA(int v) {
        backing.writeByte(v + 128);
        backing.writeByte(v >> 8);
        return this;
    }

    public RSBuffer writeShortA(int v) {
        backing.writeByte(v >> 8);
        backing.writeByte(v + 128);
        return this;
    }

    public short readShort() {
        return backing.readShort();
    }

    public int readUShort() {
        return backing.readUnsignedShort();
    }

    public int readULEShort() {
        return readUByte() | (readUByte() << 8);
    }

    public int readULEShortA() {
        return ((readByte() - 128) & 0xFF) | (readUByte() << 8);
    }

    public int readSmart() {
        int i = getFirst();
        if(i >= 128)
            return readUShort() - '\u8000';
        return readUByte();
    }

    public int getFirst() {
        byte[] bytes = new byte[backing.readableBytes()];
        backing.getBytes(backing.readerIndex(), bytes);
        return 0xFF & bytes[0];
    }

    public RSBuffer writeInt(int v) {
        backing.writeInt(v);
        return this;
    }

    public RSBuffer writeLEInt(int v) {
        backing.writeByte(v);
        backing.writeByte(v >> 8);
        backing.writeByte(v >> 16);
        backing.writeByte(v >> 24);
        return this;
    }

    public RSBuffer writeIntV1(int v) {
        backing.writeByte(v >> 16);
        backing.writeByte(v >> 24);
        backing.writeByte(v);
        backing.writeByte(v >> 8);
        return this;
    }

    public int readInt() {
        return backing.readInt();
    }

    public RSBuffer writeSmart(int v) {
        if (v >= 128) {
            writeShort(v + '\u8000');
        } else {
            writeByte(v);
        }
        return this;
    }

    public RSBuffer writeString(String str) {
        if (str == null)
            str = "";

        backing.writeBytes(str.getBytes()).writeByte(0);
        return this;
    }

    public String readString() {
        return BufferUtilities.readString(backing);
    }

    public void startBitMode() {
        bitPosition = backing.writerIndex() * 8;
    }

    public void endBitMode() {
        backing.writerIndex((bitPosition + 7) / 8);
    }

    public int bitpos(int i) {
        return 8 * i - bitPosition;
    }

    public void writeBits(int numBits, int value) {
        int bytePos = bitPosition >> 3;
        int bitOffset = 8 - (bitPosition & 7);
        bitPosition += numBits;
        for (; numBits > bitOffset; bitOffset = 8) {
            backing.writerIndex(bytePos);
            backing.ensureWritable(1);

            backing.setByte(bytePos, backing.getByte(bytePos) & ~BIT_MASK[bitOffset]);
            backing.setByte(bytePos, backing.getByte(bytePos) | (value >> numBits - bitOffset & BIT_MASK[bitOffset]));

            backing.writerIndex(bytePos);
            backing.ensureWritable(1);
            backing.writerIndex(bytePos++);

            numBits -= bitOffset;
        }

        backing.writerIndex(bytePos);
        backing.ensureWritable(1);

        // checkCapacityPosition(bytePos);
        if (numBits == bitOffset) {
            backing.setByte(bytePos, backing.getByte(bytePos) & ~BIT_MASK[bitOffset]);
            backing.setByte(bytePos, backing.getByte(bytePos) | (value & BIT_MASK[bitOffset]));
        } else {
            backing.setByte(bytePos, backing.getByte(bytePos) & ~(BIT_MASK[numBits] << bitOffset - numBits));
            backing.setByte(bytePos, backing.getByte(bytePos) | ((value & BIT_MASK[numBits]) << bitOffset - numBits));
        }
    }

    static {
        for (int i = 0; i < 32; i++) {
            BIT_MASK[i] = (1 << i) - 1;
        }
    }

    public RSBuffer finish() {
        if (type != null) {
            if (type == SizeType.BYTE) {
                backing.setByte(sizeIndicator, backing.writerIndex() - sizeIndicator - 1);
            } else {
                backing.setShort(sizeIndicator, backing.writerIndex() - sizeIndicator - 2);
            }
        }

        return this;
    }

    public static byte[] applyRSA(byte[] data, BigInteger exponent, BigInteger modulus) {
        return new BigInteger(data).modPow(exponent, modulus).toByteArray();
    }

    public void skip(int size) {
        backing.skipBytes(size);
    }

    public static enum SizeType {
        BYTE, SHORT
    }

}
