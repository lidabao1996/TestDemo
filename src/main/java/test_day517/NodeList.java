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


    public static NodeList reverseNode(NodeList head) {
        NodeList dummy = new NodeList(-1, head);
        NodeList m = head;
        NodeList f = m;
        while (m.next != null) {
            dummy.next = m.next;
            m.next = dummy.next.next;
            dummy.next.next = f;
            f = dummy.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        NodeList tail = new NodeList(1, null);
        NodeList b = new NodeList(2, tail);
        NodeList a = new NodeList(3, b);
        NodeList head = new NodeList(4, a);

        NodeList reverseNode = reverseNode(head);


        while (reverseNode != null) {
            System.out.println(reverseNode.value);
            reverseNode = reverseNode.next;
        }
    }
}
