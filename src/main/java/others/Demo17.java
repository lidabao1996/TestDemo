package others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Demo17 {
    public static void main(String[] args) {
        HashMap<List<String>,Object> vo = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("heihei");
        Object value = new Object();
        vo.put(list,value);
        System.out.println(vo.get(list));
       /* list.add("hello world");
        System.out.println(vo.get(list));*/

       int[] arr = new int[5];
        arr[0] = 1;

        int[] a = {1,2,3};

    }
}
