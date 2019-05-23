package test_day517;


/**
 * 二叉树测试
 */
public class TreeMain {
    public static void main(String[] args) {

        int[] arr = {3, 7, 9, 1, 5, 40, 21, 45, 39, 78, 20, 35};

        TreeNode root = new TreeNode();
        root.value = arr[0];

        for (int i = 1; i < arr.length; i++) {
            root.addNode(arr[i]);
        }


        TreeNode node = root.find(21);
        if (node != null) {
            System.out.println("value=" + node.value + ",right=" + (node.right != null ? node.right.value : "")
                    + ",left=" + (node.left != null ? node.left.value : ""));
        }

        int deep = root.deep(1);
        System.out.println("deep = " + deep);

    }
}
