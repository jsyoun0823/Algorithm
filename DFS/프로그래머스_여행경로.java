import java.util.ArrayList;
import java.util.Collections;

public class 프로그래머스_여행경로 {

	static int N;
	static boolean[] used;
	static String[][] tickets;
	static String route = "";
	static ArrayList<String> list;
	
	public static void main(String[] args) {
		tickets = new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		N = tickets.length;
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			used = new boolean[N];
			String s = tickets[i][0];
			String e = tickets[i][1];
			
			if(s.equals("ICN")) {
				route = s + " ";
				used[i] = true;
				dfs(1, e);
				used[i] = false;
			}
		}
		Collections.sort(list);
		System.out.println(list);
//		String[] answer = list.get(0).split(" ");
	}
	
	public static void dfs(int cnt, String end) {
		route += end + " ";
		
		if(cnt == N) {	// 만일 티켓을 모두 사용했으면
			list.add(route);
			return; // 현재까지 방문한 곳들 리턴
		}
		
		// 아직 남은 티켓이 있다면
		for (int i = 0; i < N; i++) {
			String s = tickets[i][0]; // 출발지
			String e = tickets[i][1]; // 도착지
			
			if(s.equals(end) // 이전 도착지 == 지금 출발지 
					&& !used[i]) {// 사용하지 않은 티켓이라면
				used[i] = true;	// 사용처리
				dfs(cnt+1, e);	// 다음 티켓 ㄱㄱ
				used[i] = false;	// 사용한거 해제
				route = route.substring(0, route.length()-4);
			}
		}
	}
}
