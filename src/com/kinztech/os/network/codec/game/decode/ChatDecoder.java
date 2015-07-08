package com.kinztech.os.network.codec.game.decode;

import com.kinztech.os.cache.HuffmanEncoding;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.protocol.PacketDefinition;
import com.kinztech.os.utilities.RSBuffer;
import com.kinztech.os.utilities.StringUtils;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class ChatDecoder implements DecodedPacket {
    @Override
    public void decode(Player player, RSBuffer in, PacketDefinition definition, int size) {
        //in.skip(size);
        int unknown = in.readUByte();
        int chatColor = in.readUByte();
        int chatEffect = in.readUByte();
        int textSize = in.readSmart();
        byte[] decryptedTextArray = new byte[textSize];
        byte[] bytes = new byte[in.get().readableBytes()];
        in.get().getBytes(in.get().readerIndex(), bytes);
        HuffmanEncoding.huffmanEncoding.decrypt(bytes,0,decryptedTextArray,0,textSize,(byte) 103);
        String message = StringUtils.createString(decryptedTextArray, 0, textSize, -971164020);
        System.out.println("Message: " + message);
    }
}
