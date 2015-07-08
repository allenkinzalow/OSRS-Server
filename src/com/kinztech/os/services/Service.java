package com.kinztech.os.services;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public abstract class Service implements Runnable {

    protected ServiceExecutor executor;

    public Service(ServiceExecutor executor) {
        this.executor = executor;
    }

    public abstract void start();

    public abstract void run();

}
