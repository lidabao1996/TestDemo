package others;

import java.util.Arrays;

public class Demo12 {
    public static void main(String[] args) {
        int[] arr = {2, 8, 13, 11, 6, 7};
        System.out.println("排序前：" + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    // 元素交换
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
