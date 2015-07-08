package com.kinztech.os.game.node.entity.player;

import com.kinztech.os.game.node.entity.player.update.blocks.AppearanceBlock;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 */
public class Appearance {

    /**
     * The player who the appearance belongs to
     */
    private Player player;

    /**
     * The player's gender.
     */
    private byte gender;

    /**
     * Whether or not the player is skulled.
     */
    private boolean skulled;

    /**
     * The player's headicon id.
     */
    private byte headIcon;

    /**
     * Transformable ID
     */
    private int npcId = -1;

    /**
     * The player's body part ids
     */
    private int[] bodyPartIDs = new int[14];

    /**
     * Body part colors
     */
    private int[] bodyPartColors = new int[5];

    /**
     * Player stand animation
     */
    private int standAnimation;

    /**
     * Player turn animation
     */
    private int standTurnAnimation;

    /**
     * Player walk animation
     */
    private int walkAnimation;

    /**
     * Player turn 180 degrees animation
     */
    private int turn180Animation;

    /**
     * Player turn 90 degrees right animation
     */
    private int turn90RightAnimation;

    /**
     * Player turn 90 degrees left animation
     */
    private int turn90LeftAnimation;

    /**
     * Player run animation
     */
    private int runAnimation;

    public Appearance(Player player) {
        this.player = player;
        this.setDefaults();
    }

    public void setDefaults() {
        gender = 0;
        skulled = false;
        headIcon = -1;
        npcId = -1;
        bodyPartIDs = new int[]{1 + 0x100, 10 + 0x100, 18 + 0x100, 26 + 0x100, 33 + 0x100, 36 + 0x100, 42 + 0x100,0,0,0,0,0};
        bodyPartColors = new int[]{3,16,16,0,0};
        standAnimation = 809;
        standTurnAnimation = 823;
        walkAnimation = 819;
        turn180Animation = 820;
        turn90RightAnimation = 821;
        turn90LeftAnimation = 822;
        runAnimation = 824;
    }

    public void update() {
        player.getUpdateContainer().appendBlock(new AppearanceBlock(player));
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
        this.update();
    }

    public boolean isSkulled() {
        return skulled;
    }

    public void setSkulled(boolean skulled) {
        this.skulled = skulled;
        this.update();
    }

    public byte getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(byte headIcon) {
        this.headIcon = headIcon;
        this.update();
    }

    public int getTransformedNPCId() {
        return npcId;
    }

    public void setTransformNPCId(int npcId) {
        this.npcId = npcId;
        this.update();
    }

    public int getBodyPartID(int slot) {
        return bodyPartIDs[slot];
    }

    public void setBodyPartID(int value, int slot) {
        this.bodyPartIDs[slot] = value;
        this.update();
    }

    public int getBodyPartColor(int slot) {
        return bodyPartColors[slot];
    }

    public int[] getBodyPartColors() {
        return bodyPartColors;
    }

    public void setBodyPartColor(int value, int slot) {
        this.bodyPartColors[slot] = value;
        this.update();
    }

    public int getStandAnimation() {
        return standAnimation;
    }

    public int getStandTurnAnimation() {
        return standTurnAnimation;
    }

    public int getWalkAnimation() {
        return walkAnimation;
    }

    public int getTurn180Animation() {
        return turn180Animation;
    }

    public int getTurn90RightAnimation() { return turn90RightAnimation; }

    public int getTurn90LeftAnimation() {
        return turn90LeftAnimation;
    }

    public int getRunAnimation() {
        return runAnimation;
    }

}
