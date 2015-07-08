package com.kinztech.os.game.node.entity.player.update;

import com.kinztech.os.GameServer;
import com.kinztech.os.game.node.entity.UpdateBlock;
import com.kinztech.os.game.node.entity.UpdateContainer;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.game.node.entity.player.update.blocks.AppearanceBlock;
import com.kinztech.os.game.region.Direction;
import com.kinztech.os.game.region.WorldTile;
import com.kinztech.os.network.codec.game.encode.MapRegion;
import com.kinztech.os.network.codec.game.encode.PlayerUpdateEncoder;
import com.kinztech.os.utilities.EntityList;
import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 * Credits to Bart for some of the ideas used here.
 */
public class PlayerUpdateContainer extends UpdateContainer<Player> {

    /**
     * The last updated scene tile.
     */
    private WorldTile latestSceneTile;

    /**
     * The primary direction.
     */
    private Direction primaryDirection = Direction.NONE;

    /**
     * The secondary direction.
     */
    private Direction secondaryDirection = Direction.NONE;

    /**
     * List of local players to render.
     */
    private EntityList<Player> localPlayers = new EntityList<Player>(256);

    /**
     * A list of recently added players to append the appearance block
     *  on the first flag update.
     */
    private ArrayList<Player> recentlyAdded = new ArrayList<Player>();

    /**
     * A lsit of players who require a flag update.
     */
    private ArrayList<Player> playerUpdateRequests = new ArrayList<>();

    /**
     * @param player The entity
     */
    public PlayerUpdateContainer(Player player) {
        super(player);
    }

    /**
     * Set the latest scene tile.
     * @param tile
     */
    public void setSceneTile(WorldTile tile) { this.latestSceneTile = tile; }

    /**
     * Retrieve the latest scene tile.
     * @return
     */
    public WorldTile getLatestSceneTile() { return this.latestSceneTile; }

    /**
     * Get the primary direction.
     * @return
     */
    public Direction getPrimaryDirection() {
        return primaryDirection;
    }

    /**
     * Get the secondary direction.
     * @return
     */
    public Direction getSecondaryDirection() {
        return secondaryDirection;
    }

    /**
     * Update the player's walking queue and region.
     */
    public void processPlayer() {

        if(this.getEntity().getWalkingQueue().getTeleport() != null) {
            this.getEntity().getWorldTile().setWorldTile(this.getEntity().getWalkingQueue().getTeleport());
        } else {
            // process walking
        }

        if(this.latestSceneTile == null) {
            WorldTile tile = this.getEntity().getWorldTile();
            this.getEntity().write(new MapRegion(tile.getX(), tile.getY(), tile.getPlane()));
            System.out.println("updting maps1");
        } else {
            WorldTile tile = this.getEntity().getWorldTile();
            int dx = Math.abs(this.latestSceneTile.getX() - tile.getX());
            int dy = Math.abs(this.latestSceneTile.getY() - tile.getY());
            if (dx <= 16 || dy <= 16 || dx >= 88 || dy >= 88) {
                System.out.println("updating maps2 dx: " + dx + " dy: " + dy);
                this.getEntity().write(new MapRegion(tile.getX(), tile.getY(), tile.getPlane()));
            }
        }

    }

    /**
     * Execute the player update packet.
     */
    public void sendPlayerUpdate() {
        RSBuffer buffer = new RSBuffer(Unpooled.buffer());
        if(buffer == null)
            buffer = new RSBuffer(Unpooled.buffer());
        buffer.packet(74).writeSize(RSBuffer.SizeType.SHORT);

        buffer.startBitMode();
        updateMovement(buffer);
        updateLocalPlayerMovement(buffer);
        updateLocalPlayers(buffer);
        buffer.endBitMode();

        updatePlayerFlags(buffer);
        getEntity().write(new PlayerUpdateEncoder(buffer));
    }

    /**
     * Finalize the player update process.
     */
    public void finishUpdate() {
        this.clear();
        if(this.getEntity().getWalkingQueue().getTeleport() != null)
            this.getEntity().getWalkingQueue().teleport(null);
        this.playerUpdateRequests.clear();
    }

    /**
     * Update the local position and movement type of the player.
     * @param buffer
     */
    private void updateMovement(RSBuffer buffer) {
        if(this.isUpdateRequired()) {
            buffer.writeBits(1,1);

            WorldTile teleport = null;

            int moveType = teleport != null ? 3 : (secondaryDirection != null && secondaryDirection.toInteger() >= 0) ? 2 : 1;
            buffer.writeBits(2,moveType);
            if(moveType == 3) {
                buffer.writeBits(2, teleport.getPlane());
                buffer.writeBits(1,1);
                buffer.writeBits(1, this.getUpdateFlag() != 0 ? 1 : 0);
                buffer.writeBits(7, teleport.getX() - latestSceneTile.getX());
                buffer.writeBits(7, teleport.getY() - latestSceneTile.getY());
                if(this.getUpdateFlag() != 0) {
                    this.playerUpdateRequests.add(this.getEntity());
                }
            } else if(moveType > 0) {
                buffer.writeBits(3, primaryDirection.toInteger());
                if(secondaryDirection != null && secondaryDirection.toInteger() >= 0 && moveType == 2)
                    buffer.writeBits(3, secondaryDirection.toInteger());
                buffer.writeBits(1, this.getUpdateFlag() != 0 ? 1 : 0);
                if(this.getUpdateFlag() != 0) {
                    this.playerUpdateRequests.add(this.getEntity());
                }
            } else {
                if(this.getUpdateFlag() != 0) {
                    this.playerUpdateRequests.add(this.getEntity());
                }
            }
        } else
            buffer.writeBits(1, 0);
    }

    /**
     * Update surrounding player's movements
     * @param buffer
     */
    private void updateLocalPlayerMovement(RSBuffer buffer) {
        buffer.writeBits(8, localPlayers.size());
        Iterator<Player> it = localPlayers.iterator();
        while(it.hasNext()) {
            Player local = it.next();
            if(local == null || !this.getEntity().getWorldTile().withinDistance(local.getWorldTile(), 14)) {
                buffer.writeBits(1,1);
                buffer.writeBits(2,3);
                it.remove();
                continue;
            }
            if(local.getUpdateContainer().isUpdateRequired()) {
                buffer.writeBits(1,1);

                WorldTile teleport = null;
                int moveType = teleport != null ? 3 : (secondaryDirection != null && secondaryDirection.toInteger() >= 0) ? 2 : 1;
                buffer.writeBits(2, moveType);
                if(moveType > 0 && moveType < 3) {
                    int secondaryDirection = local.getUpdateContainer().getSecondaryDirection().toInteger();
                    buffer.writeBits(3, local.getUpdateContainer().getPrimaryDirection().toInteger());
                    if(secondaryDirection >= 0 && moveType == 2)
                        buffer.writeBits(3, secondaryDirection);
                    buffer.writeBits(1, local.getUpdateContainer().getUpdateFlag() != 0 ? 1 : 0);
                    if(local.getUpdateContainer().getUpdateFlag() != 0)
                        this.playerUpdateRequests.add(local);
                }
            } else
                buffer.writeBits(1, 0);
        }
    }

    /**
     * Update the local players that are rendered, add necessary ones.
     * @param buffer
     */
    private void updateLocalPlayers(RSBuffer buffer) {
        for(Player p : GameServer.getSingleton().getWorld().getPlayers()) {
            if(p == getEntity() || this.localPlayers.get(p.getIndex()) != null)
                continue;

            if(this.localPlayers.size() >= 255)
                break;

            buffer.writeBits(11, p.getIndex());
            buffer.writeBits(5, p.getWorldTile().getY() - getEntity().getWorldTile().getY());
            buffer.writeBits(1, 1);
            buffer.writeBits(5, p.getWorldTile().getX() - getEntity().getWorldTile().getX());
            buffer.writeBits(1, 1);
            buffer.writeBits(3, 0);
            this.localPlayers.add(p);
            this.recentlyAdded.add(p);
            this.playerUpdateRequests.add(p);
        }
        buffer.writeBits(11, 2047);
    }

    /**
     * Process the player update flags.
     * @param buffer
     */
    private void updatePlayerFlags(RSBuffer buffer) {
        for(Player p : playerUpdateRequests) {
            if(p == null)
                continue;

            if(this.recentlyAdded.contains(p)) {
                p.getUpdateContainer().appendBlock(new AppearanceBlock(p));
                this.recentlyAdded.remove(p);
            }

            int flag = p.getUpdateContainer().getUpdateFlag();
            if(flag >> 8 != 0)
                flag |= 0x64;

            buffer.writeByte(flag);
            if(flag >> 8 != 0)
                buffer.writeByte(flag >> 8);

            System.out.println("Flag: " + flag);

            for(UpdateBlock block : p.getUpdateContainer().getUpdateBlocks()) {
                if(block != null && p.getUpdateContainer().containsMask(block.getUpdateMask().getMask())) {
                    block.encode(buffer);
                }
            }
            System.out.println("Final Size: " + buffer.get().readableBytes());
        }
    }

}
