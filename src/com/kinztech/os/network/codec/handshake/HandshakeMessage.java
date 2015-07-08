package com.kinztech.os.network.codec.handshake;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class HandshakeMessage {

    int revision;

    public HandshakeMessage(int revision) {
        this.revision = revision;
    }

    public int getRevision() {
        return revision;
    }

}
