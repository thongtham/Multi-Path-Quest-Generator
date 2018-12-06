package testing;

public class testIntToString {
	
	public static void main (String arg[])
	{
		int typeOfToken = 2;
		int typeOfObject = 3;
		
		String tempInt = Integer.toString(typeOfToken);
		tempInt += Integer.toString(typeOfObject);
		
		System.out.println(tempInt);
		
		boolean currentChar = false;
		
		String curIsAlive = Boolean.toString(currentChar);
		System.out.println(curIsAlive);
		
	}
}
