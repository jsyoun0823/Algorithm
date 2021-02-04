
import java.util.Scanner;

/** 
 * LIS(Lonest Increasing Subsequence) 최장 증가 수열
 * 배열 순서 유지하면서, 크기가 점진적으로 커지는 가장 긴 부분수열 길이
 * 
 * LIS(i) = 1 + max LIS(j)    if(j<i and a[i]<a[j])
 * 	i원소까지 고려한 최장 길이
 * => i원소를 끝으로 하는 LIS 최장길이
 * 
 * */
public class LIS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N]; // 자신을 끝으로 하는 LIS 최장길이
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS[i] = 1; // 자신만으로 LIS 구성했을 때의 길이 1
			
			// 자신(i)의 앞에 있는 원소들과 비교
			for (int j = 0; j < i; j++) {
				// 앞쪽 원소보다 자신이 큰 경우
				if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			
			// 현 원소의 LIS 값과 전체 최대값과 비교해 최대값 갱신
			if (max < LIS[i]) max = LIS[i];
		}
		
		System.out.println(max);
	}
}