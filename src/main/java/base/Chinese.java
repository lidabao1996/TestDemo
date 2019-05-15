package base;

import com.google.common.collect.Lists;

import java.util.List;

public class Chinese extends Human {


    Chinese(String name) {
        //super(name);// 调用父类具有相同形参的构造方法（2）
        //prt("子类·调用父类”含一个参数的构造方法“： " + "her name is " + name);
    }


    Chinese(String name, int age) {
        this(name);// 调用具有相同形参的构造方法（3）
       // prt("子类：调用子类具有相同形参的构造方法：her age is " + age);
    }


    public static void main(String[] args) {

        Chinese chinese = new Chinese("sophia", 22);

        //List user = Lists.newArrayList();



    }

}
