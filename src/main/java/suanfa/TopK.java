package suanfa;

//速记口诀：最小的K个用最大堆，最大的K个用最小堆。
public class TopK {
    public static void main(String[] args) {

        int[] a = {1, 17, 3, 4, 5, 6, 7, 16, 9, 10, 11, 12, 13, 14, 15, 8};
        int[] b = topK(a, 4);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + ", ");
        }

    }

    public static void heapify(int[] array, int index, int length) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        if (left < length && array[left] > array[index]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (index != largest) {
            swap(array, largest, index);
            heapify(array, largest, length);
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void buildHeap(int[] array) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, i, length);
        }
    }

    public static void setTop(int[] array, int top) {
        array[0] = top;
        heapify(array, 0, array.length);
    }

    public static int[] topK(int[] array, int k) {
        int[] top = new int[k];
        for (int i = 0; i < k; i++) {
            top[i] = array[i];
        }
        //先建堆，然后依次比较剩余元素与堆顶元素的大小，比堆顶小的， 说明它应该在堆中出现，则用它来替换掉堆顶元素，然后沉降。
        buildHeap(top);
        for (int j = k; j < array.length; j++) {
            int temp = top[0];
            if (array[j] < temp) {
                setTop(top, array[j]);
            }
        }
        return top;
    }

}
