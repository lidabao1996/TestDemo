package practice;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public void addNode(int value) {
        if (this.value > value) {
            if (this.left == null) {
                this.left = new TreeNode();
                this.left.value = value;
            } else {
                this.left.addNode(value);
            }
        } else {
            if (this.right == null) {
                this.right = new TreeNode();
                this.right.value = value;
            } else {
                this.right.addNode(value);
            }

        }


    }


    public TreeNode findNode(int value) {

        if (this.value > value) {
            if (this.left != null) {
                return this.left.findNode(value);
            }
        } else if (this.value < value) {
            if (this.right != null) {
                return this.right.findNode(value);
            }
        }
        return null;
    }


    public int deep(int deepNum) {
        if (this.left == null && this.right == null) {
            return 0;
        }
        int leftNum = 0;
        int rightNum = 0;

        if (this.left != null) {
            leftNum = this.left.deep(deepNum + 1);

        }
        if (this.right != null) {
            rightNum = this.right.deep(deepNum + 1);
        }
        return leftNum > rightNum ? leftNum : rightNum;
    }

}
