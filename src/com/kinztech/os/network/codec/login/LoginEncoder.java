package com.kinztech.os.network.codec.login;

import com.kinztech.os.GameServer;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.NetworkChannelHandler;
import com.kinztech.os.network.codec.game.GamePacketDecoder;
import com.kinztech.os.network.codec.game.GamePacketEncoder;
import com.kinztech.os.network.codec.game.encode.MapRegion;
import com.kinztech.os.utilities.IsaacRand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.Arrays;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class LoginEncoder extends MessageToByteEncoder<LoginRequest> {

    @Override
    protected void encode(ChannelHandlerContext ctx, LoginRequest request, ByteBuf out1) throws Exception {
        System.out.println("");
        LoginResponse response = LoginResponse.CONTINUE;
        String username = request.getUsername();
        String password = request.getPassword();

        int[] crcs = request.getCrcs();
        for(int index = 0; index < GameServer.getSingleton().getFileStore().getIndexCount(); index++) {
            if(crcs[index] != GameServer.getSingleton().getFileStore().getIndex(index).getCRC()) {
                response = LoginResponse.RS_UPDATED;
                break;
            }
        }

        ByteBuf out = Unpooled.buffer();

        out.writeByte(response.getResponseId());
        if(response == LoginResponse.CONTINUE) {
            System.out.println("Writing login response...");
            out.writeByte(0); // adding to trust list
            out.writeInt(0);

            out.writeByte(2); // rights
            out.writeByte(1); // members
            out.writeShort(0); // something to do with player index client sided
            out.writeByte(0); // something to do with JS Object

            //out.writeByte(5);

            ctx.channel().write(out);

            ctx.channel().pipeline().remove(LoginEncoder.class);
            ctx.channel().pipeline().addBefore("handler", GamePacketEncoder.class.getName(), new GamePacketEncoder());
            ctx.channel().pipeline().addBefore("handler", GamePacketDecoder.class.getName(), new GamePacketDecoder());
            System.out.println("Channels: " + ctx.channel().pipeline().names());

            int[] inseed = request.getIsaacSeeds();
            int[] outseed = new int[inseed.length];
            for (int i = 0; i < outseed.length; i++)
                outseed[i] = inseed[i] + 50;
            System.out.println("InIsaac: " + Arrays.toString(inseed));
            IsaacRand inrand = new IsaacRand(inseed);
            IsaacRand outrand = new IsaacRand(outseed);

            Player player = new Player(ctx.channel(), username, password, inrand, outrand, request.getSession());

            ctx.channel().attr(NetworkChannelHandler.ATTRIB_PLAYER).set(player);

            GameServer.getSingleton().getWorld().register(player);

            player.write(new MapRegion(player.getWorldTile().getX(),player.getWorldTile().getY(),player.getWorldTile().getPlane()));

            player.initiate();

        }
    }

}
