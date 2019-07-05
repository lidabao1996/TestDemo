package test_0605;

public class Node {
     int value;
     Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public static Node reverseNode(Node head) {
        Node dummy = new Node(-1, head);
        Node m = head;
        Node f = m;

        if (m.next != null) {
            dummy.next = m.next;
            m.next = dummy.next.next;
            dummy.next.next = f;
            f = dummy.next;
        }

        return dummy.next;
    }


    public static void main(String[] args) {
        Node tail = new Node(1, null);
        Node b = new Node(2, tail);
        Node a = new Node(3, b);
        Node head = new Node(4, a);


        Node reverse = reverseNode(head);
        while (reverse != null) {
            System.out.println("reverse.value = " + reverse.value);
            reverse = reverse.next;
        }


    }


}
