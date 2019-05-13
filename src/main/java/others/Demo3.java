package others;

/**
 * 链表 的删除和翻转
 */
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


    static ListNode reverseNodeList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            System.out.println("pre = " + pre.data);
        }


        return pre;
    }


    public static void main(String[] args) {
        ListNode tail = new ListNode(1, null);
        ListNode c = new ListNode(2, tail);
        ListNode b = new ListNode(3, c);
        ListNode head = new ListNode(4, b);
        //deleteNode(head, c);
       /* while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }*/

        System.out.println("---------------翻转后------------------");
        ListNode reverslist = reverseNodeList(head);
        System.out.println("reverslist = " + reverslist);
        while (reverslist != null) {
            System.out.println(reverslist.data);
            reverslist = reverslist.next;
        }

    }
}
