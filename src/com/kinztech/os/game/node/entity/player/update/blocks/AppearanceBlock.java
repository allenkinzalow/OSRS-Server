package com.kinztech.os.game.node.entity.player.update.blocks;

import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateMask;
import com.kinztech.os.game.node.entity.player.Appearance;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateMask;
import com.kinztech.os.utilities.RSBuffer;
import com.kinztech.os.utilities.Utils;
import com.sun.deploy.util.ArrayUtil;
import io.netty.buffer.Unpooled;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class AppearanceBlock implements UpdateBlock{

    Player player;

    public AppearanceBlock(Player player) {
        this.player = player;
    }

    @Override
    public UpdateMask getUpdateMask() {
        return PlayerUpdateMask.APPEARANCE;
    }

    @Override
    public void encode(RSBuffer finalBuff) {
        Appearance appearance = player.getAppearance();
        System.out.println("Appearance Size Before: " + finalBuff.get().readableBytes());

        RSBuffer buffer = new RSBuffer(Unpooled.buffer());
        buffer.writeByte(appearance.getGender());
        buffer.writeByte(appearance.isSkulled() ? 0 : -1);
        buffer.writeByte(appearance.getHeadIcon());

        if(appearance.getTransformedNPCId() != -1) {
            buffer.writeShort(-1);
            buffer.writeShort(appearance.getTransformedNPCId());
        } else {
            for(int i = 0; i < 12; i++) {
                int bodyPartID = appearance.getBodyPartID(i);
                if(bodyPartID == 0)
                    buffer.writeByte(0);
                else
                    buffer.writeShort(bodyPartID);
            }
        }

        for(int color : appearance.getBodyPartColors())
            buffer.writeByte(color);

        buffer.writeShort(appearance.getStandAnimation());
        buffer.writeShort(appearance.getStandTurnAnimation());
        buffer.writeShort(appearance.getWalkAnimation());
        buffer.writeShort(appearance.getTurn180Animation());
        buffer.writeShort(appearance.getTurn90RightAnimation());
        buffer.writeShort(appearance.getTurn90LeftAnimation());
        buffer.writeShort(appearance.getRunAnimation());

        System.out.println("Appearance Block: " + player.getDetails().getUsername());
        buffer.writeString(player.getDetails().getUsername());

        buffer.writeByte(126);
        buffer.writeShort(0);
        buffer.writeByte(0);

        byte[] appearanceData = new byte[buffer.get().writerIndex()];
        buffer.get().readerIndex(0);
        buffer.get().readBytes(appearanceData);
        //Utils.reverse(appearanceData); // change this if necessary

        byte[] block = new byte[appearanceData.length + 1];
        System.arraycopy(appearanceData, 0, block, 1, appearanceData.length);
        block[0] = (byte)(128 - appearanceData.length);
        for (int i = 1; i < appearanceData.length; i++) { // change this if necessary
            block[i] += 128;
        }

        System.out.println("Size: " + appearanceData.length);
        finalBuff.get().writeBytes(block);

        System.out.println("Appearance Size After: " + finalBuff.get().readableBytes());
    }

}
