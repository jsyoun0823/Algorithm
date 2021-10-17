
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 가장 먼저 끝나는 회의를 선택하고, 이 회의와 겹치지 않는 회의 중 가장 먼저 끝나는 회의를 선택 
 * - 가장 일찍 끝나는 회의 선택 
 * - 정렬 과정에서 회의 종료시간이 같다면, 회의 시작시간을 기준으로 한 오름차순 정렬 해야함
 * 
 */
public class 백준_1931_회의실배정 {

	static int N;
	static int[][] arr; // 회의 시작시간, 끝나는 시간 저장할 배열

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine()); // 회의의 수 N(1 ≤ N ≤ 100,000)
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, (o1, o2) -> {
			if (o2[1] == o1[1]) { // 종료시간 같으면
				return o1[0] - o2[0]; // 회의 시작시간 기준으로 오름차순 정렬
			} else
				return o1[1] - o2[1]; // 회의 종료시간 일찍 끝나게

		});
		System.out.println(solve()); // 최대 사용할 수 있는 회의의 최대 개수
	}

	static int solve() {
		int now = 0;
		int cnt = 1;

		for (int i = 1; i < N; i++) {
			// 다음 회의의 시작시간이 현재 회의의 종료시간보다 작으면 continue
			if (arr[i][0] < arr[now][1])
				continue;
			cnt++;
			now = i;
		}
		return cnt;
	}// end of solve

}
