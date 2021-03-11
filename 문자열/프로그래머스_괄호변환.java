import java.util.*;

class 프로그래머스_괄호변환 {
    public static void main(String[] args) {
        String p = "";
        System.out.println(correct(p));
    }

    public static String correct(String str) {
        if(str.length() == 0 || isCorrect(str)) return str;

        String u = "";
        String v = "";
        for(int j = 2; j <= str.length(); j+=2) {
            if(isBalance(str.substring(0, j)) && str.length() != 0) {
                u = str.substring(0, j);
                
                if(u.equals(str)) v = "";
                else v = str.substring(j);
                
                break;
            }
        }
 
       if(isCorrect(u)) {
            u += correct(v);
            return u;
           
        } else {
            String temp = "(";
            temp += correct(v);
            temp += ")";
           
            u = u.substring(1, u.length()-1);
           
            char[] ch = u.toCharArray();
            for(char cur : ch) {
                if(cur == '(') temp += ')';
                else if(cur == ')') temp += '(';
            }
            return temp;
        }
    }
    
    public static boolean isBalance(String s) {
        int cnt = 0;
        char[] crr = s.toCharArray();
        for(char c : crr) {
            if(c == '(') cnt++;
            else cnt--;
        }
        return cnt == 0;
    }
    
    public static boolean isCorrect(String str) {
        Stack<Character> s = new Stack<>();
        char[] crr = str.toCharArray();
        for(char c : crr) {
            if(c == '(') s.push(c);
            else if(c == ')') {
                if(s.isEmpty()) return false;
                s.pop();
            }
        }
        return s.size() == 0;
    }
}
