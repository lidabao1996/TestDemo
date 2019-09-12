package ms;

public class Parent {
    {
        System.out.println("父类-代码块");
    }

    static {
        System.out.println("父类-静态代码块");
    }

    public Parent() {
        System.out.println("父类构造");
    }
}
