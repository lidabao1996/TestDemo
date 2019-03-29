package quickSort;

public class DemoQuick {
    public static void main(String[] args) {
        int[] a = new int[]{2, 7, 4, 5, 10, 1, 9, 3, 8, 6};
        quickSort(a, 0, a.length - 1);

        System.out.println("排序后的结果：");
        for (int x : a) {
            System.out.println(x + " ");
        }

    }


    public static void quickSort(int arr[], int left, int right) {
        int bench = arr[right];//基准
        int temp = 0;

        if (left > right) {
            return;
        }
        while (left != right) {
            //先从右边找出小于基准的数
            while (arr[right] >= bench && left < right)
                right--;


            //再从左边找出大于基准的数
            while (arr[left] <= bench && left < right)
                left++;

            if (left < right) {
                //大小数字互换位置
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //left = end 相遇
        arr[left] = arr[right];
        arr[right] = bench;

        quickSort(arr, left, right - 1);//继续处理左边的，这里是一个递归的过程
        quickSort(arr, left + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

}
