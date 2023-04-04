public class RedBlackTree {
    private Node root;

    private class Node {
        private int value;
        private Node left;
        private Node right;
        private boolean color;
    }

    private void insertNode(Node node, int value){
        if(node.value == value) return;
            else{
                if (node.value > value){
                if(node.left != null){
                    insertNode(node.left, value);
                    node.left = balance(node.left);
                }else{
                    node.left = new Node();
                    node.left.color = true;
                    node.left.value = value;
                }
            }else{
                if (node.right != null){
                    insertNode(node.right, value);
                    node.right = balance(node.right);
                }else{
                    node.right = new Node();
                    node.right.color = true;
                    node.right.value = value;
                }
            }
        }
    }

    private Node rightSwap(Node node){
        Node rightChild = node.right;
        Node temp = rightChild.left;
        rightChild.left = node;
        node.right = temp;
        rightChild.color = node.color;
        node.color = true;
        return rightChild;
  }

    private Node leftSwap(Node node){
        Node leftChild = node.left;
        Node temp = leftChild.right;
        leftChild.right = node;
        node.left = temp;
        leftChild.color = node.color;
        node.color = true;
        return leftChild;
  }

    private void colorSwap(Node node){
        node.right.color = false;
        node.left.color = false;
        node.color = true;
    }

    private Node balance(Node node){
        Node result = node;
        boolean needBalance = true;
        while (needBalance){
            needBalance = false;
                if (result.right != null && result.right.color &&
                    (result.left == null || !result.left.color)){
                    needBalance = true;
                result = rightSwap(result);
            }
                if (result.left != null && result.left.color &&
                    result.left.left != null && result.left.left.color){
                    needBalance = true;
                result = leftSwap(result);
            }
                if (result.left != null && result.left.color &&
                    result.right != null && result.right.color) {
                    needBalance = true;
                colorSwap(result);
            }
        }
        return result;
    }

    public void add(int value) {
        if(root!= null) {
            insertNode(root, value);
            root = balance(root);
            root.color = false;
        } else {
            root = new Node();
            root.color = false;
            root.value = value;
        }
    }
    public void print(){
        print(root);
        }
        
        private void print(Node node){
            if(node == null)
                return;
            System.out.printf("(%c)", !node.color ? 'B' : 'R');
            System.out.println(node.value);
            if(node.left != null)
                System.out.println(node.left.value);
            if(node.right != null)
                System.out.println(node.right.value);
            System.out.println();
        
            print(node.left);
            print(node.right);
        
        }
        
        public static void main(String args[]){
            RedBlackTree tree = new RedBlackTree();
            for(int i=1; i<=20; i++)
                tree.add(i);
            tree.print(tree.root);
        }
}