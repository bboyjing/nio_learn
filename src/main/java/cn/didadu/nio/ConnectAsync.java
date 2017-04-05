package cn.didadu.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangjing on 17-3-29.
 */
public class ConnectAsync {
    public static void main (String [] argv) throws Exception {
        // 初始化SocketChannel，并将默认的阻塞模式改为非阻塞
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("initiating connection");

        // 连接服务端
        sc.connect(new InetSocketAddress ("localhost", 1234));

        // 判断是否完成连接
        while (!sc.finishConnect()) {
            System.out.println ("doing something...");
        }

        System.out.println("connection established");
    }
}
