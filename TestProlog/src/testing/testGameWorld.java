package testing;

import java.util.ArrayList;

import enviroment.GameWorld;
import gameObject.Character;
import templateComponent.Token;

public class testGameWorld {
	
	//static GameWorld mainGameWorld;
	static ArrayList<Token> tokenList;
	
	public static void main(String arg[])
	{
		ArrayList<Character> listCharacter = new ArrayList<Character>();
		
		Character newChar = new Character("player", 1, true,"City");
		listCharacter.add(newChar);
		newChar = new Character("Alpha", 15, true,"City");
		listCharacter.add(newChar);
		newChar = new Character("Beta", 15, true,"City");
		listCharacter.add(newChar);
		newChar = new Character("Charle", 15, true,"City");
		listCharacter.add(newChar);
		newChar = new Character("Delta", 15, true,"City");
		listCharacter.add(newChar);
		
		//System.out.println(listCharacter.toString());
		
		//----------------------------------------
		
		GameWorld mainGameWorld = new GameWorld();
		tokenList = new ArrayList<Token>();
				
		System.out.println("breakpoint 1");
		mainGameWorld.setBasicNewGameWorld();
	
			
		System.out.println(mainGameWorld.getListCharacter().toString());
		System.out.println(mainGameWorld.getListItemFromLocationAll().toString());
	}
}
