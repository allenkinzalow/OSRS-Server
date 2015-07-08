package com.kinztech.os.network.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by Allen Kinzalow on 5/29/2015.
 */
public class ProtocolDefinitionBuilder {

    public static ProtocolDefinition buildProtocolDefinitions() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProtocolDefinition.class, new ProtocolDeserializer());
        gsonBuilder.registerTypeAdapter(PacketDefinition.class, new PacketDeserializer());
        final Gson gson = gsonBuilder.create();

        try (Reader reader = new InputStreamReader(new FileInputStream(new File("./resources/protocol/packets.json")))) {
            final ProtocolDefinition definition = gson.fromJson(reader, ProtocolDefinition.class);
            System.out.println("Loaded " + definition.getPacketDefinitions().size() + " packet definitions!");
            return definition;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
