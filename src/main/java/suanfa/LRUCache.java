package suanfa;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int CAPACITY;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        map = new LinkedHashMap<>(capacity, 0.75f, true);
    }


    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (isFull()) {
            map.put(key, value);
        }

        pop();//
        map.put(key, value);
    }

    /**
     * 判断是否为满
     *
     * @return
     */
    public boolean isFull() {
        if (getLength() == CAPACITY) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return map.size() == 0 ? true : false;
    }


    public int getLength() {
        return map.size();
    }

    public void mapTraverse() {
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object value = map.get(iterator.next());
            System.out.println("value = " + value);
        }

    }

    public void pop() {
        if (isEmpty()){
            System.out.println(" 没有要删除的值");
        }
        map.remove(0);
    }


}
