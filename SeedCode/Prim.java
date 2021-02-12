import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 
 * 하나의 정점에서 연결된 간선들 중에 하나씩 선택하면서 MST를 만들어 가는 방식
 * */
public class Prim {
	
	static class Node implements Comparable<Node>{
		int no, edge; // 정점, 가중치
		public Node(int no, int edge) {
			this.no = no;
			this.edge = edge;
		}
		public int compareTo(Node o) { // 가중치가 작은 값을 먼저 나오게(우선순위를 높임)
			return this.edge - o.edge;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] input = new int[N][N]; // 인접행렬
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N]; // 정점의 사용여부를 체크할 배열
			// 신장트리에 포함된 정점들과 자신을 연결할 때 최소 간선비용
		
		// 정렬의 시간을 개선해보자
		PriorityQueue<Node> queue = new PriorityQueue<>();
		// 계속 비교작업해서 (내부적으로 Heap) 가장 작은 값을 위쪽으로 올려줌
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		} // i 노드에서 j 노드까지의 비용을 모두 배열에 저장
		
		int result = 0, nodeCount = 0;
		// 0번 정점을 시작정점으로 한다고 가정
		minEdge[0] = 0; // 시작점 최소간선비용은 0
		queue.offer(new Node(0, 0));
		
		while(!queue.isEmpty()) {
			// 최소값을 구하기 위한 작업이 필요없다 (우선순위 큐기 때문에 꺼내면 알아서 최소값임)
			Node minVertex = queue.poll();
			if(visited[minVertex.no]) continue;
			
			result += minVertex.edge; // 신장트리 비용 누적
			visited[minVertex.no] = true; // 신장트리에 포함시킴
			
			if(++nodeCount == N) break; // 전체 정점의 개수와 같아지면 종료
			
			// 선택된 최소비용 정점 기준으로 신장트리에 포함되지 않은 다른 정점으로의 비용 계산하여 최소값 갱신
			for (int i = 0; i < N; i++) {
				if(!visited[i] && input[minVertex.no][i] != 0
						&& minEdge[i] > input[minVertex.no][i]) {
					minEdge[i] = input[minVertex.no][i];
					queue.offer(new Node(i, input[minVertex.no][i]));
				}
			} // 업데이트
		}
		System.out.println(result);
	}
	
}
