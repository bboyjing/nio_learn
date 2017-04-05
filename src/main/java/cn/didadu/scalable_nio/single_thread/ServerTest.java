package cn.didadu.scalable_nio.single_thread;

import java.io.IOException;

/**
 * Created by zhangjing on 17-4-5.
 */
public class ServerTest {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(1234);
        reactor.run();
    }
}
