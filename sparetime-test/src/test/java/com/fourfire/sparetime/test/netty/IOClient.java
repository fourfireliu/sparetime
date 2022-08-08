package com.fourfire.sparetime.test.netty;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author liuyi
 * @date 2022/8/2
 * @desc
 */

public class IOClient {

    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                Socket socket = new Socket("127.0.0.1", 8000);

                while (true) {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        System.out.println("finish");
                        Thread.sleep(2000L);
                }} catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
