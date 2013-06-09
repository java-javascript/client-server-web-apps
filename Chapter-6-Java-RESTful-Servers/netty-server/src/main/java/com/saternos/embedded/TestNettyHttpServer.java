package com.saternos.embedded;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestNettyHttpServer {
    
    public static void main(String[] args) throws Exception {
		
		ServerBootstrap bootstrap = new ServerBootstrap();
        
		bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
				.channel(NioServerSocketChannel.class)
				.localAddress(8000)
				.childHandler(new JsonServerInitializer());
				
        bootstrap.bind().sync().channel().closeFuture().sync();
    }
}