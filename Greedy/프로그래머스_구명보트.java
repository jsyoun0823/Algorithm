import java.util.Arrays;

public class 프로그래머스_구명보트 {

	public static void main(String[] args) {
		
		int[] people = {70, 80, 50};
		int limit = 130;

		Arrays.sort(people);
		boolean[] visited = new boolean[people.length];
		boolean flag = false;
		int cnt = people.length;
		int end = people.length - 1;
		for (int j = 0; j < people.length; j++) {
			if(j >= end) break; 
			for (int i = end; i > j; i--) {
				if(visited[j]) break;
				if(visited[i]) continue;
				if(people[j] + people[i] <= limit) {
					visited[j] = visited[i] = true;
					cnt--;
					end = i;
					flag = true;
				}
			}
			if(!flag) break;
		}
		System.out.println(cnt);
	}

}
