import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_4256_트리 {

    /** 트리 Class */
    static class Tree {
        class Node { // 노드 클래스
            int data;
            Node left, right;
            public Node(int data) { this.data = data; }
        }

        Node root; // root 노드
        static int pIndex = 0; // 앞에서부터 읽어오기 위한 인덱스

        public void buildTreeByInPre(int in[], int pre[]) { // pIndex = 0부터 시작해, 루트노드를 구하기 위해 트리 재현 함수 호출
            pIndex = 0;
            root = buildTreeByInPre(in, pre, 0, in.length - 1);
        }

        /** inorder 와 preorder 로 트리 재현하는 함수 */
        private Node buildTreeByInPre(int[] in, int[] pre, int start, int end) {
            if(start > end) return null; // 재귀 종료 조건

            Node node = new Node(pre[pIndex++]); // preorder는 맨 앞 원소가 root 노드이므로 앞에서부터 읽어온다.

            if(start == end) return node; // leaf node

            int mid = search(in, start, end, node.data); // inorder에서 data 기준으로 중앙값을 구한다
            node.left = buildTreeByInPre(in, pre, start, mid - 1); // 왼쪽 서브트리
            node.right = buildTreeByInPre(in, pre, mid + 1, end); // 오른쪽 서브트리

            return node;
        }

        /** arr[]에서 data를 값으로 가진 index를 찾아서 반환하는 함수 */
        private int search(int[] arr, int start, int end, int data) {
            int i;
            for (i = start; i <= end; i++) {
                if(arr[i] == data) return i;
            }
            return i;
        }

        /** 후위 순회 출력 함수 : left -> right -> root */
        public void printPostorder(Node node) {
            if(node == null) return;

            printPostorder(node.left);
            printPostorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine()); // 노드 개수

            int[] preOrder = new int[n]; // 전위 순회 결과
            int[] inOrder = new int[n]; // 중위 순회 결과

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
            }

            Tree tree = new Tree(); // 트리 생성
            tree.buildTreeByInPre(inOrder, preOrder); // inorder 결과와 preorder 결과로 트리 재현
            tree.printPostorder(tree.root); // 완성된 트리를 postorder로 출력
            System.out.println(); // 테스트케이스 줄바꿈

        } // end of for test case
    } // end of main
}
