package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_다리를지나는트럭 {

	static int[] bridge;

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };
		int answer = 0;

		Queue<Integer> bridge = new LinkedList<>();
		int cur_weight = 0;

		for (int i : truck_weights) {
			while (true) {
				if (bridge.isEmpty()) { // 큐가 비었으면 진입 가능
					bridge.add(i);
					cur_weight += i;
					answer++;
					break;
				} else if (bridge.size() == bridge_length) { // 큐에 트럭이 모두 차있다면
					cur_weight -= bridge.poll(); // 맨 먼저 들어간 놈 빼준다
				} else {
					if (cur_weight + i <= weight) { // 현재 트럭이 올라가면 무게 초과하는지 체크
						bridge.add(i);
						cur_weight += i;
						answer++;
						break;
					} else {
						bridge.add(0);
						answer++;
					}
				}
			}
		}
		System.out.println(answer+bridge_length);
	}
}
