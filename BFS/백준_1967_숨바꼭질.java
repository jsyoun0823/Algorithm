import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1967_숨바꼭질 {
	private static int N, K; // 수빈이 시작 위치, 동생의 위치 
	private static boolean[] visited; // 위치 방문 여부 담길 배열
	private static Queue<Integer> q = new LinkedList<Integer>(); // 수빈이 위치가 담길 객체
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치 N (0 ≤ N ≤ 100,000)
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치 K (0 ≤ K ≤ 100,000)
		visited = new boolean[100001]; // 위치 방문 여부 담길 배열, 수빈이의 최대 위치가 100000 이므로 최대값은 100001 로 초기화
		find(); // bfs 시작
	} // end of main
	
	/** 수빈이의 위치와 동생의 위치가 같아질 때까지의 위치를 이동하고, 그 때까지 걸린 시간을 출력한 후 리턴 */
	public static void find() { // bfs 탐색
		int cnt = 0; // 몇 초 지났는지 체크
		q.offer(N); // 수빈이 시작 위치를 저장
		visited[N] = true;
		while (!q.isEmpty()) { // 큐가 빌 때까지 반복
			int len = q.size(); // 현재 큐의 크기 저장
			for (int i = 0; i < len; i++) { // 큐의 크기만큼 반복
				int cur = q.poll(); // 큐에서 수빈이 위치 꺼내서 저장
				if (cur == K) { // 수빈이 현재위치와 K가 같아지는 순간 수빈이는 동생을 찾았으므로 종료
					System.out.println(cnt); // 현재 몇 초 지났는지 출력
					return;
				}
				if (cur - 1 >= 0 && !visited[cur - 1]) { // 현재위치에서 X-1 로 이동
					q.offer(cur - 1); // 큐에 다음 위치 저장
					visited[cur - 1] = true;
				}
				if (cur + 1 <= 100000 && !visited[cur + 1]) { // 현재위치에서 X+1 로 이동
					q.offer(cur + 1); // 큐에 다음 위치 저장
					visited[cur + 1] = true;
				}
				if (cur*2 <= 100000 && !visited[cur * 2]) { // 현재위치에서 2*X로 이동
					q.offer(cur * 2); // 큐에 다음 위치 저장
					visited[cur * 2] = true;
				}
			}
			++cnt;
		} // end of while
	} // end of find
} // end of class
