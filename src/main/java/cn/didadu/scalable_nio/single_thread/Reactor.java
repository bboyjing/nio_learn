package cn.didadu.scalable_nio.single_thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangjing on 17-4-5.
 */
public class Reactor implements Runnable{
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        // 初始化ServerSocketChannel，以非阻塞模式运行
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        // 初始化Selector
        selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 在SelectionKey上绑定一个附属对象Acceptor
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 阻塞直至事件就绪
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    // 分发
                    dispatch((SelectionKey)(it.next()));
                }
                // 需要自己清除selectedKeys
                selected.clear();
            }
        }catch (IOException ex) {

        }
    }

    void dispatch(SelectionKey k) {
        /**
         * 获取SelectionKey中的attachment，并执行该attachment的run()方法
         * 拿第一个到达的socket连接来看，该attachment为一个Acceptor实例
         */
        Runnable r = (Runnable)(k.attachment());
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable{
        public void run() {
            try {
                // 获取新连接的SocketChannel
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // 具体的处理逻辑
                    new Handler(selector, socketChannel);
                }
            } catch (IOException ex) {

            }
        }
    }
}
