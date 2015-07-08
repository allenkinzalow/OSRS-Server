package com.kinztech.os.game.node.entity.player;

import com.kinztech.os.GameServer;
import com.kinztech.os.game.node.entity.Entity;
import com.kinztech.os.game.node.entity.player.update.PlayerUpdateContainer;
import com.kinztech.os.game.node.entity.player.update.blocks.AppearanceBlock;
import com.kinztech.os.game.node.entity.player.varp.VarpContainer;
import com.kinztech.os.game.node.entity.player.widgets.WidgetManager;
import com.kinztech.os.game.region.WorldTile;
import com.kinztech.os.network.codec.game.encode.*;
import com.kinztech.os.utilities.IsaacRand;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class Player extends Entity {

    /**
     * The current channel of the player.
     */
    private Channel channel;

    /**
     * Player details.
     */
    private PlayerDetails details;

    /**
     * Inbound isaac random gen
     */
    private IsaacRand inrand;

    /**
     * Outbound isaac random gen
     */
    private IsaacRand outrand;

    /**
     * Player varp map.
     */
    private VarpContainer varpContainer;

    /**
     * The manager for widgets
     */
    private WidgetManager widgetManager;

    /**
     * The player's update container.
     */
    private PlayerUpdateContainer updateContainer;

    /**
     * The player's appearance.
     */
    private Appearance appearance;

    /**
     * The player's skill data.
     */
    private Skills skills;

    public Player(Channel channel, String username, String password, IsaacRand inrand, IsaacRand outrand, String clientSession) {
        details = new PlayerDetails();
        details.setUsername(username);
        details.setPassword(password);
        details.setClientSession(clientSession);
        this.inrand = inrand;
        this.outrand = outrand;
        this.channel = channel;
        this.updateContainer = new PlayerUpdateContainer(this);
        this.setWorldTile(new WorldTile(3093, 3493, 0));
    }

    /**
     * Initiate the player
     */
    public void initiate() {
        //load player details...
        varpContainer = new VarpContainer(this);
        widgetManager = new WidgetManager(this);
        appearance = new Appearance(this);
        skills = new Skills(this);
        widgetManager.sendInitPane();
        this.getWalkingQueue().teleport(this.getWorldTile());
        this.getUpdateContainer().appendBlock(new AppearanceBlock(this));
        this.getVarpContainer().addInitialVarp();
        //write(new UnknownLogin3(14155777, 37, 2, 0), new UnknownLogin3(15663105, 502, 2, 0), new UnknownLogin4(), new UnknownLogin(), new UnknownLogin2(10813466), new UnknownLogin2(10813465));
        skills.updateSkills();
        this.write(new SendChatMessage("Welcome to Blah!"));
    }

    /**
     * Write an object to the channel.
     * @param o
     */
    public void write(Object... o) {
        if (channel.isActive()) {
            for (Object msg : o) {
                channel.write(msg);
            }
            channel.flush();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public PlayerDetails getDetails() {
        return details;
    }

    public IsaacRand getInrand() {
        return inrand;
    }

    public IsaacRand getOutrand() {
        return outrand;
    }

    public VarpContainer getVarpContainer() {
        return varpContainer;
    }

    public WidgetManager getWidgetManager() { return widgetManager; }

    @Override
    public PlayerUpdateContainer getUpdateContainer() { return updateContainer; }

    public Appearance getAppearance() { return appearance; }

    public Skills getSkills() { return skills; }

}
