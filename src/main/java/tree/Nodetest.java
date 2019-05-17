package tree;

public class Nodetest {
    public static void main(String[] args) {


        int[] arr = {3, 7, 9, 1, 5, 40, 21, 45, 39, 78, 20, 35};

        Node root = new Node();
        root.value = arr[0];

        for (int i = 1; i < arr.length; i++) {

            root.add(arr[i]);
        }

        Node node = root.find(40);
        if (node != null) {
            System.out.println("value=" + node.value + ",right=" + (node.right != null ? node.right.value : "")
                    + ",left=" + (node.left != null ? node.left.value : ""));
        }


    }


}
