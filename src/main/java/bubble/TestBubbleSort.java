package bubble;

public class TestBubbleSort {
    public static void main(String[] args) {
        int arr[] = new int[]{3, 6, 4, 2, 11, 10};
        bubbleSort(arr);
    }

    public static void bubbleSort(int arr[]) {
        int i, j, temp, len = arr.length;
        for (i = 0; i < len - 1; i++) {
            for (j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }


            }
        }
        for (int k = 0; k < arr.length; k++) {
            System.out.println(arr[k]);
        }

    }


}
