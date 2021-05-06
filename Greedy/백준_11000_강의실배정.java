import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

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

        // 가장 일찍 끝나는 강의 선택
        // 강의 종료시간이 같다면, 강의 시작시간을 기준으로 오름차순 정렬 해야함
        Arrays.sort(arr, (o1, o2) -> {
            if (o2[1] == o1[1]) { // 종료시간 같으면
                return o1[0] - o2[0]; // 강의 시작시간 기준으로 오름차순 정렬
            } else
                return o1[1] - o2[1]; // 일찍 끝나는 강의 순

        });

        System.out.println(getCnt());
    }

    public static int getCnt() {
        int now = 0;
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            // 다음 강의 시작시간이 현재 강의 종료시간보다 작으면 넘어감
            if(arr[i][0] < arr[now][1]) continue;

            cnt++;
            now = i;
        }

        return cnt;
    }
}
