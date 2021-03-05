import java.util.Scanner;

public class BinaryTreeTravel {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    static class Tree {
        Node root;

        public void addNode (int data) {
            if(root == null) {
                root = new Node(data); // root에 값이 없으면 root에 값을 넣는다.
            } else {
                // root 존재하면, root 변경하기 위한 메소드 호출
                addNode(data, root);
            }
        }

        public void addNode (int data, Node root) {
            if(data <= root.data) {
                if(root.left == null) {
                    root.left = new Node(data);
                } else {
                    addNode(data, root.left);
                }
            } else {
                if(root.right == null) {
                    root.right = new Node(data);
                } else {
                    addNode(data, root.right);
                }
            }
        }

        public void preOrder(Node root) {
            if(root == null) return;

            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public void inOrder(Node root) {
            if(root == null) return;

            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public void postOrder(Node root) {
            if(root == null) return;

            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        Tree tree = new Tree();
        for (int i = 0; i < K; i++) {
            tree.addNode(sc.nextInt());
        }

        System.out.print("Preorder : ");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("Inorder : ");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("Postorder : ");
        tree.postOrder(tree.root);
        System.out.println();
    }
}
/* 샘플 입력 데이터
7
24 15 19 2 28 27 30
* */