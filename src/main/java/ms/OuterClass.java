package ms;

public class OuterClass {
    //static String name = "OuterClass";

    public String  fun(){
        return InnerClass.name;
    }
    protected static class InnerClass {
        static String name = "我是内部类";

        public void sayHi() {
            System.out.println("dddd");
            //System.out.println(OuterClass.name);
        }


    }
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        System.out.println(outerClass.fun());

    }

}
