package com.kinztech.os.network;

import com.kinztech.os.GameServer;
import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.codec.game.encode.EncodedPacket;
import com.kinztech.os.network.codec.handshake.HandshakeResult;
import com.kinztech.os.network.codec.js5.JS5FileRequest;
import com.kinztech.os.network.codec.login.LoginRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public class NetworkChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * Logger for NetworkChannelHandler
     */
    private final Logger logger = Logger.getLogger(NetworkChannelHandler.class.getName());

    /**
     * The attribute key for the Player attachment of the channel.
     */
    public static final AttributeKey<Player> ATTRIB_PLAYER = AttributeKey.valueOf("player");

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception{
        super.channelRegistered(ctx);
        logger.log(Level.INFO, "Channel Registered: " + ctx.channel().localAddress());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception{
        super.channelUnregistered(ctx);
        logger.log(Level.INFO, "Channel Unregistered: " + ctx.channel().localAddress());
        Player player = ctx.channel().attr(NetworkChannelHandler.ATTRIB_PLAYER).get();
        if(player != null)
            GameServer.getSingleton().getWorld().unregister(player);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.log(Level.INFO, "Channel Inactive: " + ctx.channel().localAddress());
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx,msg);
        if(msg instanceof HandshakeResult) {
            HandshakeResult res = (HandshakeResult)msg;
            ctx.writeAndFlush(res);
        } else if(msg instanceof JS5FileRequest) {
            JS5FileRequest request = (JS5FileRequest)msg;
            ctx.writeAndFlush(request); // TODO: change this to prioritize request
        } else if(msg instanceof LoginRequest) {
            LoginRequest request = (LoginRequest) msg;
            ctx.writeAndFlush(request);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Error!!");
        cause.printStackTrace();
    }

}
