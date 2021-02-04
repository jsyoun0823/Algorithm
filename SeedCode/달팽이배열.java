import java.util.Scanner;

/*
 절차
  -> 오른쪽으로++;  1행 n칸 우
  1 2 3  아래로 ++;  n열 n-1칸 하
  8 9 4
  7 6 5
  왼쪽으로++; 방향바꾸기 
  
 */
public class 달팽이배열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = sc.nextInt();
			int[][] snail = new int[N][N];
			
			int row = 0, col = -1, dir = 1;	// 행, 열, 방향
			int num = 1;	// 채울 숫자
			
			while (true) {
				for (int j = 0; j < N; j++) {	 // 행 채우기
					col = col + dir;
					snail[row][col] = num;
					num++;
				}
				if (N == 0) break;
				N--;	//  5-> 4-> 3-> 채워야하는 칸 수 줄이기
				for (int j = 0; j < N; j++) { // 열 채우기
					row = row + dir;
					snail[row][col] = num;
					num++;
				}
				dir = dir * (-1); // 방향 바꾸기
			}
			
			// 달팽이 배열 출력
			System.out.println("#" + testCase);
			for (int i = 0; i < snail.length; i++) {
				for (int j = 0; j < snail.length; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		} // end of testCase
	} // end of main
} // end of class
