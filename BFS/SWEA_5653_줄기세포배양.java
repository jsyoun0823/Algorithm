import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시뮬레이션. 백준에 나무재테크랑 비슷
public class SWEA_5653_줄기세포배양 {
	private static int N, M, K;
	private static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우 네 방향
	private static int[] dc = {0, 0, -1, 1};
	private static boolean[][] visited;

	// 세포는 수명이 있다
	// 최초 비활성 상태에서 수명만큼 시간이 지나면 활성이 된다
	// 활성이 되는 순간에 상하좌우 빈칸에 세포 번식 (자신과 수명이 같음)
		// 동시번식 하려고 하면 생명력 수치가 높은 줄기 세포가 혼자서 차지 
			// -> 애초에 PQ를 이용해서 큰놈부터 처리하되, 세포가 존재하게 되면 해당 행열 위치에 방문체크를 함으로써 빈곳에만 세포가 생기게 하자
	// 활성상태가 된 이후 수명만큼 시간이 지나면 죽는다
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로크기 (행)
			M = Integer.parseInt(st.nextToken()); // 가로크기 (열)
			K = Integer.parseInt(st.nextToken()); // 배양시간
			// 최대 K/2시간 대기 후 사방으로 K/2시간 동안 번식한다. 그리고 시작좌표는 1,1
			visited = new boolean[N+K+2][N+K+2]; 
			PriorityQueue<Cell> lives = new PriorityQueue<Cell>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int val = Integer.parseInt(st.nextToken());
					if(val > 0) { // 세포가 있다면
						// 좌표를 가운데로 밀기 위해서 K의 반만큼 이동
						Cell cell = new Cell(i + K/2, j + K/2, val);
						visited[cell.r][cell.c] = true;
						lives.add(cell);
					}
				}
			} // 입력처리
			
			for (int k = 0; k < K; k++) { // 배양시간동안 반복
				// 이전 큐는 lives이고 새로 만들어진 신 큐를 만들자
				// 높은 순으로 뽑아서 처리하고 다른 큐에다가 옮겨 담아놓으려고 큐 두개쓰는거!!!!!!
				PriorityQueue<Cell> nextQ = new PriorityQueue<Cell>();
				while(!lives.isEmpty()) { // 구 큐를 다 처리할때까지 반복
					Cell cell = lives.poll(); // 구 PQ에서 하나 꺼내서 
					if(!cell.stat) { // 비활성상태면
						cell.inactive--; // 비활성수명을 1 줄이고
						if(cell.inactive == 0) { // 줄인 비활성수명이 0이되면
							cell.stat = true; // 활성상태로 바꾼다. 
						}
						nextQ.add(cell);
					} else { // 활성상태면
						// 원래는 한번만 번식하는 거지만, 어차피 한번 하고나면 사방에 뭔가 다 생겨서 번식을 못한다
						// 번식 했는지, 안했는지 상태관리해도 되지만.. 그냥 못할 번식하는 걸로
						for (int i = 0; i < 4; i++) {  // 4방에 번식
							int nr = cell.r + dr[i];
							int nc = cell.c + dc[i];
							
							// 이미 세포가 존재한다면, 이전 시간에 생긴 세포가 있거나
							// 이번 시간에 생긴 세포였다면 생명 수치가 높은 세포이니까 먼저 와있을 것.
							if(visited[nr][nc]) continue;
							visited[nr][nc] = true;
							nextQ.add(new Cell(nr, nc, cell.active));
						}
						cell.active--;
						// 여전히 살아있다면 큐에 넣지만, 죽엇다면 큐에 넣지 않고 버리자.
						if(cell.active > 0) {
							nextQ.add(cell);
						}
					}
				} // end of while
				lives = nextQ;
			} // end of K time
			sb.append('#').append(tc).append(' ').append(lives.size()).append('\n');
		} // end of test case
		System.out.println(sb);
	} // end of main
	
	// 세포정보 들어갈 객체 : 행, 열, 비활성수명, 활성수명, 상태 (활성:true, 비활성:false)
	static class Cell implements Comparable<Cell>{
		int r, c, active, inactive;
		boolean stat;
		public Cell(int r, int c, int active) {
			this.r = r;
			this.c = c;
			this.active = this.inactive = active;
			this.stat = false;
		}
		public int compareTo(Cell o) {
			return Integer.compare(o.active, this.active);
		}
	}

} // end of class
