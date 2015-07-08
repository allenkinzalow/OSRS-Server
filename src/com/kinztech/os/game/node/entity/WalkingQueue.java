package com.kinztech.os.game.node.entity;

import com.kinztech.os.game.region.WorldTile;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class WalkingQueue {

    /**
     * The path deque
     */
    private BlockingDeque<WorldTile> worldTileQueue = new LinkedBlockingDeque<>();

    private WorldTile teleport;

    public void teleport(WorldTile teleport) {
        this.teleport = teleport;
    }

    public WorldTile getTeleport() {
        return teleport;
    }



}
