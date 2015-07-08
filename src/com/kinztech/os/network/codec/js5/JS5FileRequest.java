package com.kinztech.os.network.codec.js5;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class JS5FileRequest {

    private int index;
    private int file;
    private boolean priority;

    public JS5FileRequest(int index, int file, boolean priority) {
        this.index = index;
        this.file = file;
        this.priority = priority;
    }

    public int getIndex() {
        return index;
    }

    public int getFile() {
        return file;
    }

    public boolean isPriority() {
        return priority;
    }

}
