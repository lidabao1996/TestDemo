package ms;

public class Son extends Parent{
    {
        System.out.println("son-代码块");
    }

    static {
        System.out.println("Son静态代码块");
    }

    public Son() {
        System.out.println("Son构造");
    }

    public static void main(String[] args) {
        Son son = new Son();
    }
}
