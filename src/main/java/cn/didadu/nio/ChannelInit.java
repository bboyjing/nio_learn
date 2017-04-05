package cn.didadu.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhangjing on 17-3-28.
 */
public class ChannelInit {
    public static void main(String[] args) throws IOException {
        // 连接到TCP网络套接字的通道
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8888));

        // 可以监听新进来的TCP连接的通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8888));

        // 能收发UDP包的通道
        DatagramChannel dc = DatagramChannel.open();

        // 连接到文件的通道
        RandomAccessFile raf = new RandomAccessFile("somefile", "r");
        FileChannel fc = raf.getChannel();
    }
}
