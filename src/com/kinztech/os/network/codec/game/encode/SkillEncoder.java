package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 6/3/2015.
 */
public class SkillEncoder implements EncodedPacket {

    int skillID;
    int level;
    int experience;

    public SkillEncoder(int skillID, int level, int experience) {
        this.skillID = skillID;
        this.level = level;
        this.experience = experience;
    }

    @Override
    public RSBuffer encode(Player player) {
        RSBuffer buff = new RSBuffer(Unpooled.buffer());
        buff.packet(237);
        buff.writeByte(level);
        buff.writeInt(experience);
        buff.writeByte(skillID);
        return buff;
    }
}
