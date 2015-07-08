package com.kinztech.os.network.codec.login;

import com.kinztech.os.GameServer;
import com.kinztech.os.utilities.BufferUtilities;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class LoginDecoder extends ByteToMessageDecoder {

    private final static int LOGIN = 16;
    private final static int RECONNECT = 18;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        int opcode = in.readUnsignedByte();
        System.out.println("Login: " + opcode);
        if(opcode == LOGIN || opcode == RECONNECT) {
            int size = in.readUnsignedShort();
            /*if (in.readableBytes() < size) {
                in.resetReaderIndex();
                return;
            }*/
            int revision = in.readInt();

            int secureBufferSize = in.readShort();
            byte[] secureData = new byte[secureBufferSize];
            in.readBytes(secureData, 0, secureBufferSize);
            RSBuffer secureBuffer = new RSBuffer(Unpooled.copiedBuffer(RSBuffer.applyRSA(secureData, GameServer.getSingleton().getProtocolDefinition().getExponent(), GameServer.getSingleton().getProtocolDefinition().getModulus())));

            int unknown_1 = secureBuffer.readByte(); // is always 1
            int loginType = secureBuffer.readByte();
            System.out.println("Size: " + size + " Avail: " + in.readableBytes() + " Revision: " + revision + " Magic: " + unknown_1 + " Type: " + loginType);
            int[] isaacSeed = new int[4];
            for (int i = 0; i < 4; i++) {
                isaacSeed[i] = secureBuffer.readInt();
            }
            if(loginType == 2) // regular login - others deal with JAG & Google Auth
                secureBuffer.skip(8);

            String password = BufferUtilities.readString(secureBuffer.get());
            System.out.println("Password: " + password);
            String username = BufferUtilities.readString(in);
            System.out.println("User: " + username);
            boolean lowMemory = in.readUnsignedByte() == 1;
            int clientWidth = in.readUnsignedShort();
            int clientHeight = in.readUnsignedShort();
            in.skipBytes(24);

            String clientSession = BufferUtilities.readString(in);
            System.out.println("Client Session: " + clientSession);
            int unknown = in.readInt();

            int machineinfo1 = in.readUnsignedByte(); // 6
            int machineinfo2 = in.readUnsignedByte(); // os
            int machineinfo3 = in.readUnsignedByte();
            int machineinfo4 = in.readUnsignedByte();
            int machineinfo5 = in.readUnsignedByte();
            int machineinfo6 = in.readUnsignedByte();
            int machineinfo7 = in.readUnsignedByte();
            int machineinfo8 = in.readUnsignedByte();
            int machineinfo9 = in.readUnsignedByte();
            int machineinfo10 = in.readUnsignedShort();
            int machineinfo11 = in.readUnsignedByte();
            int machineinfo12 = /*in.readMedium()*/BufferUtilities.readTriByte(in);
            int machineinfo13 = in.readUnsignedShort();
            String machineinfo14 = BufferUtilities.readJAGString(in);
            String machineinfo15 = BufferUtilities.readJAGString(in);
            String machineinfo16 = BufferUtilities.readJAGString(in);
            String machineinfo17 = BufferUtilities.readJAGString(in);
            int machineinfo18 = in.readUnsignedByte();
            int machineinfo19 = in.readUnsignedShort();
            String machineinfo20 = BufferUtilities.readJAGString(in);
            String machineinfo21 = BufferUtilities.readJAGString(in);
            int machineinfo22 = in.readUnsignedByte();
            int machineinfo23 = in.readUnsignedByte();
            int machineinfo24 = in.readInt();
            int machineinfo25 = in.readInt();
            int machineinfo26 = in.readInt();
            int machineinfo27 = in.readInt();

            int uid = in.readByte();

            int[] crcs = new int[16];
            for(int crc = 0; crc < crcs.length; crc++) {
                crcs[crc] = in.readInt();
            }

            ctx.pipeline().remove(LoginDecoder.class);

            LoginRequest request = new LoginRequest(username, password, isaacSeed, revision, clientSession, crcs);
            list.add(request);
        }
    }

}
