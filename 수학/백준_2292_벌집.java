import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2292_벌집 {
    static int start; // 계산 시작 값, 2부터 시작
    static int num; // 입력값

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine()); // num : 입력값 저장, N(1 ≤ N ≤ 1,000,000,000)
        int ans = 0; // 이동해야하는 최소 칸 수
        if(num == 1) ans = 1; // 1이면 자기 자신 한칸
        else if(num <= 7) ans = 2; // 2~7까지는 2칸
        else { // 8 이상부터는 이동 칸 수 계산
            start = 2; // 2부터 시작
            ans = go() + 1; // 자기 자신 포함이므로 +1 해서 결과값 저장
        }
        System.out.println(ans);
    }

    public static int go() { // 이동칸 수 계산하는 메소드
        int cnt = 1; // 몇번째 육각형 내인지 카운트
        int cnt2 = 2; // 한 육각형 띠의 개수를 세기위한 카운트
        for (int j = 0; j < 1_000_000_000; j++) { // N의 최댓값만큼 설정
            start += 6*cnt; // 시작 지점에 6*몇번째 육각형 만큼 곱해서 더한다 (2->8>20->...)
            for (int i = start; i < start + (cnt2 * 6); i++) { // 시작점부터, 각 육각형 띠 개수만큼
                if(i == num) { // num을 찾으면
                    return cnt2; // 몇칸 왔는지 (자기자신 제외) 반환
                }
            }
            cnt++; // 다음을 위해 +1
            cnt2++; // 다음을 위해 +1
        }
        return -1; // 범위 벗어나서 다 해도 못찾으면 -1 반환
    }
}
