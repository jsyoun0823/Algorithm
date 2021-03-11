import java.util.*;

public class 프로그래머스_가장큰수 {
    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        String answer = "";

        int len = numbers.length;
        String[] strs = new String[len];
        for(int i=0; i<len; i++) {
            strs[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(strs, Collections.reverseOrder());

//        if(strs[0].equals("0")) return "0";

        for(String s : strs) {
            answer += s;
        }

        System.out.println(answer);
    }
}
