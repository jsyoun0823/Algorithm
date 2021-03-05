class Tree {
    class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
        }
    }

    Node root;
    static int pIndex = 0;

    /** -- Inorder + Preorder 로 트리 재현하는 함수 -- */
    public void buildTreeByInPre(int in[], int pre[]) {
        pIndex = 0;
        root = buildTreeByInPre(in, pre, 0, in.length - 1);
    }

    private Node buildTreeByInPre(int in[], int pre[], int start, int end) {
        if(start > end) { // 두개 포인터가 돌다가 서로 지나치면 재귀 종료
            return null;
        }
        // 앞에서부터 데이터 하나씩 가져오면서 인덱스 늘리며 다음 데이터 가르키게함
        Node node = new Node(pre[pIndex++]);

        // 해당 서브트리에 노드가 단 하나밖에 없다는 뜻! 더이상 밑으로 호출할 노드 x
        // 만든 노드 반환해서 트리에 추가하게 node return
        if(start == end) return node;

        // inorder에서 해당 노드의 데이터를 가지고 있는 방 번호를 기준으로
        int mid = search(in, start, end, node.data);
        node.left = buildTreeByInPre(in, pre, start, mid-1); //앞에서부터 왼쪽 서브트리를 왼쪽에 담고,
        node.right = buildTreeByInPre(in, pre, mid + 1, end); // 오른쪽 서브트리 오른쪽에 담아

        return node;
    }

    /** -- Inorder + Postorder 로 트리 재현하는 함수 -- */
    public void buildTreeByInPost(int in[], int post[]) {
        pIndex = post.length - 1; // 루트가 맨 끝에 있으니 뒤에서부터 읽는다
        root = buildTreeByInPost(in, post, 0, in.length - 1);
    }

    private Node buildTreeByInPost(int[] in, int[] post, int start, int end) {
        if(start > end) return null;

        Node node = new Node(post[pIndex--]);

        if(start == end) return node; // leaf node

        // 왼->오->루 니까, 뒤에서부터 읽을땐 반대로 루->오->왼 이므로 right부터 저장
        int mid = search(in, start, end, node.data);
        node.right = buildTreeByInPost(in, post, mid + 1, end);
        node.left = buildTreeByInPost(in, post, start, mid - 1);

        return node;
    }

    /** arr[]에서 data를 값으로 가진 index를 찾아서 반환하는 함수 */
    private int search(int arr[], int start, int end, int data) {
        int i;
        for (i = start; i <= end; i++) {
            if(arr[i] == data) return i;
        }
        return i;
    }

    /** -- inorder로 Print 하는 함수 --*/
    void printInorder(Node node) {
        if(node == null) return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }
} // end of Tree Class

public class BuildTreeTest {
    public static void main(String[] args) {
        Tree tree = new Tree();
        int[] pre = {4, 2, 1, 3, 6, 5, 7};
        int[] in = {1, 2, 3, 4, 5, 6, 7};
        int[] post = {1, 3, 2, 5, 7, 6, 4};

        tree.buildTreeByInPre(in, pre);
        tree.printInorder(tree.root);
        System.out.println();

        tree.buildTreeByInPost(in, post);
        tree.printInorder(tree.root);
    }
}
