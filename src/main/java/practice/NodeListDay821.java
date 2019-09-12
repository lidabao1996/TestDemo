package practice;

public class NodeListDay821 {
    int value;
    NodeListDay821 next;

    public NodeListDay821(int value, NodeListDay821 next) {
        this.value = value;
        this.next = next;
    }

    public static NodeListDay821 reverseNode(NodeListDay821 head) {

        NodeListDay821 pre = null;
        NodeListDay821 next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        NodeListDay821 tail = new NodeListDay821(1, null);
        NodeListDay821 b = new NodeListDay821(2, tail);
        NodeListDay821 a = new NodeListDay821(3, b);
        NodeListDay821 head = new NodeListDay821(4, a);

        NodeListDay821 node = reverseNode(head);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

}
