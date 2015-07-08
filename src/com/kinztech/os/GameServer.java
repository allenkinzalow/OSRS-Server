package com.kinztech.os;

import com.kinztech.os.cache.HuffmanEncoding;
import com.kinztech.os.game.World;
import com.kinztech.os.network.NetworkChannelHandler;
import com.kinztech.os.network.codec.handshake.HandshakeDecoder;
import com.kinztech.os.network.codec.handshake.HandshakeEncoder;
import com.kinztech.os.network.protocol.ProtocolDefinition;
import com.kinztech.os.network.protocol.ProtocolDefinitionBuilder;
import com.kinztech.os.services.ServiceExecutor;
import com.kinztech.os.utilities.xtea.XTEAKeys;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import nl.bartpelle.dawnguard.DataStore;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Allen Kinzalow on 5/18/2015.
 */
public class GameServer {

    /**
     * The game server class logger
     */
    private final Logger logger = Logger.getLogger(GameServer.class.getName());

    /**
     * The netty event loop group
     */
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    /**
     * The netty bootstrap
     */
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * The filestore for the game cache.
     */
    private DataStore fileStore;

    /**
     * The service processor
     */
    private ServiceExecutor executor = new ServiceExecutor();

    /**
     * The current definitions for incomming protocol.
     */
    private ProtocolDefinition protocolDefinition;

    /**
     * The game's world
     */
    private World world;

    /**
     * Singleton
     */
    public static GameServer singleton;


    public void initialize() {
        singleton = this;
        initializeBootstrap();
        fileStore = new DataStore(new File("./resources/cache"));
        protocolDefinition = ProtocolDefinitionBuilder.buildProtocolDefinitions();
        world = new World();
        XTEAKeys.initialize();
        HuffmanEncoding.initialize(fileStore);
        executor.startProcessor();
    }

    private void initializeBootstrap() {
        logger.log(Level.INFO, "Initializing Server Bootstrap...");
        serverBootstrap.group(eventLoopGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nsc) throws Exception {
                nsc.pipeline().addLast(HandshakeDecoder.class.getName(), new HandshakeDecoder());
                nsc.pipeline().addLast(HandshakeEncoder.class.getName(), new HandshakeEncoder());
                //nsc.pipeline().addLast(IdleStateHandler.class.getName(), new IdleStateHandler(20,0,0));
                nsc.pipeline().addLast("handler", new NetworkChannelHandler());
            }
        });
        serverBootstrap.childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000);
        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            serverBootstrap.bind(new InetSocketAddress(43594)).sync().awaitUninterruptibly();
            logger.log(Level.INFO, "Binded to port: 43594");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DataStore getFileStore() {
        return fileStore;
    }

    public ServiceExecutor getExecutor() {
        return executor;
    }

    public ProtocolDefinition getProtocolDefinition() {
        return protocolDefinition;
    }

    public World getWorld() { return world; }

    public static GameServer getSingleton() {
        return singleton;
    }



}
