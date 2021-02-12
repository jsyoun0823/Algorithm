import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14889_스타트와링크 {
	static int[][] S;
	static int N, min;
	static boolean[] select;
	static int[] start, link;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 사람 수 (짝수)
		S = new int[N][N]; // 팀에 더해지는 능력치 
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		select = new boolean[N]; // 팀을 나누기 위해 뽑은 사람을 체크할 배열
		start = new int[N/2]; // 스타트팀 
		link = new int[N/2]; // 링크팀
		min = Integer.MAX_VALUE; // 스타트 팀과 링크 팀의 능력치의 차이의 최솟값
		comb(0, 0);
		System.out.println(min);
	} // end of main

	/** N명 중 스타트팀, 링크팀에 각각 들어갈 N/2명을 뽑는 조합 */
	public static void comb(int cnt, int idx) {
		if (cnt == (N/2)) { // N/2명을 다 뽑으면
			int sCnt = 0, lCnt = 0;
			for (int i = 0; i < N; i++) {
				if(select[i]) start[sCnt++] = i; // 선택된 사람들은 스타트팀에 넣고
				else link[lCnt++] = i; // 나머지 사람들은 링크팀에 넣기
			}
			int dif = Math.abs(cal(start) - cal(link)); // 두 팀의 능력치 합의 차이 구하기
			min = (min > dif) ? dif : min; // 최소값 갱신
			return;
		} 
		
		for (int i = idx; i < N; i++) {
			if(select[i]) continue;
			select[i] = true;
			comb(cnt + 1, i);
			select[i] = false;
		}
	}

	/** 한 팀의 능력치의 합을 구하는 메소드 */
	public static int cal(int[] arr) {
		int sum = 0, x = 0, y = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				x = arr[i]; y = arr[j];
				sum += S[x][y] + S[y][x]; // Sij + Sji
			}
		}
		return sum;
	}
} // end of class
