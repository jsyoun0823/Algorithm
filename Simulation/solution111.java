// You may use import as below.
//import java.util.*;

public class solution111 {
    public static long solution(long num) {
        num += 1;
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '0') chars[i] = '1';
        }
        return Long.parseLong(String.valueOf(chars));
    }

    // The following is main method to output testcase.
    public static void main(String[] args) {
        long num = 9949999;
        long ret = solution(num);

        // Press Run button to receive output. 
        System.out.println("Solution: return value of the method is " + ret + " .");
    }
}