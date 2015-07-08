package com.kinztech.os.network.codec.game.encode;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.game.region.WorldTile;
import com.kinztech.os.utilities.RSBuffer;
import com.kinztech.os.utilities.xtea.XTEAKeys;
import io.netty.buffer.Unpooled;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 * Credit to Bart Pelle
 */
public class MapRegion implements EncodedPacket {

    private int x;
    private int y;
    private int localX;
    private int localY;
    private int plane;

    private int lastSceneX;
    private int lastSceneY;

    public MapRegion(int x, int y, int plane) {
        int base_x = x / 8;
        int base_y = y / 8;

        lastSceneX = (base_x - 6) * 8;
        lastSceneY = (base_y - 6) * 8;

        this.x = base_x;
        this.y = base_y;
        this.localX = x - lastSceneX;
        this.localY = y - lastSceneY;
        this.plane = plane;
    }

    @Override
    public RSBuffer encode(Player player) {
        System.out.println("Sending Static Map");

        player.getUpdateContainer().setSceneTile(new WorldTile(lastSceneX, lastSceneY, plane));

        RSBuffer buf = new RSBuffer(Unpooled.buffer());
        buf.packet(72).writeSize(RSBuffer.SizeType.SHORT);

        buf.writeLEShort(y);

        /*System.out.println("LocalX: " + localX);
        buf.writeLEShortA(localX);
        buf.writeLEShort(x);*/

        /* Calculate map keys needed - Bart */
        List<Integer[]> keys = new LinkedList<>();
        for (int rx = (x - (104 >> 4)) / 8; ((104 >> 4) + x) / 8 >= rx; rx++) {
            for (int ry = (y - (104 >> 4)) / 8; ((104 >> 4) + y) / 8 >= ry; ry++) {
                int mapid = ry + (rx << 8);
                System.out.println("Writing region: " + mapid);
                keys.add(XTEAKeys.get(mapid));
            }
        }

        for (Integer[] keyset : keys) {
            if(keyset == null) {
                System.out.println("keys null");
                continue;
            }
            System.out.println("Xteas: " + Arrays.toString(keyset));
            for (int key : keyset) {
                buf.writeInt(key);
            }
        }

        /*buf.writeLEShort(y);
        System.out.println("y: " + y);
        buf.writeLEShort(localY);
        buf.writeByteN(plane);*/

        buf.writeLEShort(localY);
        buf.writeByteN(plane);
        buf.writeShort(localX);
        buf.writeShort(x);


        return buf;
    }

}
