package enviroment;

import java.util.ArrayList;
import java.util.List;

import gameObject.Item;
import gameObject.Location;
import gameObject.Character;

public class GameWorld {
	
	String gameName;
	
	List<Character> listCharacter = new ArrayList<Character>();
	List<Location> listLocation = new ArrayList<Location>();
	List<Item> listItemOnWorld = new ArrayList<Item>();

	//Have to be implemented to each character
	int factionRelation;
	int factionRelation2;
	int factionRelation3;
	
	//Have to be implemented to each character
	int Restriction_To_Do_Certain_Action;   //From debuff?
	int Restruction_To_Do_Certain_Action2;  //From faction relation penelty?
	
	
	
	public GameWorld(){
		
	}
	
	public GameWorld(String name){
		gameName = name;
	}
	
	public boolean checkGameState(GameState inputState){
		return true;
	}
	
	public void getListCharacterFromLocation() {

		for(int i = 0; i < listLocation.size(); i++) {
			Location tempLocation = listLocation.get(i);
			List<Character> tempListCharacter = tempLocation.getListCharacter();
			listCharacter.addAll(tempListCharacter);
		}
		
	}
	public void getListItemFromLocation() {

		for(int i = 0; i < listLocation.size(); i++) {
			Location tempLocation = listLocation.get(i);
			List<Item> tempListItem = tempLocation.getListItem();
			listItemOnWorld.addAll(tempListItem);
		}
		
	}
	
}
