package gameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//
// *** NOTE TO SELF
//  Rewrite Prolog to not use Attribute (It's obsolete now).


public class Character {
	
	
	//character(Name,Level,Status,Attribute,Location)
	
	//Character will use characterName as unique ID (unlike item, which can has identical name)
	String characterName;
	boolean isPlayer;
	int level;
	boolean isAlive;  //Status
	
	String attribute;
	
	String currentLocation;
	String charRace;

	ArrayList<String> listStatus = new ArrayList<String>();
	ArrayList<Item> listItem = new ArrayList<Item>();       // This is character's Inventory 
	ArrayList<String> listSkill = new ArrayList<String>();
	ArrayList<String> listOccupation = new ArrayList<String>();

	// Each object (character, item, etc.) should have levelQuest to determine what is 'acceptable' as quest
	// objective depended on quest giver (farmer shouldn't give quest to kill dragon)
	int levelQuest;
	
	// Some object (character, item, etc.) can give quest 
	// Ex. most character can give quest
	// Ex2. Some item can also give quest (treasure map, ancient tomb, secret letter)
	boolean isQuestGiver;

	//List<String> listFriend = new ArrayList<String>();
	//List<String> listEnemy = new ArrayList<String>();


	//////////////////////////Constructor ////////////////////////////////
	
	
	public Character()
	{
		characterName = "testing Character";
	}
	
	
	public Character (Character inputC)
	{
		characterName = new String (inputC.getName());
		isPlayer = inputC.isPlayer();
		level = inputC.getLevel();
		isAlive = inputC.getIsAlive();  
		
		attribute = new String (inputC.getAttribute());
		
		currentLocation = new String (inputC.getLocation());
		charRace = new String (inputC.getRace());

		listStatus = new ArrayList<String>();
		for (String str : inputC.getListStatus()) {
			String strDeepCopy = new String (str);
			listStatus.add(strDeepCopy);
		}
				
		listItem = new ArrayList<Item>();    
		for (Item str : inputC.getListItem()) {
			Item strDeep = new Item(str);
			listItem.add(strDeep);
		}
		listSkill = new ArrayList<String>();
		for (String str : inputC.getListSkill()) {
			String strDeepCopy = new String (str);
			listSkill.add(strDeepCopy);
		}
		listOccupation = new ArrayList<String>();
		for (String str : inputC.getListOccupation()) {
			String strDeepCopy = new String (str);
			listOccupation.add(strDeepCopy);
		}
		
		levelQuest = inputC.getLevelQuest();
		isQuestGiver = inputC.getisQuestGiver();
	}
	
	public Character(String name)
	{
		characterName = name;
	}
	//character(Name,Level,Status,Attribute,Location)
	public Character(String newName, int newLevel, Boolean newStatus, String newLocation, int questLvl, boolean isQG)
	{
		characterName = newName;
		level = newLevel;
		isAlive = newStatus;
		currentLocation = newLocation;
		attribute = "";
		isQuestGiver = isQG;
		levelQuest = questLvl;
	}
	
	//////////////////////////Constructor ////////////////////////////////
	
	
	
	//////////////////////////set data ////////////////////////////////
	
	public void setIsAlive(boolean boo) 
	{
		isAlive = boo;
	}
	
	public void setCharName(String newName) 
	{
		characterName = newName;
	}
	
	public void setIsPlayer(Boolean boo)
	{
		isPlayer = boo;
	}
	
	public void setLevel(int lvl)
	{
		level = lvl;
	}

	public void setCurrentLocation(String newLo)
	{
		currentLocation = newLo;
	}

	public void setQuestLevel(int newQL)
	{
		levelQuest = newQL;
	}
	
	public void setIsQuestGiver(boolean inputB)
	{
		isQuestGiver = inputB;
	}
	
	//////////////////////////reset data ////////////////////////////////
	
	
	public void setToGenericHuman(String Name,String Location)
	{
		characterName = Name;
		isPlayer = false;
		level = 15;
		isAlive = true;			//Status
		attribute = "nothing";   // Obsolete, kept for prolog format
		
		currentLocation = Location;
		
		charRace = "Human";
		
		listStatus = new ArrayList<String>();
		listItem = new ArrayList<Item>();       // This is character's Inventory 
		listSkill = new ArrayList<String>();
		listOccupation = new ArrayList<String>();

	}
	
	//////////////////////////reset data ////////////////////////////////
	
	
	
	//////////////////////////modify data ////////////////////////////////
	
	
	public void addStatus(String str)
	{
		if (!listStatus.contains(str))listStatus.add(str);
	}

	public boolean removeStatus(String str)
	{
		//OLD CODE
		boolean isSuccess = listStatus.remove(str);
		return isSuccess;
	}

	////
	
	public void addOccupation(String str)
	{
		if (!listOccupation.contains(str))listOccupation.add(str);
	}

	public boolean removeOccupation(String str)
	{
		//OLD CODE
		boolean isSuccess = listOccupation.remove(str);
		return isSuccess;
	}
	
	////

	public void addSkill(String str)
	{
		if (!listSkill.contains(str))listSkill.add(str);
	}

	public boolean removeSkill(String str)
	{
		//OLD CODE
		boolean isSuccess = listSkill.remove(str);
		return isSuccess;
	}

	//////////////////////////modify data ////////////////////////////////
	
	
	
	////////////////////////////manipulate object ////////////////////////
	
	
	public void addOrUpdateItem(Item newItem)
	{
		newItem.setHolder(characterName);
		Item tempNI = new Item(newItem);
		
		//IF there's a item with same id, this is update, thus old one will be removed
		//and new one replace it. (the Item class has @override equals)
		if (listItem.contains(newItem))
		{
			listItem.remove(newItem);
			listItem.add(tempNI);
		}
		else {
			listItem.add(newItem);
		}
	}
	
	
	// for removing that EXCAT newItem 
	public void removeSpecificItem(Item inputItem)
	{
		// ASK AJ, if override 'equals' also effect 'remove'?
		//**
		//**
		listItem.remove(inputItem);
	}
	
	// for removing all item with the same name as newItem?	
	public void removeItemWithNameAll(String inputName)
	{
		ArrayList<Item> newListItem = new ArrayList<Item>();
		
		for (int x = 0; x<listItem.size();x++){
			if (listItem.get(x).getName() == inputName) {	
			}
			else{
				newListItem.add(listItem.get(x));
			}
		}
		listItem = newListItem;
	}

	////////////////////////////manipulate object ////////////////////
	
	
	
	//////////////////////// get information ////////////////////////
	
	
	public String getName()
	{
		return characterName;
	}
	
	public boolean isPlayer()
	{
		return isPlayer;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	public int getLevel()
	{
		return level;
	}
	public String getAttribute()
	{
		return attribute;
	}
	
	public String getLocation()
	{
		return currentLocation;
	}
	
	public String getRace()
	{
		return charRace;
	}
	
	public ArrayList<String> getListStatus()
	{
		return listStatus;
	}
	
	public ArrayList<Item> getListItem()
	{
		return listItem;
	}
	
	public ArrayList<String> getListSkill()
	{
		return listSkill;
	}
	public ArrayList<String> getListOccupation()
	{
		return listOccupation;
	}

	public int getLevelQuest()
	{
		return  levelQuest;
	}
	
	public boolean getisQuestGiver()
	{
		return isQuestGiver;
	}
	/*
	public ArrayList<String> getlistDesire()
	{
		return listStringDesire;
	}
	*/
	
	public boolean isSameChararacter(Character compareCharacter)
	{
	    if (compareCharacter == null) return false;
	    if (compareCharacter == this ) return true;
	    if (!(compareCharacter instanceof Character)) return false;
	    
	    // If same name = always true
	    return compareCharacter.getName() == this.characterName;

	}
	
	
	
	
	
	//////////////// utility ////////////////////////
	
	
	public String toStringPrologFormat()
	{
		String strReturn = "character(";

			strReturn += characterName;
			strReturn += ",";
			strReturn += level;
			strReturn += ",";
			
			String isAliveS = "";
			if (isAlive){isAliveS = "alive";}
			else 		{isAliveS = "dead";}
			strReturn += isAliveS;
			
			strReturn += ",";
			strReturn += attribute;  // Obsolete, kept for prolog format
			strReturn += ",";
			strReturn += currentLocation;	
			strReturn += ")";

		return strReturn;
	}
	
	
	
	
	
	
	
	
	///////////Below = override Java Utility methods/////////////////////////////
	
	
	
	
	
	public String toString()
	{
		String strReturn = "";
		
		strReturn += "[IsPlayer:";
		strReturn += isPlayer;
		strReturn += ", Name:";
		strReturn += characterName;
		strReturn += ", Level:";
		strReturn += level;
		strReturn += ", isAlive:";
		strReturn += isAlive;
		strReturn += ", CurrentLocation:";
		strReturn += currentLocation;
		strReturn += ", Race:";
		strReturn += charRace;
		strReturn += " | Status = [";

		//System.out.println(listStatus);
		String result = String.join(",", listStatus);
		strReturn += result;
		
		strReturn += "] : Item = [";
		//List<Item> listItem = new ArrayList<Item>();
		
		
		// v this is too detail, thus why use simpleToString instead 
		//result = listItem.stream().map(Object::toString).collect(Collectors.joining(", "));
		
		boolean isWriteItem = false;
		for (Item A: listItem)
		{
			result += A.simpleToString();
			result += ",";
			isWriteItem = true;
		}
		if (isWriteItem) result.substring(0, result.length() -1 );
		strReturn += result;
		
		
		
		strReturn += "] : Skill = [";
		//List<Skill> listSkill = new ArrayList<Skill>();
		result = listSkill.stream().map(Object::toString).collect(Collectors.joining(", "));
		strReturn += result;
		
		strReturn += "] : Occupation = [";
		//List<Occupation> listOccupation = new ArrayList<Occupation>();
		result = listOccupation.stream().map(Object::toString).collect(Collectors.joining(", "));
		strReturn += result;
		
		//strReturn += ":";
		//List<Character> listFriend = new ArrayList<Character>();
		//result = listFriend.stream().map(Object::toString).collect(Collectors.joining(", "));
		//strReturn += result;
		
		//strReturn += ":";
		//List<Character> listEnemy = new ArrayList<Character>();
		//result = listEnemy.stream().map(Object::toString).collect(Collectors.joining(", "));
		//strReturn += result;	
		
		strReturn += "]";
		strReturn += "\n";
		return strReturn;
	}
	
}
