package com.kinztech.os.network.protocol;

import com.google.gson.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class ProtocolDeserializer implements JsonDeserializer<ProtocolDefinition> {

    @Override
    public ProtocolDefinition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        int revision = jsonObject.get("revision").getAsInt();

        String modulus = jsonObject.get("modulus").getAsString();
        String exponent = jsonObject.get("exponent").getAsString();

        PacketDefinition[] packetDefinitions = ctx.deserialize(jsonObject.get("packets"), PacketDefinition[].class);

        HashMap<Integer,PacketDefinition> packetDefinitionMap = new HashMap<Integer,PacketDefinition>();

        for(PacketDefinition def : packetDefinitions)
            packetDefinitionMap.put(def.getPacketID(), def);

        return new ProtocolDefinition(revision, modulus, exponent, packetDefinitionMap);
    }

}
