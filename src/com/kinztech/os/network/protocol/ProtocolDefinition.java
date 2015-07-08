package com.kinztech.os.network.protocol;

import com.kinztech.os.network.codec.handshake.HandshakeMessage;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class ProtocolDefinition {

    private int revision;

    private BigInteger modulus;

    private BigInteger exponent;

    private HashMap<Integer,PacketDefinition> packetDefMap = new HashMap<>();

    public ProtocolDefinition(int revision, String modulus, String exponent, HashMap<Integer,PacketDefinition> packetDefMap) {
        this.revision = revision;
        this.modulus = new BigInteger(modulus);
        this.exponent = new BigInteger(exponent);
        this.packetDefMap = packetDefMap;
    }

    public int getRevision() {
        return revision;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public BigInteger getExponent() {
        return exponent;
    }

    public HashMap<Integer,PacketDefinition> getPacketDefinitions() {
        return packetDefMap;
    }

}
