package data;

public class Demo2 {
    public static void main(String[] args) {
        String str = new String("hello");//在堆上
        String after_str = "hello";//在常量池
        System.out.println(str+after_str);

    }
}
