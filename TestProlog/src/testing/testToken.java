package testing;

import templateComponent.Token;
import gameObject.Character;

public class testToken {

	public static void main (String arg[]) {
		
		// NVM this test class
		//the Class Token just typo that cause error (= instead of ==   in getTokenInfo)
		
		Character newChar = new Character();
		newChar.setToGenericHuman("JACK", "Test Area");
		
		Token latestToken = new Token(1,newChar);
		
		/*
		latestToken.getInfo();
		System.out.println(latestToken.getInfo());
		
		latestToken.getTokenInfo();
		System.out.println(latestToken.getInfo());
		
		latestToken.setFirstInfo(false);
		System.out.println(latestToken.getInfo());
		
		System.out.println();
		*/
		
		System.out.println(latestToken.getTokenInfo());
		System.out.println(latestToken.getTokenInfo());
		
		/*
		
		
		if (latestToken.getTokenInfo()) {
			//curComponent.setObject(latestToken.getTokenObject());
			//curComponent.setTypeOfToken(1);
			//break;
			System.out.println("Info");
		}
		else if (latestToken.getTokenLoc()) {
			//curComponent.setObject(latestToken.getTokenObject());
			//curComponent.setTypeOfToken(2);
			//break;
			System.out.println("Loc");
		}
		else if (latestToken.getTokenItself()) {
			//curComponent.setObject(latestToken.getTokenObject());
			//curComponent.setTypeOfToken(3);
			//break;
			System.out.println("Itself");
		}
		// If == 0; it means last token is reached

		else {
			//continue loop;
			//currentTokenNumFromLast++;
		}
		
		
		
		*/
		
		
	}
}
