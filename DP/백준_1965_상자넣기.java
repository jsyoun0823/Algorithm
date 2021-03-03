import java.util.Scanner;

/*
   LIS(i) = 1 + max LIS(j) if(j<i and a[i]<a[j])
   i 원소까지 고려한 최장 길이 == i원소를 끝으로 하는 LIS 최장길이
* */
public class 백준_1965_상자넣기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] box = new int[n]; // 박스 크기 배열
        int[] lis = new int[n]; // 자신을 끝으로 하는 LIS 최장길이
        for (int i = 0; i < n; i++) {
            box[i] = sc.nextInt();
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            lis[i] = 1; // 자신만으로 LIS 구성했을 때의 길이 1

            // 자신(i)의 앞에 있는 원소들과 비교
            for (int j = 0; j < i; j++) {
                // 앞쪽 원소보다 자신이 큰 경우 && 현재 자신까지 최장길이보다 j 까지 최장길이 + 1가 더 큰 경우
                if(box[j] < box[i] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }

            max = Math.max(max, lis[i]);
        }

        System.out.println(max);

    }
}
