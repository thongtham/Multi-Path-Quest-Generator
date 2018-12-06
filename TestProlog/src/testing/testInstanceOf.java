package testing;

import gameObject.Character;
import gameObject.Item;
import gameObject.Location;
import templateComponent.Token;

public class testInstanceOf {

	public static void main (String arg[]) {

		Character newChar = new Character("player", 1, true,"City");
		Item newItem = new Item();
		Location newLocation = new Location();
		
		Token newToken = new Token(1, newChar);
		
		
		System.out.println(newChar instanceof Character);
		System.out.println(newItem instanceof Item);
		System.out.println(newLocation instanceof Location);
		
		System.out.println(newToken.getTokenObject() instanceof Character);
		
	}
}
