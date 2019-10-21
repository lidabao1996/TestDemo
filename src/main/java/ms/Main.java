package ms;

public class Main {
    public static void compute() {
        int i = 1;
        int j = 2;

    }

    public static void main(String[] args) {
      /*  A a = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        a.m(a);
        a.m(b);
        a.m(c);
        a.m(d);*/
        compute();
        Cat cat = new Cat();
        cat.printName();

        new Thread().start();
    }
}
