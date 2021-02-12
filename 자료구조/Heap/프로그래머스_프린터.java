package Heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 프로그래머스_프린터 {

	public static void main(String[] args) {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i : priorities) {
			pq.add(i);
		}

		int cnt = 1;
out:	while (!pq.isEmpty()) {
			for (int i = 0; i < priorities.length; i++) {
				if (priorities[i] == pq.peek()) {
					if (i == location) {
						break out;
					}
					pq.poll();
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
