package cn.didadu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by zhangjing on 17-3-31.
 */
public class SelectSockets {

    public void go() throws IOException {
        int port = 1234;
        System.out.println("Listening on port " + port);

        // 初始化一个ServerSocketChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 利用JDK1.7新增的API，将ServerSocketChannel绑定本地端口
        serverChannel.bind(new InetSocketAddress(port));
        // 设为非阻塞模式
        serverChannel.configureBlocking(false);

        // 初始化一个Selector
        Selector selector = Selector.open();

        // 将ServerSocketChannel注册到Selector上
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //
            int n = selector.select();
            if (n == 0) {
                continue;
            }

            Iterator it = selector.selectedKeys().iterator();
        }

    }

}
