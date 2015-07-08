package com.kinztech.os.game.region;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 * Some of these methods come from the Arios project.
 */
public class WorldTile {

    /**
     * X Pos
     */
    int x;

    /**
     * Y Pos
     */
    int y;

    /**
     * Z/Height/Plane
     */
    int plane;

    public WorldTile(int x, int y, int plane) {
        this.x = x;
        this.y = y;
        this.plane = plane;
    }

    /**
     * Get X Pos
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y Pos
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Get Plane
     * @return
     */
    public int getPlane() {
        return plane;
    }

    /**
     * Set a new world tile to this instance.
     * @param tile
     */
    public void setWorldTile(WorldTile tile) {
        this.x = tile.x;
        this.y = tile.y;
        this.plane = tile.plane;
    }

    /**
     * Gets the x coordinate of the central region.
     * @return The x coordinate of the central region.
     */
    public int getChunkX() {
        return getX() / Region.CHUNK_SIZE;
    }

    /**
     * Gets the y coordinate of the central region.
     * @return The y coordinate of the central region.
     */
    public int getChunkY() {
        return getY() / Region.CHUNK_SIZE;
    }

    /**
     * Gets the x coordinate of the region this position is in.
     *
     * @return The region x coordinate.
     */
    public int getRegionX() {
        return getX() >> 6;
    }

    /**
     * Gets the y coordinate of the region this position is in.
     *
     * @return The region y coordinate.
     */
    public int getRegionY() {
        return getY() >> 6;
    }

    /**
     * Gets the region id.
     * @return The region id.
     */
    public int getRegionId() {
        return (x >> 6) << 8 | (y >> 6);
    }

    /**
     * Gets the local x-coordinate on the current region.
     * @return The local x-coordinate.
     */
    public int getLocalX() {
        return x - ((x >> 6) << 6);
    }

    /**
     * Gets the local y-coordinate on the current region.
     * @return The local y-coordinate.
     */
    public int getLocalY() {
        return y - ((y >> 6) << 6);
    }

    /**
     * Gets the local x-coordinate.
     * @param tile The location containing the regional coordinates.
     * @return The local x-coordinate.
     */
    public int getSceneX(WorldTile tile) {
        return x - ((tile.getRegionX() - 6) << 3);
    }

    /**
     * Gets the local y-coordinate.
     * @param tile The location containing the regional coordinates.
     * @return The local y-coordinate.
     */
    public int getSceneY(WorldTile tile) {
        return y - ((tile.getRegionY() - 6) * 8);
    }
    /**
     * Returns the distance to another tile
     * @param tile
     * @return
     */
    public int distanceTo(WorldTile tile) {
        return (int)Math.sqrt(Math.pow((tile.getX() - this.getX()), 2) + Math.pow((tile.getX() - this.getX()), 2));
    }

    /**
     * Is this tile within distance of another?
     * @param other
     * @param distance
     * @return
     */
    public boolean withinDistance(WorldTile other, int distance) {
        int deltaX = Math.abs(getX() - other.getX());
        int deltaY = Math.abs(getY() - other.getY());
        return deltaX <= distance && deltaY <= distance;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof WorldTile) {
            WorldTile other = (WorldTile)obj;
            return other.getX() == this.getX() && other.getY() == this.getY() && other.getPlane() == this.getPlane();
        }
        return false;
    }

}
