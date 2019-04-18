package others;

public class Demo4 {
    public static void main(String[] args) {
        char[] arr = {'f', 'e', 'd', 'c', 'b', 'a'};
        char tmp;
        for (int i = 0; i < arr.length / 2; i++) {
            tmp =  arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
    }
}
