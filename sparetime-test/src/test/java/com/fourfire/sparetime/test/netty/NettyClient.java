package com.fourfire.sparetime.test.netty;

import java.util.Date;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author liuyi
 * @date 2022/8/7
 * @desc
 */

public class NettyClient {

    public static void main(String args[]) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class)

            .handler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new StringDecoder());
            }
        });

        Channel channel = bootstrap.connect("localhost", 12554).channel();
        System.out.println("connected");
        while (true) {
            //valid format
            channel.writeAndFlush(Unpooled.wrappedBuffer((new Date() + ", hello world").getBytes()));

            //invalid format
            //channel.writeAndFlush(new Date() + ", hello world");

            //invalid format
            //channel.writeAndFlush((new Date() + ", hello world").getBytes());


            System.out.println("send msg");
            Thread.sleep(2000);
        }
    }
}
