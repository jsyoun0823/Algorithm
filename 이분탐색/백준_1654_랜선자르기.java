import java.util.*;
import java.io.*;
public class 백준_1654_랜선자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 랜선의 개수 (1 <= K <= 10,000)
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수 (1 <= N <= 1,000,000)
        // 항상 K <= N

        int[] cables = new int[K]; // 각 랜선의 길이 (1 <= cables[i] <= 2^32 - 1)
        int max = 0;
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(cables[i], max);
        }

        // N개를 만들 수 있는 랜선의 최대 길이를 반환해야 한다.
        // "길이"를 맞춰야 함 길이를 이분탐색으로 찾으면서,
        //  해당 길이를 잘랐을 때 11개 이상 만들 수 있는지 확인..
        // 이때 최초 최대길이는 랜선길이중에 가장 큰놈 (위에 입력받으면서 찾기)
        long min = 1; // 최소 랜선 길이
        long result = 0;

        while (min <= max) {
            long mid = (min + max) / 2;

            // mid 길이로 랜선을 자를 때 만들 수 있는 랜선 개수 계산
            long count = 0;
            for (int cable : cables) {
                count += cable / mid;
            }

            if (count >= N) { // N개 이상 만들 수 있는 경우
                result = mid; // 현재 길이를 저장하고
                min = mid + 1; // 더 긴 길이를 탐색
            } else { // N개를 만들 수 없는 경우
                max = (int) (mid - 1); // 더 짧은 길이를 탐색
            }
        }

        System.out.println(result);
    }
}
