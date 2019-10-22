package others;

import java.util.Arrays;

public class Demo7 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
    }


    public static void rotate(int[] nums, int k) {
        int tmp;
        int length = nums.length;
        //[1,2,3,4,5,6,7]
        for (int j = 0; j < k % length; j++) {
            for (int i = nums.length - 1; i > 0; i--) {
                tmp = nums[i - 1];
                System.out.print(tmp + ",");
                nums[i - 1] = nums[i];
                nums[i] = tmp;
            }
            System.out.println("----------------------------");
            System.out.println("第"+(j+1) + "次,循环结果：" + Arrays.toString(nums));
            System.out.println();

        }
        System.out.println("---------------------result-----------------------");
        System.out.println(Arrays.toString(nums));
        System.out.println( );
        System.out.println( );
        System.out.println( );
    }


}
