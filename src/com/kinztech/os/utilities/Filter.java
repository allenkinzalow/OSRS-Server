package com.kinztech.os.utilities;

import com.kinztech.os.game.node.Node;

/**
 * Created by Allen Kinzalow on 5/31/2015.
 */
public interface Filter<T extends Node> {

    boolean condition(T node);

}
