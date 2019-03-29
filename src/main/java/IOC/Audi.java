package IOC;

public class Audi implements Car {
    @Override
    public void start() {
        System.out.println("audi 开启");
    }

    @Override
    public void straight() {
        System.out.println("audi 执行");
    }

    @Override
    public void right() {
        System.out.println("audi 右转向");
    }

    @Override
    public void left() {
        System.out.println("audi 左转向");
    }

    @Override
    public void stop() {
        System.out.println("audi 熄火");
    }
}
