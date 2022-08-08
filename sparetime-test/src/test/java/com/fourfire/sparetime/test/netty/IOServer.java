package com.fourfire.sparetime.test.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuyi
 * @date 2022/8/1
 * @desc
 */

public class IOServer {

    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========server socket start=========");
                while (true) {
                    try (Socket socket = serverSocket.accept();) {
                        System.out.println("======accept client socket======");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try (InputStream inputStream = socket.getInputStream()) {
                                    int len;
                                    byte[] data = new byte[1024];
                                    while ((len = inputStream.read(data)) != -1) {
                                        System.out.println(new String(data, 0, len));
                                    }
                                } catch (IOException e) {}
                            }
                        }).start();
                    } catch (IOException e) {}
                }
            }
        }).start();
    }
}
