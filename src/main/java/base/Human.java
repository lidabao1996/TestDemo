package base;

public class Human {
    public static void prt(String s) {
        System.out.println(s);
    }


    Human() {
        prt("父类·无参数构造方法： "+"A Person.");
    }//构造方法(1)

    Human(String name) {
        prt("父类·含一个参数的构造方法： "+"A person's name is " + name);
    }//构造方法(2)

}
