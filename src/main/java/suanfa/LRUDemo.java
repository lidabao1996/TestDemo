package suanfa;

public class LRUDemo {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(10);

        //lruCache.put(1,1);
        lruCache.get(1);

        System.out.println(lruCache.getLength());

        lruCache.mapTraverse();
    }
}
