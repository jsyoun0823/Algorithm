import java.util.*;
import java.io.*;

public class 백준_11663_선분위의점 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] points = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(points);

        // 각 선분에 대해 점 개수 계산
        for (int i = 0; i < m; i++) {
            String[] segment = br.readLine().split(" ");
            int start = Integer.parseInt(segment[0]);
            int end = Integer.parseInt(segment[1]);

            int leftIdx = lowerBound(points, start);
            int rightIdx = upperBound(points, end);

            sb.append(rightIdx - leftIdx).append("\n");
        }

        System.out.print(sb);
    }

    // target 이상의 첫 위치를 반환
    private static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // target 이하의 마지막 위치 + 1 반환
    private static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
