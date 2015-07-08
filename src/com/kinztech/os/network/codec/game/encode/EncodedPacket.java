package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public interface EncodedPacket {

    RSBuffer encode(Player player);

}
