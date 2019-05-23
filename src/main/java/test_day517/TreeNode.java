package test_day517;

/**
 * 二叉树
 */
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


    public TreeNode find(int value) {
        if (this.value == value) {
            return this;
        }

        if (this.value > value) {
            if (this.left != null) {
                return this.left.find(value);
            }
        }else if (this.value < value) {
            if (this.right != null) {
                return this.right.find(value);
            }
        }
        return null;
    }


    /**
     * 二叉树的深度
     *
     * @return
     */
    public int deep(int deepNum) {
        if (this.left == null && this.right == null) {
            return deepNum;
        }
        int rightDeep = 0;
        int leftDeep = 0;

        if (this.left != null) {
            leftDeep = this.left.deep(deepNum + 1);
        }

        if (this.right != null) {
            rightDeep = this.right.deep(deepNum + 1);
        }
        return leftDeep > rightDeep ? leftDeep : rightDeep;
    }


}
