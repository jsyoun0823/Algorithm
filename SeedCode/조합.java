//조합 : 순서 무의미 Comb {1, 2} {1, 3} {2, 3}

import java.util.Arrays;
import java.util.Scanner;

/** 입력받은 N개의 숫자 중 R개를 선택하는 조합 */
public class 조합 { 
	private static int N, R;
	private static int[] numbers, input; // 조합 저장 배열, 입력된 숫자 배열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		
		numbers = new int[R];
		input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		combination(0, 0);
	}
	
	/** 지정된 자리에 조합 수 뽑기 */
	private static void combination(int cnt, int start) {
		// cnt : 현재까지 뽑은 조합수의 갯수
		// start : 조합에 시작점으로 시도할 원소 인덱스
		
		if(cnt == R){
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		// 현자리에 시작 위치 수부터 끝위치 수까지 시도
		for (int i = start; i < N; i++) {
			numbers[cnt] = input[i];	
			combination(cnt+1, i+1);
		}
	}
}
