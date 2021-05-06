import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_11000_강의실배정 {
    private static int N, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 강의 수
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작 시간으로 정렬, 같으면 종료시간으로 정렬
        Arrays.sort(arr, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {arr[0][0], arr[0][1]});

        for (int i = 1; i < N; i++) {
            int[] cur = pq.peek();

            if(arr[i][0] >= cur[1]) {
                pq.poll();
            }
            pq.offer(arr[i]);
        }

        System.out.println(pq.size());
    }
}
