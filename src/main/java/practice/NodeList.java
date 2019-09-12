package practice;

public class NodeList {
    public int value;
    public NodeList next;

    public NodeList(int value, NodeList next) {
        this.value = value;
        this.next = next;
    }

    public static NodeList reverseNodeList(NodeList head) {
        NodeList pre = null;
        NodeList next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;

            pre = head;
            head = next;
        }

        return pre;
    }


    public static void main(String[] args) {
        NodeList tail = new NodeList(1, null);
        NodeList c = new NodeList(2, tail);
        NodeList b = new NodeList(3, c);
        NodeList head = new NodeList(4, b);

        NodeList reverseNode = reverseNodeList(head);

        while (reverseNode != null) {

            System.out.println(reverseNode.value);
            reverseNode = reverseNode.next;
        }


    }


}
