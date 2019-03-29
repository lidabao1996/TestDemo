package quickSort;

public class QuickSortTest {
    public static void main(String[] args) {
        //int[] a = new int[]{2, 7, 4, 5, 10, 1, 9, 3, 8, 6};

        int a[] = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quicksort(a, 0, a.length - 1);

        System.out.println("排序后的结果：");
        for (int x : a) {
            System.out.println(x + " ");
        }
    }


    public static void quicksort(int a[], int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return;

        temp = a[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j) {
                j--;
            }
            //再找右边的
            while (a[i] <= temp && i < j) {
                i++;
            }
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left] = a[i];
        a[i] = temp;

        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    /**
     * 快排
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] quit(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int key = arr[l];  //先找出一个数作为基准数（这里取数组最中间的一位）

        while (l < h) {
            while (l < h && arr[h] >= key) h--; //从后向前：寻找比基准数小的数据，如果找到，停下来
            if (l < h) {  //“探测”到了符合要求的数据，则交换数据，继续顺着方向寻找
                arr[l] = arr[h];
                l++;
            }
            while (l < h && arr[l] <= key) l++; //从前向后：寻找比基准数大的数据，如果找到，停下来
            if (l < h) { ////“探测”到了符合要求的数据，则交换数据，继续顺着方向寻找
                arr[h] = arr[l];
                h--;
            }
        }
        arr[l] = key;
        if (l > low) quit(arr, low, l - 1);
        if (h < high) quit(arr, h + 1, high);
        return arr;
    }
}
