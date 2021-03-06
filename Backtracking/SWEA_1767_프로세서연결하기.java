import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {
	public static int map[][], N, max, min, totalCnt; // 맥시노스판, 판크기, 최대코어수, 최소전선길이, 처리할 코어수
	public static ArrayList<int[]> list;
	public static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	public static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>(); // 처리해야할 가장자리가 아닌 코어들을 저장할 리스트
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
		
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if((i==0 || j==0 || i==N-1 || j==N-1) && map[i][j] == 1) continue; // 가장 자리에 있는 코어는 리스트에 추가하지 않음
					// 가장자리에 있지 않은 코어 리스트에 추가
					if(map[i][j] == 1) {
						list.add(new int[] {i, j});
						totalCnt++;
					}
				}
			} // end of input
			go(0, 0);
			System.out.println("#" + t + " " + min);
		} // end of test case
	} // end of main
	
	// index : 처리할 코어의 index, cCnt : 직전까지 포함된 코어수
	public static void go(int index, int cCnt) {
		if(index == totalCnt) { // 기저조건, 코어를 전부 다 처리했으면
			int res = getLength(); // 놓아진 전선의 길이를 계산
			if(max < cCnt) {
				max = cCnt;
				min = res;
			} else if(max == cCnt) { // 최대 코어개수가 같다면 최소길이의 전선으로.
				min = (min > res)? res : min;
			}
			return;
		}
		
		int[] cur = list.get(index); // 해당 코어 선택
		int r = cur[0];
		int c = cur[1];
		// 4방향의 직선으로 전선 놓아보는 시도
		for (int d = 0; d < 4; d++) {
			if(isAvailable(r, c, d)) { // 해당 방향으로 전선 놓는게 가능한지 체크
				setStatus(r, c, d, 2); // 가능하다면 전선 놓기 : 맥시노스 판에 2로 세팅
				go(index + 1, cCnt + 1); // 다음 코어로 넘어가기
				setStatus(r, c, d, 0); // 놓았던 전선 지우기(되돌리기) : 다시 0으로 세팅
			}
		}
		// 해당 코어 비선택
			// 아무런 전선도 놓지 않고 다음 코어로 넘어가기
		go(index + 1, cCnt);
	}
	
	// 현 코어 위치에서 해당 방향으로 전선을 놓을 수 있는지 체크 
	public static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break; // 가장자리까지 다 전선을 놓을 수 있는 상황
			if(map[nr][nc] >= 1) return false; // 1: 코어, 2: 전선
		}
		return true;
	}
	
	// 현코어의 위치에서 해당 방향으로 전선을 놓거나(s=2), 지우는(s=0) 셋팅
	public static void setStatus(int r, int c, int d, int s) {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
		}
	}
	
	public static int getLength() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 2) ++cnt;
			}
		}
		return cnt;
	}
} // end of class
