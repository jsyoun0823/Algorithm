package Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_기능개발 {

	public static void main(String[] args) {
		int[] progresses = { 5, 5, 5 };
		int[] speeds = { 21, 25, 20};
		ArrayList<Integer> answer = new ArrayList<>();
		int size = progresses.length;
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < size; i++) {
			q.add(new int[] { progresses[i], speeds[i] });
		}

		while (!q.isEmpty()) {
			if (q.peek()[0] >= 100) {
				int cnt = 0;
				while (!q.isEmpty() && q.peek()[0] >= 100) {
					q.poll();
//					System.out.println(q.poll());
					System.out.println(q.peek());
					cnt++;
				}
				System.out.println(cnt);
				answer.add(cnt);
			} else {
//				int tempSize = q.size();
				for (int i = 0; i < q.size(); i++) {
					int[] temp = q.poll();
					temp[0] += temp[1];
					q.add(temp);
				}
			}

		}

		System.out.println(answer);

	}

}
