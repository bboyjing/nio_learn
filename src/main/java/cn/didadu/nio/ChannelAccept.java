package cn.didadu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangjing on 17-3-29.
 */
public class ChannelAccept {
    public static final String GREETING = "Hello I must be going.\r\n";

    public static void main (String [] args) throws IOException, InterruptedException {
        // 定义缓冲区
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes( ));

        // 初始化ServerSocketChannel，以非阻塞模式运行
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //ssc.bind(new InetSocketAddress(1234));
        ssc.socket().bind(new InetSocketAddress(1234));
        ssc.configureBlocking(false);

        while (true) {
            System.out.println ("Waiting for connections");

            // 对每一个新进来的连接都会创建一个SocketChannel
            SocketChannel sc = ssc.accept( );
            if (sc == null) {
                // 没有连接到达时
                Thread.sleep (2000);
            } else {
                System.out.println ("Incoming connection from: "
                        + sc.socket().getRemoteSocketAddress( ));
                // rewind()方法与flip()相似，但是不会影响limit字段
                buffer.rewind( );
                sc.write (buffer);
                sc.close( );
            }
        }
    }
}
