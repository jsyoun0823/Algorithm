import java.util.Scanner;
import java.util.regex.Pattern;

public class 백준_잠수함식별 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		//(100~1~|01)~
        String pattern = "(100+1+|01)+";

        boolean regex = Pattern.matches(pattern, str);
        if(regex) System.out.println("SUBMARINE");
        else System.out.println("NOISE");

	}

}
