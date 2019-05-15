package suanfa;

import java.util.Arrays;

/**
 * @author sophia
 * @since 2019-05-05 10:28
 */
public class Sort {


    public static void main(String[] args) {
        int[] arrs = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println(arrs.length - 1);
        sort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));

    }


    public static void sort(int[] arrs, int start, int end) {


        if (start > end) {
            return;
        }

        int bench, tmp, left, right;
        bench = arrs[start];
        left = start;
        right = end;

        while (left != right) {

            while (arrs[right] >= bench && left < right) {
                right--;
            }


            while (arrs[left] <= bench && left < right) {
                left++;
            }

            if (left < right) {
                tmp = arrs[left];
                arrs[left] = arrs[right];
                arrs[right] = tmp;
            }

        }

        arrs[start] = arrs[left];

        arrs[left] = bench;


        sort(arrs, start, left - 1);//左边部分
        sort(arrs, left + 1, end);//右边部分

    }

}
