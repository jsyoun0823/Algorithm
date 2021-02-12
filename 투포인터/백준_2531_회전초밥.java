import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2531 회전 초밥
 * 
 * 초밥 N개 중 연속된 k개 선택 추가 쿠폰 c번 초밥 무료 가능한 한 다양한 종류의 초밥
 *  -> 손님이 먹을 수 있는 초밥 가짓수의 최대값
 *  
 *  슬라이딩 윈도우 알고리즘 
 *   + 현재 먹은 초밥에 쿠폰번호와 같은 초밥 포함이면 그대로 두고, 
 *   	그렇지 않으면 +1
 */
public class 백준_2531_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시 수, 2≤N≤3,000,000
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수, 2≤d≤3,000
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수, 2≤k≤3,000 (k≤N)
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호, 1≤c≤d
		
		int[] s = new int[N + k]; // 원형 형태로 되어있으므로 뒤에 k개 만큼 더 추가
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(br.readLine());
		}
		for (int i = N; i < N + k; i++) {
			s[i] = s[i - N];
		}
		
		Queue<Integer> queue = new LinkedList<>(); // 현재 k개만큼의 연속된 초밥이 들어갈 큐
		Map<Integer, Integer> map = new HashMap<>(); // 초밥의 가짓수가 지금 선택된 배열에 몇개 들어있는지, 7->2
		int max = 0;

		for (int i = 0; i < N + k; i++) {
			if (queue.size() == k) { // 큐의 사이즈가 k개가 되면 (k개 선택되어있는 상태면)
				int remove = queue.poll(); // 하나를 꺼낸다

				map.put(remove, map.get(remove) - 1);
				if (map.get(remove) <= 0) // 0개보다 적어지면
					map.remove(remove); // 제거
			}

			queue.add(s[i]);
			map.put(s[i], map.getOrDefault(s[i], 0) + 1);

			if (map.containsKey(c)) // 현재 먹은 초밥에 쿠폰번호와 같은 초밥 포함하는지 체크
				max = Math.max(max, map.size());
			else
				max = Math.max(max, map.size() + 1);
		}

		System.out.println(max);
	} // end of main
} // end of class
