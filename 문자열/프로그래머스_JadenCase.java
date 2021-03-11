import java.util.*;

public class 프로그래머스_JadenCase {
    public static void main(String[] args) {
        String s = "3aaaaa    aaa";
        StringBuilder sb = new StringBuilder();

        sb.append(Character.toUpperCase(s.charAt(0)));
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') sb.append(' ');
            else if (Character.isDigit(c)) sb.append(c);
            else if (s.charAt(i - 1) == ' ') sb.append(Character.toUpperCase(c));
            else sb.append(Character.toLowerCase(c));
        }

        System.out.println(sb.toString());
    }
}
