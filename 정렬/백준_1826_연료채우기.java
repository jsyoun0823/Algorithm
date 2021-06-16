import java.util.*;

public class 백준_1826_연료채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 주유소 개수
        int[][] stations = new int[N][2];
        for (int i = 0; i < N; i++) {
            stations[i][0] = sc.nextInt(); // 거리
            stations[i][1] = sc.nextInt(); // 연료
        }

        Arrays.sort(stations, Comparator.comparingInt(o1 -> o1[0]));
        int L = sc.nextInt(); // 마을까지 거리
        int P = sc.nextInt(); // 원래 있던 연료 양

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int curIdx = 0, cnt = 0;
        boolean flag = false;

        while (P < L) { // 현재 연료로 목적지 갈 수 있는지 체크
            // 현재 위치에서 현재 연료로 갈 수 있는 주유소의 연료 pq에 넣기
            while (curIdx < N && stations[curIdx][0] <= P) {
                pq.add(stations[curIdx][1]);
                curIdx++;
            }

            if(pq.isEmpty()) { // 저장된 주유소 연료값 없으면
                flag = true; // 목적지 도착 못했으므로 탈출
                break;
            }

            P += pq.poll(); // 연료 채우기
            cnt++; // 멈춘 횟수 증가
        }

        if(flag) System.out.println(-1);
        else System.out.println(cnt);
    }
}
