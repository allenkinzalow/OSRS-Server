package com.kinztech.os.game.node.entity;

import com.kinztech.os.utilities.RSBuffer;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public interface UpdateBlock {

    public UpdateMask getUpdateMask();

    public void encode(RSBuffer buffer);

}
