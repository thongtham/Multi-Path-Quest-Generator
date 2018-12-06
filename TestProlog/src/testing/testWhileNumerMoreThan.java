package testing;

import java.util.ArrayList;

public class testWhileNumerMoreThan {

	public static void main(String arg[]) {
		
		int a = 0;
		int b = 3;
		
		ArrayList<Integer> la = new ArrayList<Integer>();
		ArrayList<Integer> lb = new ArrayList<Integer>();
		
		la.add(2);
		la.add(4);
		la.add(6);
		la.add(8);
		
		int x = 0;
		while (a <= b) {
			System.out.println(a);
			a = la.get(x);
			x++;
		}
	}
}
