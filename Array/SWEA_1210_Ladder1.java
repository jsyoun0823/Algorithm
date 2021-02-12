import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 번호
			int[][] ladder = new int[100][100]; // 사다리 배열
			
			for (int i = 0; i < ladder.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < ladder.length; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 'X' 지점으로부터 타고 올라간다. 맨 끝행에서 시작
			int row = 99;
			int col = -1; // 쓰레기 값으로 초기화
			for (int i = 0; i < ladder.length; i++) {
				if (ladder[99][i] == 2) {
					col = i;	// 'X' 지점의 열을 저장
					break;
				}
			}
			
			int dir = 1;	// 방향 상:1, 좌:2, 우:3
			while(true) {
				if (row == 0) break; // 0행에 도착하면 종료
				if(dir != 3 && col-1 >= 0 && ladder[row][col-1] == 1) { // 왼쪽에 길이 있고 현재 방향이 오른쪽이 아니면
					col--;  // 왼쪽으로 이동
					dir = 2; // 방향 왼쪽
				} else if (dir != 2 &&col+1 < 100 && ladder[row][col+1] == 1) { // 오른쪽에 길이 있고 방향이 왼쪽이 아니면
					col++; // 오른쪽으로 이동
					dir = 3;
				} else {
					row--; // 한칸 위로 이동
					dir = 1;
				}
			}
			System.out.println("#" + tc + " " + col);
		} // end of for testCase
	} // end of main
} // end of class
