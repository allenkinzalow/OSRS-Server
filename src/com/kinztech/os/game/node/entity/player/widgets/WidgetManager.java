package com.kinztech.os.game.node.entity.player.widgets;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.codec.game.encode.OpenWidget;
import com.kinztech.os.network.codec.game.encode.SetRootPane;
import com.kinztech.os.network.codec.game.encode.UnknownLogin5;
import com.kinztech.os.network.codec.game.encode.UnknownLogin58;

/**
 * Created by Allen Kinzalow on 5/27/2015.
 */
public class WidgetManager {

    private static final int PANE_FIXED = 548;
    private static final int PANE_LOGIN = 165;

    private Player player;
    private int activeRoot;
    private boolean fixed; // coming soon =]

    public WidgetManager(Player player) {
        this.player = player;
        this.fixed = true;
    }

    public void sendInitPane() {
        sendRoot(PANE_LOGIN);
        sendGameInterfaces();
        sendRoot(PANE_FIXED);

        player.write(new UnknownLogin5(32, 64));
        player.write(new UnknownLogin5(32 , 72));
        player.write(new UnknownLogin5(40 , 24));
        player.write(new UnknownLogin5(40 , 32));
        player.write(new UnknownLogin5(32, 64));
        player.write(new UnknownLogin5(40 , 40));
        player.write(new UnknownLogin5(40 , 48));
        player.write(new UnknownLogin5(40 , 56));
        player.write(new UnknownLogin5(40 , 64));
        player.write(new UnknownLogin5(40 , 72));
        player.write(new UnknownLogin5(48 , 24));
        player.write(new UnknownLogin5(48 , 32));
        player.write(new UnknownLogin5(48 , 40));
        player.write(new UnknownLogin5(48 , 48));
        player.write(new UnknownLogin5(48 , 56));
        player.write(new UnknownLogin5(48 , 64));
        player.write(new UnknownLogin5(48 , 72));
        player.write(new UnknownLogin5(56 , 24));
        player.write(new UnknownLogin5(56 , 32));
        player.write(new UnknownLogin5(56 , 40));
        player.write(new UnknownLogin5(56 , 48));
        player.write(new UnknownLogin5(56 , 56));
        player.write(new UnknownLogin5(56 , 64));
        player.write(new UnknownLogin5(56 , 72));
        player.write(new UnknownLogin5(64 , 24));
        player.write(new UnknownLogin5(64 , 32));
        player.write(new UnknownLogin5(64 , 40));
        player.write(new UnknownLogin5(64 , 48));
        player.write(new UnknownLogin5(64 , 56));
        player.write(new UnknownLogin5(64 , 64));
        player.write(new UnknownLogin5(64 , 72));
        player.write(new UnknownLogin5(72 , 24));
        player.write(new UnknownLogin5(72 , 32));
        player.write(new UnknownLogin5(72 , 40));
        player.write(new UnknownLogin5(72 , 48));
        player.write(new UnknownLogin5(72 , 56));
        player.write(new UnknownLogin5(72 , 64));
        player.write(new UnknownLogin5(72 , 72));

        player.write(new UnknownLogin58(10813441 , 35913746));
        player.write(new UnknownLogin58(10813444 , 35913744));
        player.write(new UnknownLogin58(10813442 , 35913739));
        player.write(new UnknownLogin58(10813443 , 35913741));
        player.write(new UnknownLogin58(10813445 , 35913786));
        player.write(new UnknownLogin58(10813446 , 35913788));
        player.write(new UnknownLogin58(10813447 , 35913789));
        player.write(new UnknownLogin58(10813448 , 35913790));
        player.write(new UnknownLogin58(10813449 , 35913791));
        player.write(new UnknownLogin58(10813450 , 35913792));
        player.write(new UnknownLogin58(10813451 , 35913793));
        player.write(new UnknownLogin58(10813452 , 35913794));
        player.write(new UnknownLogin58(10813453 , 35913795));
        player.write(new UnknownLogin58(10813454 , 35913796));
        player.write(new UnknownLogin58(10813455 , 35913797));
        player.write(new UnknownLogin58(10813456 , 35913798));
        player.write(new UnknownLogin58(10813457 , 35913799));
        player.write(new UnknownLogin58(10813458 , 35913800));
        player.write(new UnknownLogin58(10813459 , 35913801));
        player.write(new UnknownLogin58(10813460 , 35913740));
        player.write(new UnknownLogin58(10813461 , 35913742));
        player.write(new UnknownLogin58(10813462 , 35913736));

    }

    public void sendRoot(int root) {
        this.activeRoot = root;
        player.write(new SetRootPane(root));
    }

    public void openWidget(int id, int target, int targetChild, boolean walkable) {
        player.write(new OpenWidget(id,target,targetChild,walkable));
    }

    public void sendGameInterfaces() {

        /*openWidget(320, PANE_FIXED, 136, true);
        openWidget(149, PANE_FIXED, 138, true);
        openWidget(387, PANE_FIXED, 139, true);
        openWidget(271, PANE_FIXED, 140, true);
        openWidget(218, PANE_FIXED, 141, true);
        openWidget(429, PANE_FIXED, 143, true);
        openWidget(432, PANE_FIXED, 144, true);
        openWidget(182, PANE_FIXED, 145, true);
        openWidget(261, PANE_FIXED, 146, true);
        openWidget(216, PANE_FIXED, 147, true);
        openWidget(589, PANE_FIXED, 142, true);
        openWidget(593, PANE_FIXED, 135, true);

        openWidget(137, PANE_FIXED, 128, true);
        openWidget(239, PANE_FIXED, 148, true);
        openWidget(274, PANE_FIXED, 137, true);
        openWidget(216, PANE_FIXED, 147, true);*/

        openWidget(162, PANE_LOGIN, 1, true);
        openWidget(163, PANE_LOGIN, 21, true);
        openWidget(160, PANE_LOGIN, 22, true);
        openWidget(378, PANE_LOGIN, 26, true);
        openWidget(405, PANE_LOGIN, 25, true);
        openWidget(320, PANE_LOGIN, 7, true);
        openWidget(274, PANE_LOGIN, 8, true);
        openWidget(149, PANE_LOGIN, 9, true);
        openWidget(387, PANE_LOGIN, 10, true);
        openWidget(271, PANE_LOGIN, 11, true);
        openWidget(218, PANE_LOGIN, 12, true);
        openWidget(429, PANE_LOGIN, 14, true);
        openWidget(432, PANE_LOGIN, 15, true);
        openWidget(182, PANE_LOGIN, 16, true);
        openWidget(261, PANE_LOGIN, 17, true);
        openWidget(216, PANE_LOGIN, 18, true);
        openWidget(239, PANE_LOGIN, 19, true);
        openWidget(589, PANE_LOGIN, 13, true);
        openWidget(593, PANE_LOGIN, 6, true);

        /*Interface: walkable=1, id=162, target={165, 1}, hash={10813441}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=163, target={165, 21}, hash={10813461}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=160, target={165, 22}, hash={10813462}
        PacketID: 0 Size: 7
        Interface: walkable=0, id=378, target={165, 26}, hash={10813466}
        PacketID: 0 Size: 7
        Interface: walkable=0, id=405, target={165, 25}, hash={10813465}
        PacketID: 106 Size: 0
        PacketID: 0 Size: 7
        Interface: walkable=1, id=320, target={165, 7}, hash={10813447}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=274, target={165, 8}, hash={10813448}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=149, target={165, 9}, hash={10813449}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=387, target={165, 10}, hash={10813450}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=271, target={165, 11}, hash={10813451}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=218, target={165, 12}, hash={10813452}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=429, target={165, 14}, hash={10813454}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=432, target={165, 15}, hash={10813455}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=182, target={165, 16}, hash={10813456}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=261, target={165, 17}, hash={10813457}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=216, target={165, 18}, hash={10813458}
        PacketID: 159 Size: 12
        159: 14155777 , 37 , 2 , 0
        PacketID: 0 Size: 7
        Interface: walkable=1, id=239, target={165, 19}, hash={10813459}
        PacketID: 159 Size: 12
        159: 15663105 , 502 , 2 , 0
        PacketID: 0 Size: 7
        Interface: walkable=1, id=589, target={165, 13}, hash={10813453}
        PacketID: 0 Size: 7
        Interface: walkable=1, id=593, target={165, 6}, hash={10813446}*/

        /*Interface: walkable=1, id=320, target={548, 136}
        Interface: walkable=1, id=149, target={548, 138}
        Interface: walkable=1, id=387, target={548, 139}
        Interface: walkable=1, id=271, target={548, 140}
        Interface: walkable=1, id=218, target={548, 141}
        Interface: walkable=1, id=429, target={548, 143}
        Interface: walkable=1, id=432, target={548, 144}
        Interface: walkable=1, id=182, target={548, 145}
        Interface: walkable=1, id=261, target={548, 146}
        Interface: walkable=1, id=216, target={548, 147}
        Interface: walkable=1, id=589, target={548, 142}
        Interface: walkable=1, id=593, target={548, 135}



        Interface: walkable=1, id=137, target={548, 128}
        Interface: walkable=1, id=137, target={548, 128}
        Interface: walkable=1, id=239, target={548, 148}
        Interface: walkable=1, id=274, target={548, 137}
        Interface: walkable=1, id=216, target={548, 147}*/
    }

}
