package com.kinztech.os.network.protocol;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class PacketDeserializer implements JsonDeserializer<PacketDefinition> {

    @Override
    public PacketDefinition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext ctx) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final PacketDefinition definition = new PacketDefinition(jsonObject.get("packetID").getAsInt(), jsonObject.get("packetSize").getAsInt(), jsonObject.get("decoderClassName").getAsString(),
                jsonObject.get("index").getAsInt());

        return definition;
    }

}
