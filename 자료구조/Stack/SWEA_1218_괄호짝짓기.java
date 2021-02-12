package Stack;

import java.util.Scanner;
import java.util.Stack;

/** Stack 이용 */
public class SWEA_1218_괄호짝짓기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        for(int test_case = 1; test_case <=10; test_case++) {
            int result = -1;
            int T = sc.nextInt();
 
            String str = sc.next();
            Stack s = new Stack();
            
            for(int i = 0; i < T; i++) {
                char c = str.charAt(i);
                if(c == '(' || c == '{' || c == '[' || c == '<' ) s.push(c);
                else if(c == ')' || c == '}' || c == ']' || c == '>') {
                    char temp = (char) s.pop();
                    if ( (c == ')' && temp =='(' ) 
                        || (c == '}' && temp =='{' )
                        || (c == ']' && temp =='[')
                        || (c == '>' && temp =='<')) {
                        continue;
                    }else {
                        result = 0;
                    }
                }
                if(result == 0 ) break;
            }
            if(s.size() != 0) {
                result = 0;
            } else {
                result = 1;
            }
            System.out.println("#" + test_case + " " + result);
        } // end of test case
	} // end of main
} // end of class