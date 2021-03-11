package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_1655_가운데를말해요 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());

		// 1. 우선순위 큐를 이용해 최대힙, 최소힙을 생성한다.
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 2. 최대힙과 최소힙의 사이즈를 비교하여 두 힙에 번갈아서 값을 추가한다.
			if(maxHeap.size() == minHeap.size()) {
				maxHeap.offer(num);
			} else {
				minHeap.offer(num);
			}

			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				// 3. 최소힙의 최소값 > 최대힙의 최대값 이 유지되는지 확인하고, 어긋나면 swap해준다.
				if(maxHeap.peek() > minHeap.peek()) {
					maxHeap.offer(minHeap.poll());
					minHeap.offer(maxHeap.poll());
				}
			}
			
			sb.append(maxHeap.peek()).append('\n'); // 최대힙의 꼭대기 값을 뽑으면 그 값이 중간값
		}
		
		System.out.println(sb);
	}
}
