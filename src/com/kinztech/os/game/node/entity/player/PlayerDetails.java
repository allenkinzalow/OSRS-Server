package com.kinztech.os.game.node.entity.player;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class PlayerDetails {

    /**
     * Player Username
     */
    private String username;

    /**
     * Player Display name
     */
    private String displayName;

    /**
     * Player Password
     */
    private String password;

    /**
     * Player Email
     */
    private String email;

    /**
     * The latest client session
     */
    private String latestSession;

    /**
     * The player's rank.
     */
    private PlayerRank rank;

    public PlayerDetails() {
        username = "";
        displayName = "";
        password = "";
        email = "";
        latestSession = "";
        rank = PlayerRank.PLAYER;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientSession(String clientSession) {
        this.latestSession = clientSession;
    }

    public void setRank(PlayerRank rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLatestSession() {
        return latestSession;
    }

    public PlayerRank getRank() {
        return rank;
    }

}
