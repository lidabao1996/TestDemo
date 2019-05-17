package test_day517;

/**
 * 链表
 */
public class NodeList {
    int value;
    NodeList next;

    public NodeList(int value, NodeList next) {
        this.value = value;
        this.next = next;
    }


    /*public NodeList reverseNode(NodeList head){


    }*/


    public static void main(String[] args) {
        NodeList tail = new NodeList(1, null);
        NodeList b = new NodeList(2, tail);
        NodeList a = new NodeList(3, b);
        NodeList head = new NodeList(4, a);


        while (head!= null) {
            System.out.println(head.value);
            head = head.next;
        }
    }
}
