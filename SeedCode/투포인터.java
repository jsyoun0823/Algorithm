import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2003. 수들의 합2

public class 투포인터 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int sum = arr[i];
			if(sum == M) { // 자기 자신이 M이면
				cnt++;
				continue;
			}
			
			for (int j = i + 1; j < N; j++) {
				sum += arr[j];
				if(sum == M) { // 합이 M과 같다면
					cnt++;
					break;
				} else if (sum > M) { // M보다 크면 j가 늘어나도 계속 크니까 i를 옮겨서 다시 시작
					break;
				}
				// M보다 작으면 j 늘리기
			}
		}
		System.out.println(cnt);
	}
}
