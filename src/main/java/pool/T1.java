package pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class T1 {
    public static void main(String[] args) {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder().setNameFormat("");
    }
}
