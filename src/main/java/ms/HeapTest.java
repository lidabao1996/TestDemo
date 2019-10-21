package ms;

import java.util.ArrayList;

public class HeapTest {
    //100KB
    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
