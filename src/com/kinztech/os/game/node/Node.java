package com.kinztech.os.game.node;

import com.kinztech.os.game.region.WorldTile;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 *
 * Represents a renderable object that has a tiled position in the game scene.
 */
public abstract class Node {

    WorldTile tile;

    public void setWorldTile(WorldTile tile) {
        this.tile = tile;
    }

    public WorldTile getWorldTile() {
        return tile;
    }

}
