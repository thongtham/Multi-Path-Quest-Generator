package testing;

public class testSubString {

	public static void main (String arg[])
	{
		
		String inputSTR = "1:itemName:etc";

		int first = inputSTR.indexOf(":");
		int second = inputSTR.indexOf(":", first + 1);
		
		String variable = inputSTR.substring(first+1,second);
		String desireValue = inputSTR.substring(second+1,inputSTR.length());

		System.out.println(inputSTR);
		System.out.println(variable);
		System.out.println(desireValue);
		
		
		switch (variable)
		{
		// If [Character] or [Item in character inventory]
		case "1":
			System.out.println("Character");
			break;
		// If [Location] or [Item in Location on ground]
		case "2":
			System.out.println("Location");
			break;
		case "3":
			System.out.println("Location");
			break;
		case "4":
			System.out.println("Relationship");
			break;
			
			default:
				System.out.println("ERROR, typeOfObjectNumberIllgeal");
				break;
		}
		
	}
}
