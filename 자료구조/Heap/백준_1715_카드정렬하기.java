package Heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 백준_1715_카드정렬하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if(N == 1) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            pq.add(sc.nextInt());
        }

        int sum = 0;
        while(!pq.isEmpty()) {
            int a = pq.poll();

            if(pq.isEmpty()) {
                sum += a;
                break;
            }

            int b = pq.poll();

            sum += (a + b);

            if(!pq.isEmpty()) pq.add(a + b);
        }

        System.out.println(sum);
    }
}
