package others;

/**
 * 2019-04-17
 * 数组翻转
 */
public class Demo1 {
    public static void main(String[] args) {
        String[] arr = {"h", "e", "l", "l", "o", "ass"};
        String tmp;
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - i - 1) {
                break;
            }
            if (i - (arr.length - i - 1) == 1) {
                break;
            }


            tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;

            //System.out.print(arr[arr.length - i - 1]+" ");
        }


        System.out.println("----------------------------------------");
        for (int j = 0; j < arr.length; j++) {
            System.out.println("arr[j] = " + arr[j]);
        }
    }
}
