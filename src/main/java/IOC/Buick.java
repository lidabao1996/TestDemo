package IOC;

public class Buick implements Car {
    @Override
    public void start() {
        System.out.println("buick 开启");
    }

    @Override
    public void straight() {
        System.out.println("buick 执行");
    }

    @Override
    public void right() {
        System.out.println("buick 右转向");
    }

    @Override
    public void left() {
        System.out.println("buick 左转向");
    }

    @Override
    public void stop() {
        System.out.println("buick 熄火");
    }
}
