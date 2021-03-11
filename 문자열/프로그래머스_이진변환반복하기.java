import java.util.Arrays;

public class 프로그래머스_이진변환반복하기 {
    public static void main(String[] args) {
        String s = "110010101001";

        int[] answer = {0, 0}; // 이진 변환의 횟수, 변환 과정에서 제거된 모든 0의 개수

        while(!s.equals("1")) { // s가 "1"이 될 때까지 반복
            String str = ""; // 임시 문자열
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '1') str += '1'; // 1이면 임시 문자열에 더한다
                else if(s.charAt(i) == '0') answer[1]++; // 제거할 0의 개수 +1
            }
            s = Integer.toBinaryString(str.length()); // 0을 제거한 문자열의 길이를 2진법으로 표현한 문자열로 변환
            answer[0]++; // 이진 변환 횟수 +1
        }

        System.out.println(Arrays.toString(answer));
    }
}
