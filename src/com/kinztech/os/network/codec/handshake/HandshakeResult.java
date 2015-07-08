package com.kinztech.os.network.codec.handshake;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public enum HandshakeResult {

    CONTINUE(0),
    EXPIRED(6);

    int result;
    HandshakeResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

}
