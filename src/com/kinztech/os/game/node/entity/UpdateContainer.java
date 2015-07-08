package com.kinztech.os.game.node.entity;

import com.kinztech.os.utilities.RSBuffer;
import io.netty.buffer.Unpooled;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public abstract class UpdateContainer<T extends Entity> {

    /**
     * The current update flag which contains
     *  update block's mask
     */
    private int updateFlag = 0;

    /**
     * The update's buffer
     */
    private RSBuffer buffer;

    /**
     * The entity
     */
    private T entity;

    /**
     * The submitted update blocks.
     */
    private UpdateBlock[] updateBlocks = new UpdateBlock[10];

    /**
     *
     * @param entity    The entity
     */
    public UpdateContainer(T entity) {
        this.entity = entity;
        this.buffer = new RSBuffer(Unpooled.buffer(256));
    }

    /**
     * Get the owner of the update container.
     * @return
     */
    public T getEntity() {
        return entity;
    }

    /**
     * Append an block to the update container.
     * @param block
     */
    public void appendBlock(UpdateBlock block) {
        appendMask(block.getUpdateMask().getMask());
        updateBlocks[block.getUpdateMask().getOrder()] = block;
    }

    /**
     * Get the current submitted update blocks.
     * @return
     */
    public UpdateBlock[] getUpdateBlocks() {
        return updateBlocks;
    }

    /**
     * @return is an update required
     */
    public boolean isUpdateRequired() {
        return updateFlag != 0 || this.getEntity().getWalkingQueue().getTeleport() != null;
    }

    /**
     * Set the update flag
     * @param flag
     */
    public void setUpdateFlag(int flag) {
        this.updateFlag = flag;
    }

    /**
     * Append a mask to the update flag.
     * @param mask
     */
    public void appendMask(int mask) {
        this.updateFlag |= mask;
    }

    /**
     * Does the update flag contain a particular mask.
     * @param mask
     * @return
     */
    public boolean containsMask(int mask) {
        return (updateFlag & mask) != 0;
    }

    /**
     * @return the update flag
     */
    public int getUpdateFlag() {
        return updateFlag;
    }

    /**
     * Clear the update container.
     */
    public void clear() {
        this.updateFlag = 0;
        this.updateBlocks = new UpdateBlock[10];
    }

}
