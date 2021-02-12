package Heap;

import java.util.PriorityQueue;

public class 프로그래머스_더맵게 {
	
	public static PriorityQueue<Integer> pq;
	public static void main(String[] args) {
        int answer = 0;
        int[] scoville = {1,2,3,9,10,12};
        int K = 7;
        
        pq = new PriorityQueue<>();
        for(int x : scoville) {
            pq.add(x);
        }
        
        while(pq.peek() < K) {
        	if(pq.size() == 1) {
        		answer = -1;
        		break;
        	}
        	
            int min = pq.poll();
            int min2 = pq.poll();
            pq.add((min + (min2 * 2)));
            answer++;
        }
        
        System.out.println(answer);
	}
}
