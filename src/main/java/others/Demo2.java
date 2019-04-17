package others;

public class Demo2 {
    public static void main(String[] args) {
        boolean flag = isAnagram("sophia","sophia");
        System.out.println("flag = " + flag);
    }

    public static boolean isAnagram(String s, String t) {
        if(s.equals(t)){
            return true;
        }
        return false;
    }
}
