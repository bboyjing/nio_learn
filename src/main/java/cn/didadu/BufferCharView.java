package cn.didadu;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * Created by zhangjing on 17-3-27.
 */
public class BufferCharView {
    public static void main(String[] args) {
        // 创建容量为7的ByteBuffer实例，并手动指定字节序
        ByteBuffer byteBuffer = ByteBuffer.allocate (7).order(ByteOrder.BIG_ENDIAN);
        // 创建CharBuffer类型的视图缓冲区
        CharBuffer charBuffer = byteBuffer.asCharBuffer( );

        // 填充原始缓冲区
        byteBuffer.put (0, (byte)0);
        byteBuffer.put (1, (byte)'H');
        byteBuffer.put (2, (byte)0);
        byteBuffer.put (3, (byte)'i');
        byteBuffer.put (4, (byte)0);
        byteBuffer.put (5, (byte)'!');
        byteBuffer.put (6, (byte)0);

        println (byteBuffer);
        println (charBuffer);
    }

    private static void println (Buffer buffer){
        System.out.println ("pos=" + buffer.position( )
                + ", limit=" + buffer.limit( )
                + ", capacity=" + buffer.capacity( )
                + ": '" + buffer.toString( ) + "'");
    }
}
