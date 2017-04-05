package cn.didadu.scalable_nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangjing on 17-4-1.
 */
public class Servce {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);
        while (!Thread.interrupted()) {
            new Thread(new Handler(ss.accept())).start();
        }
    }

    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private byte[] process(byte[] cmd) {
            return null;
        }
    }
}
