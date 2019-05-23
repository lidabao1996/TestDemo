package tree;

public class Node {
    int value;
    Node left;
    Node right;


    void add(int value) {
        if (this.value < value) {
            if (this.right == null) {
                this.right = new Node();
                this.right.value = value;
            } else {
                this.right.add(value);
            }

        } else if (this.value > value) {
            if (this.left == null) {
                this.left = new Node();
                this.left.value = value;
            } else {
                this.left.add(value);
            }
        }

    }

    Node find(int value){
        if(this.value == value){
            return this;
        }
        if(this.value > value){
            if(this.left != null){
                return this.left.find(value);
            }
        }else if(this.value < value){
            if(this.right != null){
                return this.right.find(value);
            }
        }
        return null;
    }

    public int deep(int deep){
        if(this.left == null && this.right == null){
            return deep;
        }
        int leftDeep = 0;
        int rightDeep = 0;
        if(this.left != null){
            leftDeep = this.left.deep(deep+1);
        }

        if(this.right != null){
            rightDeep = this.right.deep(deep+1);
        }
        return leftDeep > rightDeep ? leftDeep:rightDeep;
    }
}
