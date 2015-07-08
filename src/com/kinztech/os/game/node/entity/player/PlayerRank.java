package com.kinztech.os.game.node.entity.player;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public enum PlayerRank {

    PLAYER(0),
    MODERATOR(1),
    ADMINISTRATOR(2);

    int playerRankID;

    PlayerRank(int playerRankID) {
        this.playerRankID = playerRankID;
    }

    public int getPlayerRankID() {
        return playerRankID;
    }

}
