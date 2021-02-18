import java.util.LinkedList;
import java.util.Queue;

/**
 * 자료구조
 * 	선형자료구조	: 1:1 관계, 배열, 리스트, 스택, 큐, 덱
 * 	비선형자료구조	: 1:N 관계, 그래프
 * 		용어, 저장, 순회
 *
 * 	트리 : 그래프 중 사이클이 없는 그래프
 * 	이진트리 : 차수가 최대 2인 트리
 *
 */

public class CompleteBinaryTreeTest {
	public static void main(String[] args) {
		CompleteBinaryTree cbt = new CompleteBinaryTree(10);
		// 이진 탐색트리에 값을 입력
		for (char i = 'A'; i < 'A' + 10; i++) {
			cbt.add(i);
		}
		cbt.bfs(); // bfs 메서드를 호출해서 순회
		System.out.println();
		cbt.bfs2();
		System.out.println();

		cbt.printPreOrder();  // 전위순회
		cbt.printInOrder();   // 중위순회
		cbt.printPostOrder(); // 후위순회

	} // end of main
} // end of class

/** 완전 이진 트리 */
class CompleteBinaryTree {
	private char[] nodes; // 완전이진트리의 정점데이터를 저장할 배열
	private int size; // 배열의 크기
	private int lastIndex; // 마지막 정점

	public CompleteBinaryTree(int size) {
		this.size = size;
		nodes = new char[size + 1]; // 0번인덱스 사용안함
	}

	public boolean isEmpty() {
		return lastIndex == 0;
	}

	public boolean isFull() {
		return lastIndex == size;
	}

	public void add(char c) {
		if (isFull()) {
			System.out.println("포화상태입니다");
			return;
		}
		nodes[++lastIndex] = c;
	}

	public void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>(); // 인덱스를 저장할 큐

		// 시작정점 지정, 큐에 추가, 방문 체크
		queue.offer(1); // 0번 안씀
		while(!queue.isEmpty()) { // 반복 큐가 빌 때까지
			int curr = queue.poll(); // 큐에서 하나 꺼냄
			System.out.print(nodes[curr]);

			// 정점에 인접한 정점 중 방문하지 않은 정점을 큐에 넣기
			if(curr*2 <= lastIndex) queue.offer(curr*2);
			if(curr*2+1 <= lastIndex) queue.offer(curr*2+1);
		}
	}

	/** 레벨을 출력하기 */
	public void bfs2() {
		Queue<Integer> queue = new LinkedList<Integer>(); // 인덱스를 저장할 큐
		// 시작정점 지정, 큐에 추가, 방문 체크
		queue.offer(1); // 0번 안씀
		int level = 0;
		while(!queue.isEmpty()) { // 반복 큐가 빌 때까지
			// 현재 큐에 들어있는 크기만큼 반복해서 작업
			int size = queue.size();
			System.out.print("level " + level + " : ");
			while(--size >= 0) {
				int curr = queue.poll(); // 큐에서 하나 꺼냄
				System.out.print(nodes[curr]);
				// 정점에 인접한 정점 중 방문하지 않은 정점을 큐에 넣기
				if(curr*2 <= lastIndex) queue.offer(curr*2);
				if(curr*2+1 <= lastIndex) queue.offer(curr*2+1);
			}
			System.out.println();
			level++;
		}
	}

	/** 전위 순회 */
	public void printPreOrder() {
		System.out.print("전위순회 : ");
		preOrder(1);
		System.out.println();
	}
	public void preOrder(int curr) {
		if (curr > lastIndex) return;
		System.out.print(nodes[curr] + " "); // 부모정점(현재노드 방문처리)
		preOrder(curr * 2); // 좌측 자식
		preOrder(curr * 2 + 1); // 우측 자식
	}

	/** 중위 순회 */
	public void printInOrder() {
		System.out.print("중위순회 : ");
		inOrder(1);
		System.out.println();
	}
	public void inOrder(int curr) {
		if (curr > lastIndex) return;
		inOrder(curr * 2); // 좌측 자식
		System.out.print(nodes[curr] + " "); // 부모정점(현재노드 방문처리)
		inOrder(curr * 2 + 1); // 우측 자식
	}

	/** 후위 순회 */
	public void printPostOrder() {
		System.out.print("후위순회 : ");
		postOrder(1);
		System.out.println();
	}
	public void postOrder(int curr) {
		if (curr > lastIndex) return;
		postOrder(curr * 2); // 좌측 자식
		postOrder(curr * 2 + 1); // 우측 자식
		System.out.print(nodes[curr] + " "); // 부모정점(현재노드 방문처리)
	}

} // end of class CompleteBinaryTree






