package suanfa;

/**
 * 查找二叉树
 */
public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;


    void addNode(TreeNode node) {
        if (node.data < this.data) {
            if (this.right == null) {
                this.right = new TreeNode();
                this.right.data = node.data;
            } else {
                addNode(node);
            }


        } else if (node.data > this.data) {
            if (this.left == null) {
                this.left = new TreeNode();
                this.left.data = node.data;
            } else {
                addNode(node);
            }
        }
    }



    void findNode(int value){
        if (this.data == value){

        }

    }
}
