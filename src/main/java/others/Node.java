package others;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int value;
    private Node next;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(int value) {
        super();
        this.value = value;
    }


    public void add(int value) {
        Node node = this;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node(value);
    }

    public Node reverse() {
        Node t = new Node(-1);
        Node m = this;
        t.next = m;
        Node mf = m;
        while (m != null && m.next != null) {
            t.next = m.next;
            m.next = m.next.next;
            t.next.next = mf;
            mf = t.next;
        }
        return t.next;
    }

    public void print() {
        List<Integer> list = new ArrayList<>();
        Node node = this;
        while (node != null) {
            list.add(node.value);
            node = node.next;
        }
        System.out.println(list.toString());

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        Node head = null;
        for (int i = 0; i < arr.length; i++) {
            if (head == null) {
                head = new Node(arr[i]);
            } else {
                head.add(arr[i]);
            }
        }
        head.print();

        head = head.reverse();
        head.print();
    }
}
