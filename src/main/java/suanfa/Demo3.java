package suanfa;

/**
 * 2019-04-16
 */
public class Demo3 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};


        method(nums);


    }

    public static void method(int[] nums){
        int tmp = 1;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[0]) {
                continue;
            }
            nums[len] = nums[i];
            nums[i] = tmp;
            len++;
        }

        for (int j = 0;j<nums.length;j++){
            System.out.println("nums:" + nums[j]);
        }
        System.out.println("len = " + len);
    }


}
