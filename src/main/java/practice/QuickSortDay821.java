package practice;

public class QuickSortDay821 {


    public static void main(String[] args) {
        int a[] = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        sort(0, a.length - 1, a);

        System.out.println("排序后的结果：");
        for (int x : a) {
            System.out.println(x + " ");
        }
    }

    public static void sort(int left, int right, int[] arr) {
        int i, j, t, temp;


        if (left > right) {
            return;
        }
        temp = arr[left];
        i = left;
        j = right;

        while (i != j) {
            while (arr[j] >= temp && i < j) {
                j--;
            }
            while (arr[i] <= temp && i < j) {
                i++;
            }

            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        arr[left] = arr[i];
        arr[i] = temp;

        sort(left, i-1, arr);
        sort(i+1, right, arr);
    }

}
