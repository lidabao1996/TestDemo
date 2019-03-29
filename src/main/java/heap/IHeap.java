package heap;

/**
 * 堆接口
 *
 * @param <T>
 */
public interface IHeap<T> {
    int size(); //当前元素的长度

    boolean isEmpty();

    void add(T data);

    T pop();//弹出堆顶元素

    T remove();//移除某位置元素

    T peek();//过去堆顶元素

}
