import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {

	static int M, aCnt; // 시간, 충전소 개수
	static int[] pathA, pathB, playerA, playerB;
	static int[][] ap;
	
	// 0:이동하지 않음, 1:상, 2:우, 3:하, 4:좌
	static int[] dx = {0, 0, 1, 0,-1};
	static int[] dy = {0,-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		playerA = new int[2];
		playerB = new int[2];
		
		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());
			aCnt = Integer.parseInt(st.nextToken());
			
			// 두 플레이어의 초기위치
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;
			
			pathA = new int[M+1]; // 0초 즉, 처음 출발위치에서도 처리하도록 M+1
			pathB = new int[M+1];
			ap = new int[aCnt][4]; // x, y, 충전가능거리, 충전량
			
			String charsA = br.readLine();
			String charsB = br.readLine();
			
			for (int c = 0; c < M; c++) {
				pathA[c+1] = charsA.charAt(c*2)-'0';
				pathB[c+1] = charsB.charAt(c*2)-'0';
			}
			
			for (int a = 0; a < aCnt; a++) {
				st = new StringTokenizer(br.readLine(), " ");
				ap[a][0] = Integer.parseInt(st.nextToken()); // x
				ap[a][1] = Integer.parseInt(st.nextToken()); // y
				ap[a][2] = Integer.parseInt(st.nextToken()); // c
				ap[a][3] = Integer.parseInt(st.nextToken()); // p
			}
			
			System.out.println("#" + t + " " + move());
		} // end of for test case
	} // end of main

	/** 매 시간마다 두 플레이어의 충전량의 합의 최대값을 구하고 그 값을 모든 시간동안 누적 */
	public static int move() {
		int totalSum = 0;
		int time = 0;
		while(time <= M) {
			// 두 플레이어를 해당 시간의 이동 정보에 맞게 이동
			playerA[0] += dx[pathA[time]];
			playerA[1] += dy[pathA[time]];
			playerB[0] += dx[pathB[time]];
			playerB[1] += dy[pathB[time]];
			
			totalSum += getcharge();
			++time;
		}
		
		return totalSum;
	}

	// 중복순열
	private static int getcharge() {
		int max = 0;
		
		for (int a = 0; a < aCnt; a++) { // 플레이어 A의 충전소
			for (int b = 0; b < aCnt; b++) { // 플레이어 B의 충전소
				int sum = 0;
				int amountA = check(a, playerA[0], playerA[1]);
				int amountB = check(b, playerB[0], playerB[1]);
				if (a != b) sum = amountA + amountB;  // 충전소 같지 않으면 더한다
				else sum = Math.max(amountA, amountB);
				
				if(max < sum) max = sum;
			}
		}
		return max;
	}
	
	// x,y 좌표에서 a 충전소에 충전이 가능한지 판단하여 충전가능하다면 충전량 리턴, 아니면 0 리턴
	private static int check(int a, int x, int y) {
		return Math.abs(ap[a][0] - x) + Math.abs(ap[a][1] - y) <= ap[a][2] ? ap[a][3] : 0;
	}
} // end of class
