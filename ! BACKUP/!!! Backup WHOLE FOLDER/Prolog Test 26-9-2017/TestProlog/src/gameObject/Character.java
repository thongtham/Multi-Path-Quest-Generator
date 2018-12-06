package gameObject;

import java.util.ArrayList;
import java.util.List;

public class Character {
	
	String characterName;
	boolean isPlayer;

	Location currentLocation;
	
	Race charRace;
	int level;
	int hp;
	int mp;
	boolean isAlive;
	
	List<Item> listItem = new ArrayList<Item>();
	List<Skill> listSkill = new ArrayList<Skill>();
	List<Status> listStatus = new ArrayList<Status>();
	List<Occupation> listOccupation = new ArrayList<Occupation>();
	
	List<Character> listFriend = new ArrayList<Character>();
	List<Character> listEnemy = new ArrayList<Character>();
	
	public Character(){
		
	}
	
	public Character(String name){
		characterName = name;
	}
	
}
