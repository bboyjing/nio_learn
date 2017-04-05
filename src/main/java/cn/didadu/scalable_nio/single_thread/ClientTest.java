package cn.didadu.scalable_nio.single_thread;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangjing on 17-4-5.
 */
public class ClientTest {
    public static void main (String [] argv) throws Exception {
        for(int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 初始化SocketChannel，并将默认的阻塞模式改为非阻塞
                    SocketChannel sc = SocketChannel.open();
                    sc.configureBlocking(false);
                    System.out.println("initiating connection");

                    // 连接服务端
                    sc.connect(new InetSocketAddress("localhost", 1234));
                    System.out.println("connection established");
                }catch (Exception e) {

                }
            }).start();
        }
    }
}
