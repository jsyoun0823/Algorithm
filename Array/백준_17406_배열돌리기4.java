import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 백준_17406_배열돌리기4
 * 
 * 회전 작업의 순열, 배열을 복사, 중심좌표, 반경 회전 swap
 * 배열의 각 행의 합, 합 중 최소값
 * */
public class 백준_17406_배열돌리기4 {
	private static int N, M, K;
	private static int[][] A, Acopy; // 원본, 사본
	private static int min = Integer.MAX_VALUE; // 최소값
	private static int[][] cycle;
	private static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 배열 A의 크기, (행) 3 ≤ N, M ≤ 50
		M = Integer.parseInt(st.nextToken()); // 배열 A의 크기, (열)
		K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수, 1 ≤ K ≤ 6
		
		A = new int[N+1][M+1]; // 0번째 행열은 안씀
		Acopy = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()); // 1 ≤ A[i][j] ≤ 100
			}
		}
		
		cycle = new int[K][3]; // 회전 연산 r, c, s
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cycle[i][0] = Integer.parseInt(st.nextToken()); // r
			cycle[i][1] = Integer.parseInt(st.nextToken()); // c
			cycle[i][2] = Integer.parseInt(st.nextToken()); // s
		}
		
		arr = new int[K]; // 회전 연산 순열을 저장할 배열
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		perm(0);
		System.out.println(min);
	} // end of main

	/** step 단계, step == K 까지 진행 */
	public static void perm(int step) {
		if(step == K) {  //	회전 작업의 순열
			for (int i = 0; i < A.length; i++) { // 배열을 복사 회전 작업시 원본 이 손상되므로 하나 복사
				Acopy[i] = A[i].clone();
			}
			for (int i = 0; i < arr.length; i++) {
				cycleA(cycle[arr[i]]); // 중심좌표, 반경 회전 swap
			}
//			배열의 각 행의 합, 합 중 최소값
			for (int i = 1; i < A.length; i++) {
				int sum = 0;
				for (int j = 1; j < A[i].length; j++) {
					sum += Acopy[i][j];
				}
				if (min > sum) min = sum;
			}
		} else {
//			step 위치에 올 사용하지 않은 숫자를 넣어보자
			for (int i = step; i < K; i++) {
//				step <-> i
				int temp = arr[step];
				arr[step] = arr[i];
				arr[i] = temp;
				perm(step + 1);
//				step <-> i   // 원복
				temp = arr[step];
				arr[step] = arr[i];
				arr[i] = temp;
			}
		}
	}

	public static void cycleA(int[] cycle) { // r, c, s
		for (int s = 1; s < cycle[2]; s++) {
			int size = s * 2 + 1; // 한 변의 크기 : 한 행의 움직일 크기
			int r = cycle[0] - s; // 좌측 상단부터 시작
			int c = cycle[1] - s; // 
			int temp = Acopy[r][c]; // 백업
			for (int i = 1; i < size; i++) { // 하
				Acopy[r][c] = Acopy[r+1][c];
				r++;
			}
			for (int i = 1; i < size; i++) { // 우
				Acopy[r][c] = Acopy[r][c+1];
				c++;
			}
			for (int i = 1; i < size; i++) { // 상
				Acopy[r][c] = Acopy[r-1][c];
				r--;
			}
			for (int i = 1; i < size; i++) { // 좌
				Acopy[r][c] = Acopy[r][c-1];
				c--;
			}
			Acopy[r][c+1] = temp;
		}
	}
}  // end of class
