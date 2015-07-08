package com.kinztech.os.network.codec.login;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class LoginRequest {

    /**
     * The username.
     */
    String username;

    /**
     * The password
     */
    String password;

    /**
     * The isaac random keys
     */
    int[] isaacSeeds = new int[4];

    /**
     * The revision
     */
    int revision;

    /**
     * The session assigned at client startup
     */
    String session;

    int[] crcs = new int[16];

    public LoginRequest(String username, String password, int[] isaacSeeds, int revision, String session, int[] crcs) {
        this.username = username;
        this.password = password;
        this.isaacSeeds = isaacSeeds;
        this.revision = revision;
        this.session = session;
        this.crcs = crcs;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int[] getIsaacSeeds() {
        return isaacSeeds;
    }

    public int getRevision() {
        return revision;
    }

    public String getSession() {
        return session;
    }

    public int[] getCrcs() {
        return crcs;
    }

}
