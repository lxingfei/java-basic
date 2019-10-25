package com.leh.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: leh
 * @Date: 2019/10/25 09:42
 * @Description: 不适用netty情况下 - 模拟服务端
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务端socket创建成功，端口:" + port);
        } catch (IOException e) {
            System.out.println("服务端socket创建成功" + e);
        }
    }

    public void start() {
        new Thread(() -> doStart()).start();
        System.out.println("服务端socket启动成功，端口:" + serverSocket.getLocalPort());
    }

    private void doStart() {
        while (true){
            try {
                //监听客户端连接 - 阻塞的 只有当一个客户端过来之后才会创建socket
                Socket client = serverSocket.accept();
                //创建处理器处理接收到的客户端数据
                new ClientHandler(client).start();
            } catch (IOException e) {
                System.out.println("服务端socket启动异常");
            }
        }
    }
}
