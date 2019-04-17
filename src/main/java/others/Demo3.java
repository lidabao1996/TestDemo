package others;

public class Demo3 {
    public static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void deleteNode(ListNode head, ListNode node) {
        //删除尾节点，采用顺序查找找到尾节点的前一节点
        if (node.next == null) {
            while (head.next != node) {
                head = head.next;
            }
            head.next = null;
        }
        //要删除的节点是头结点
        else if (head == node) {
            head = null;
        }
        //要删除的节点是中间普通节点
        else {
            node.data = node.next.data;
            node.next = node.next.next;
        }
    }

    public static void main(String[] args) {
        ListNode tail = new ListNode(1, null);
        ListNode c = new ListNode(2, tail);
        ListNode b = new ListNode(3, c);
        ListNode head = new ListNode(4, b);
        deleteNode(head, c);
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }

    }
}
