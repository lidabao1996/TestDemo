package others;

/**
 * 面试中打印出导出第K个节点
 */
public class ListNode {
    int val;
    ListNode next;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode printKNode(ListNode head, int k) {
        ListNode p1 = new ListNode();
        ListNode p2 = new ListNode();

        if (head == null) return null;

        int count = 0;

        while (count < k && p2 != null) {
            count++;
            p2 = p2.next;
        }

        while (p2 !=null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

}
