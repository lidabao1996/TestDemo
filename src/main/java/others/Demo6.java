package others;

public class Demo6 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < k % len; i++) {
            for (int j = nums.length - 1; j > 0; j--) {

            }

        }
    }
}
