package test_0605;

/**
 * 反复写二叉树
 */
public class TreeTest {
    private int value;
    private TreeTest left;
    private TreeTest right;

    public void add(int value) {
        if (this.value > value) {
            this.left = new TreeTest();
            this.left.value = value;
        } else if (this.value < value) {
            this.right = new TreeTest();
            this.right.value = value;
        }

    }

    public TreeTest find(int value) {
        if (this.value == value) {
            return null;
        }

        if (this.value > value) {
            if (this.left != null) {
                return this.left.find(value);
            }
        } else if (this.value < value) {
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
    public int deep(int deep) {
        if (this.left == null && this.right == null) {
            return deep;
        }

        int leftDeep = 0;
        int rightDeep = 0;




        if (this.left != null) {
            leftDeep = this.left.deep(deep + 1);
        }
        if (this.right != null) {
            leftDeep = this.right.deep(deep + 1);
        }


        return leftDeep > rightDeep ? leftDeep : rightDeep;
    }

    public static void main(String[] args) {



    }
}
