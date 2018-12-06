package testing;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class testRandomNumIF {

	public static void main(String args[])
	{
		ArrayList<String> stringOutput = new ArrayList<String>();
		stringOutput.add("test");
		int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
		if (randomNum == 1) {stringOutput.add("NULL"); 
							}
		if (randomNum == 2) {stringOutput.add("goto"); 
							}
		if (randomNum == 3) {stringOutput.add("wait"); 
							}
		if (randomNum == 4) {stringOutput.add("explore"); 
							}
		if (randomNum == 5) {stringOutput.add("follow"); 
		}
		if (randomNum == 6) {stringOutput.add("<stealth>"); 
							}
		if (randomNum == 7) {stringOutput.add("<learn>"); 
							stringOutput.add("<goto>"); 
							}
		if (randomNum == 8) {stringOutput.add("<prepare>"); 
							stringOutput.add("<goto>"); 
							}
		
		System.out.println(stringOutput);
		
	}
}
