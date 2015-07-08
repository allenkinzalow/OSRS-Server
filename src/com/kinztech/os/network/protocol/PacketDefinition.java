package com.kinztech.os.network.protocol;

import com.kinztech.os.network.codec.game.GamePacketDecoder;

import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class PacketDefinition {

    /**
     * The packet ID
     */
    private int packetID;

    /**
     * The packet size if its client->server
     */
    private int packetSize;

    /**
     * The corresponding decoder class.
     */
    String decoderClassName;

    /**
     * The index of the packet. i.e. action button 1,2,3,etc..
     */
    private int index;

    public PacketDefinition(int packetID, int packetSize, String decoderClassName, int index) {
        this.packetID = packetID;
        this.packetSize = packetSize;
        this.decoderClassName = decoderClassName;
        this.index = index;
    }

    public int getPacketID() {
        return packetID;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public String getPacketClass() {
        return decoderClassName;
    }

    public int getIndex() {
        return index;
    }

}
