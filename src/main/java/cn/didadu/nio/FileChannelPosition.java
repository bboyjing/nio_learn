package cn.didadu.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by zhangjing on 17-3-28.
 */
public class FileChannelPosition {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("README.md", "r");
        // 设置position
        randomAccessFile.seek(1000);

        // 从文件中创建通道
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("file pos: " + fileChannel.position());

        // 通过randomAccessFile修改position，并打印fileChannel指向的position
        randomAccessFile.seek(500);
        System.out.println("file pos: " + fileChannel.position());

        // 通过fileChannel修改position，并打印randomAccessFile指向的position
        fileChannel.position(200);
        System.out.println ("file pos: " + randomAccessFile.getFilePointer());
    }
}
