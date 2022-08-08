package com.fourfire.sparetime.test.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuyi
 * @date 2022/8/2
 * @desc
 */

public class NIOServer {

    public static void main(String args[]) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start connect thread");
                try (ServerSocketChannel listenerChannel = ServerSocketChannel.open();) {
                    listenerChannel.socket().bind(new InetSocketAddress(8000));
                    listenerChannel.configureBlocking(false);
                    listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                    while (true) {
                        if (serverSelector.select(1) > 0) {
                            System.out.println("get one connect client");
                            Set<SelectionKey> selectedKeys =  serverSelector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectedKeys.iterator();

                            while (iterator.hasNext()) {
                                SelectionKey selectionKey = iterator.next();
                                if (selectionKey.isAcceptable()) {
                                    try (SocketChannel clientChannel = ((ServerSocketChannel)selectionKey.channel()).accept();) {
                                        clientChannel.configureBlocking(false);
                                        clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                    } finally {
                                        iterator.remove();
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start read thread");
                while (true) {
                    try {
                        if (clientSelector.select(1) > 0) {
                            System.out.println("get one read client");

                            Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectionKeys.iterator();

                            while (iterator.hasNext()) {
                                SelectionKey selectionKey = iterator.next();
                                if (selectionKey.isReadable()) {
                                    try {
                                        SocketChannel clientChannel = (SocketChannel)selectionKey.channel();
                                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                        clientChannel.read(byteBuffer);
                                        byteBuffer.flip();
                                        System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                    } finally {
                                        iterator.remove();
                                        selectionKey.interestOps(SelectionKey.OP_READ);
                                    }
                                }
                            }
                        } else {
                            System.out.println("get no read");
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }).start();

    }
}
