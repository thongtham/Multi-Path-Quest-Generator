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
	
	String characterName;
	boolean isPlayer;
	int level;
	boolean isAlive; 			//Status
	String attribute;
	
	String currentLocation;
	String charRace;

	ArrayList<String> listStatus = new ArrayList<String>();
	ArrayList<Item> listItem = new ArrayList<Item>();       // This is character's Inventory 
	ArrayList<Skill> listSkill = new ArrayList<Skill>();
	ArrayList<Occupation> listOccupation = new ArrayList<Occupation>();

	//List<String> listFriend = new ArrayList<String>();
	//List<String> listEnemy = new ArrayList<String>();
	
	public Character()
	{
		
	}
	
	public Character(String name)
	{
		characterName = name;
	}
	//character(Name,Level,Status,Attribute,Location)
	public Character(String newName, int newLevel,Boolean newStatus, String newLocation)
	{
		characterName = newName;
		level = newLevel;
		isAlive = newStatus;
		currentLocation = newLocation;
		attribute = "nothing";

	}
	
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
	
	public void addStatus(String str)
	{
		listStatus.add(str);
	}
	public boolean removeStatus(String str)
	{
		boolean isSuccess = listStatus.remove(str);
		return isSuccess;
	}
	
	
	public void setToGenericHuman(String Name,String Location)
	{
		characterName = Name;
		isPlayer = false;
		level = 15;
		isAlive = true;			//Status
		attribute = "nothing";   // Obsolete, kept for prolog format
		
		currentLocation = Location;
		
		charRace = "Human";
		
		//listStatus.add("Test1");
		//listStatus.add("Test2");
		Item NI = new Item("item1");
		listItem.add(NI);
	}
	
	public String getName()
	{
		return characterName;
	}
	
	public boolean getStatus()
	{
		return isAlive;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public String getLocation()
	{
		return currentLocation;
	}
	
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
	
	
	public boolean equals(Character compareCharacter)
	{
	    if (compareCharacter == null) return false;
	    if (compareCharacter == this ) return true;
	    if (!(compareCharacter instanceof Character)) return false;
	    
	    // If same name = always true
	    return compareCharacter.getName() == this.characterName;

	}
	
	
	
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
		
		for (Item A: listItem)
		{
			result += A.simpleToString();
			result += ",";
		}
		result.substring(0, result.length() -1 );
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
