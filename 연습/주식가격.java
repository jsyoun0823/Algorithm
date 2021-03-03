import java.util.Arrays;
import java.util.Stack;

public class 주식가격 {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();

        int[] prices = {1, 2, 3, 2, 3};
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len - 1; j++) {
                if(prices[i] > prices[j]) break;
                answer[i]++;
            }
        }
        System.out.println(Arrays.toString(answer));

    }
}
