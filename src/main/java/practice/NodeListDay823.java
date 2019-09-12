package practice;

public class NodeListDay823 {
    int value;
    NodeListDay823 next;

    public NodeListDay823(int value, NodeListDay823 next) {
        this.value = value;
        this.next = next;
    }


    public static NodeListDay823 reverseNode(NodeListDay823 head) {
        NodeListDay823 pre = null;
        NodeListDay823 next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {

        NodeListDay823 tail = new NodeListDay823(1, null);
        NodeListDay823 c = new NodeListDay823(2, tail);
        NodeListDay823 b = new NodeListDay823(3, c);
        NodeListDay823 head = new NodeListDay823(4, b);

        NodeListDay823 reverseNode = reverseNode(head);

        while (reverseNode != null) {

            System.out.println(reverseNode.value);
            reverseNode = reverseNode.next;
        }


    }

}
