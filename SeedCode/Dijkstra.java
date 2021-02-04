import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 
 * 시작 정점에서의 거리가 최소인 정점을 선택해 나가면서 최단 경로를 구하는 방법
 * 경유지를 전부 다 들여다봐야한다
 * */
public class Dijkstra {

	static class Vertex implements Comparable<Vertex>{
		int no, totalDistance; // totalDistance : 출발지에서 자신까지 오는 최단거리

		public Vertex(int no, int totalDistance) {
			super();
			this.no = no;	
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.totalDistance - o.totalDistance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int start = 0;
		int end = V - 1;
		final int INFINITY = Integer.MAX_VALUE;
		
		int[][] matrix = new int[V][V];
		int[] distance = new int[V]; // 출발지에서 자신까지 오는 최단거리
		boolean[] visited = new boolean[V]; // 처리한 정점 여부 관리
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < V; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
		
		Arrays.fill(distance, INFINITY);
		
		distance[start] = 0;
		pQueue.offer(new Vertex(start, distance[start]));
		Vertex current = null;
		
		while(!pQueue.isEmpty()) {

			// 1단계 : 방문하지 않은 정점들 중 출발지에서 자신까지 오는 비용이 최소인 정점을 고려할 경유지로 선택
			current = pQueue.poll();
			
			if(visited[current.no]) continue;
			
			visited[current.no] = true;
			if(current.no == end) break;
			
			// 2단계 : 선택된 current 정점을 경유지로 해서 아직 방문하지 않은 
			// 다른 정점으로의 최단거리 비용 계산하여 유리하면 update
			for (int j = 0; j < V; j++) {
				// min ==> distance[current]
				if(!visited[j]  // 아직 방문하지 않았고
						&& matrix[current.no][j] != 0 // 둘이 인접해있고 
						&& distance[j] > current.totalDistance + matrix[current.no][j]) { // j까지 최소비용 값이 시작에서 지금까지 오는 비용보다 크다면
					distance[j] = current.totalDistance + matrix[current.no][j]; // 업데이트
					pQueue.offer(new Vertex(j, distance[j]));
				}
			}
		}
		
		System.out.println(distance[end]);
	} // end of main
} // end of class