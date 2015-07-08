package com.kinztech.os.services.impl;

import com.kinztech.os.GameServer;
import com.kinztech.os.services.Service;
import com.kinztech.os.services.ServiceExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by Allen Kinzalow on 5/30/2015.
 *
 * Handles the pulse of entities in the world.
 */
public class EntityUpdateService extends Service {

    public EntityUpdateService(ServiceExecutor executor) {
        super(executor);
    }

    @Override
    public void start() {
        this.executor.getBackgroundExecutor().scheduleAtFixedRate(this, 0, ServiceExecutor.TIME_RATE, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        try {
            GameServer.getSingleton().getWorld().getPlayers().forEach(a -> a.getUpdateContainer().processPlayer());
            GameServer.getSingleton().getWorld().getPlayers().forEach(a -> a.getUpdateContainer().sendPlayerUpdate());
            GameServer.getSingleton().getWorld().getPlayers().forEach(a -> a.getUpdateContainer().finishUpdate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
