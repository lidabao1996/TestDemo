package test_0605;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "大象");
        map.put("2", "猴子");
        map.put("3", "老虎");

        //遍历map中的key
        for (String key : map.keySet()) {
            System.out.println("key = " + key);

        }


        //遍历map中的值

    }
}
