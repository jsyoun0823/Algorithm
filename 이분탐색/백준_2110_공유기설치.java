import java.io.*;
import java.util.*;

public class 백준_2110_공유기설치 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();

        int[] point = new int[N];
        for (int i = 0; i < N; i++) {
            point[i] = sc.nextInt();
        }
        Arrays.sort(point);

        int result = 0;
        int start = 1; // 최소 거리
        int end = point[N-1] - point[0]; // 최대 거리(마지막 집 - 첫번째 집 거리)

        while(start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;
            int prev = point[0];
            for (int i = 1; i < N; i++) { // 각 집사이의 거리가 현재 설정한 거리보다 큰지 체크
                if(point[i] - prev >= mid){
                    cnt++;
                    prev = point[i];
                }
            }

            if(cnt >= C) { // 공유기를 제한 갯수 이내로 사용했을 경우 공유기 수를 줄여야 하므로 간격 넓힘
                result = Math.max(result, mid); // 최대값 갱신
                start = mid + 1; // mid 오른쪽 영역으로 옮긴다
            } else { // 공유기를 제한 갯수보다 더 사용했으면 공유기 수를 늘려야 하므로 간격 좁힘
                end = mid - 1; // mid 왼쪽 영역으로 옮긴다
            }
        }
        System.out.println(result);
    }
}
