package practice;

public class QuickSortDay823 {
    public static void main(String[] args) {
        int a[] = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(0, a.length - 1, a);
        System.out.println("排序后的结果：");
        for (int x : a) {
            System.out.println(x + " ");
        }
    }

    public static void quickSort(int left, int right, int[] arr) {
        int i, j, t, bench;
        if (left > right) {
            return;
        }
        i = left;
        j = right;
        bench = arr[left];

        while (i != j) {
            while (arr[j] >= bench && i < j) {
                j--;
            }

            while (arr[i] <= bench && i < j) {
                i++;
            }

            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }

        }

        arr[left] = arr[i];
        arr[i] = bench;

        quickSort(left, i - 1, arr);
        quickSort(i + 1, right, arr);
    }


}
