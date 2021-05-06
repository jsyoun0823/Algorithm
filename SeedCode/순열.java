// 순열 : 순서 유의미  Perm
// {1, 2} {1, 3} {2, 1}, {2, 3} {3, 1}, {3, 2} 6가지

import java.util.Arrays;
import java.util.Scanner;

public class 순열 {

	private static int N, R;
	private static int[] numbers, input; // 순열 저장 배열, 입력된 숫자 배열
	private static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		
		numbers = new int[R];
		input = new int[N];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		perm(0);
	}
	
	/** 지정된 자리에 순열 수 뽑기 */
	private static void perm(int cnt) {
		// cnt : 현재까지 뽑은 순열수의 갯수
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < N; i++) {
			if(isSelected[i]) continue; // 이미 사용한 거면 패스
			isSelected[i] = true;
			numbers[cnt] = input[i];
			perm(cnt + 1);
			isSelected[i] = false; // 재귀에서 돌아오면 사용여부 해제
		}
	
	}

}
