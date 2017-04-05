package cn.didadu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by zhangjing on 17-3-27.
 */
public class HelloBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');

        // 将位置0的值替换为M，不会影响当前position
        buffer.put(0, (byte)'M');
        // 继续操作buffer
        buffer.put((byte)'w');

        // 翻转Buffer
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

        char [] myArray = new char [100];
        CharBuffer charBuffer = CharBuffer.wrap(myArray);
    }
}
