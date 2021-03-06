package com.leh.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Auther: leh
 * @Date: 2019/10/25 09:58
 * @Description: 处理客户端数据的类
 */
public class ClientHandler {
    private static final int MAX_DATA_LEN = 1024;

    private final Socket socket;

    //持有客户端socket
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("新客户端接入");
        new Thread(() -> doStart()).start();

    }

    //客户端与服务端通信的过程
    private void doStart() {
        try {
            InputStream in = socket.getInputStream();
            while (true) {
                byte[] data = new byte[MAX_DATA_LEN];
                int len ;
                while ((len = in.read(data)) != -1){
                    String message = new String(data, 0, len);
                    System.out.println("客户端传来消息: " + message);
                    //将接收到的数据原样写回去
                    socket.getOutputStream().write(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
