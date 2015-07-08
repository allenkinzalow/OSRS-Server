package com.kinztech.os.game;

import com.kinztech.os.game.node.Node;
import com.kinztech.os.game.node.entity.npc.NPC;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.game.node.items.GroundItem;
import com.kinztech.os.game.node.object.GameObject;
import com.kinztech.os.utilities.EntityList;
import com.kinztech.os.utilities.Filter;

import java.util.ArrayList;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class World {

    /**
     * Player List
     */
    EntityList<Player> playerList = new EntityList<>(2048);

    /**
     * NPC List
     */
    EntityList<NPC> npcList = new EntityList<>(2000);

    /**
     * Spawned Object List
     */
    ArrayList<GameObject> spawnedObjectList = new ArrayList<>();

    /**
     * Ground Item List
     */
    ArrayList<GroundItem> groundItems = new ArrayList<>();


    /**
     * Register a node to the world.
     * @param node
     * @return
     */
    public void register(Node node) {
        if(node instanceof Player) {
            Player player = (Player)node;
            playerList.add(player);
        } else if(node instanceof NPC) {
            NPC npc = (NPC)node;
            npcList.add(npc);
        } else if(node instanceof GameObject) {
            GameObject obj = (GameObject)node;
            spawnedObjectList.add(obj);
        } else if(node instanceof GroundItem) {
            GroundItem groundItem = (GroundItem)node;
            groundItems.add(groundItem);
        }
    }

    /**
     * Unregister a node from the world.
     * @param node
     * @return
     */
    public void unregister(Node node) {
        if(node instanceof Player) {
            Player player = (Player)node;
            playerList.remove(player);
        } else if(node instanceof NPC) {
            NPC npc = (NPC)node;
            npcList.remove(npc);
        } else if(node instanceof GameObject) {
            GameObject obj = (GameObject)node;
            spawnedObjectList.remove(obj);
        } else if(node instanceof GroundItem) {
            GroundItem groundItem = (GroundItem)node;
            groundItems.remove(groundItem);
        }
    }

    /**
     * Get the players in the world.
     * @return
     */
    public EntityList<Player> getPlayers() {
        return playerList;
    }

    /**
     * Get a Player by filtering through with a given condition
     * @param filter
     * @return
     */
    public Player getPlayer(Filter<Player> filter) {
        for(Player player : playerList) {
            if(filter.condition(player))
                return player;
        }
        return null;
    }


}
