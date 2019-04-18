package others;

import java.util.Arrays;

public class Demo5 {
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        //int[] nums = {1, 2, 2, 3, 3,3};
        int singleNum = singleNumber(nums);
        System.out.println("singleNum = " + singleNum);
    }

    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
            System.out.println("result " + result);
        }
        return result;
    }
}
