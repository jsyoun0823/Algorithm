package Heap;

import java.io.*;
import java.util.*;

/**
 * 자료구조, 우선순위 큐 슬라이딩 윈도우
 */
public class 백준_2075_N번째큰수 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());

                if(pq.size() < N) {
                    pq.offer(cur);
                } else {
                    if(pq.peek() < cur) {
                        pq.poll();
                        pq.offer(cur);
                    }
                }
            }
        }
        System.out.println(pq.poll());
    }

}

