package data;

public class Demo3 {
    public static void main(String[] args){
        int[] fun = {0,1,2,3,4,5,6};
        System.arraycopy(fun,0,fun,3,3);
        for(int i=0;i<fun.length;i++){
            System.out.print(i);
        }

    }
}
