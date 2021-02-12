import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1920. 수 찾기 분류 : 이분 탐색
 * 2136ms
 */
public class 백준_1920_수찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int cur = Integer.parseInt(st.nextToken());

			int mid = 0;
			int left = 0;
			int right = N - 1;
			boolean flag = false;
			
			while(right >= left) {
				mid = (right + left) / 2;
				if(cur == A[mid]) {
					flag = true;
					break;
				} else if(cur < A[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			if(flag) System.out.println(1);
			else System.out.println(0);
		}

	}

}
