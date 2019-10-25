package com.leh.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @Auther: leh
 * @Date: 2019/10/25 10:11
 * @Description: 模拟客户端
 */
public class Client {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    private static final int SLEEP_TIME = 5000;

    private static final int MAX_DATA_LEN = 1024;


    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(HOST, PORT);
            new Thread(() -> sendMsgToServer(socket)).start();
            new Thread(() -> receiveServerMsg(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMsgToServer(Socket socket) {
        System.out.println("客户端启动成功!");
        while (true) {
            String msg = "hello server, i am client";
            System.out.println("客户端发送数据: " + msg);
            try {
                socket.getOutputStream().write(msg.getBytes());
            } catch (IOException e) {
                System.out.println("客户端发送数据出错!");
            }
            sleep();
        }
    }

    private static void receiveServerMsg(Socket socket) {
        while (true) {
            try {
                byte[] data = new byte[MAX_DATA_LEN];
                int len;
                while ((len = socket.getInputStream().read(data)) != -1) {
                    String receiveMsg = new String(data, 0, len);
                    System.out.println("客户端接收服务端数据:" + receiveMsg);
                }
            } catch (IOException e) {
                System.out.println("客户端接收服务端数据异常");
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
