package com.leh.socket;

/**
 * @Auther: leh
 * @Date: 2019/10/25 10:28
 * @Description: 测试 客户端启动
 */
public class ServerBoot {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }
}
