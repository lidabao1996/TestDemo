package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<String>();

        ((ArrayList<String>) coll).add("aaaaa1");
        ((ArrayList<String>) coll).add("aaaaa2");
        ((ArrayList<String>) coll).add("aaaaa3");
        ((ArrayList<String>) coll).add("aaaaa4");
        ((ArrayList<String>) coll).add("aaaaa5");

        //迭代器
        Iterator<String> it = coll.iterator();

        //通过迭代器拿到数据


        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}
