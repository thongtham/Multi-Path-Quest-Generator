package gameObject;

import java.util.ArrayList;
import java.util.List;


//There should be a list of 'final' locations class 
//All thing that contain "String location" will compare the "location name"
//To this list, then the list will return the matched location name
//Then the return will be of "location" class

//This is to avoid each character and item containing location within itself and
//create location class with similar name but different attribute.

public class Location {
	int x;
	int y;
	int z;
	
	String locationName;
	String locationType; //City , forest , mountain , ocean
	String locationEnviroment; //Rain , night, storm

	List<Item> listItemInLocation = new ArrayList<Item>();
	List<Location> listConnectLocation = new ArrayList<Location>();
	List<Character> listCharacterInLocation = new ArrayList<Character>();
	
	
	
	public Location(String name){
		locationName = name;
	}
	
	public boolean compareLocation(Location comparedLocation){
		if (comparedLocation.getLocation() == locationName){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public boolean isCharacterInLocation(Character NPC) {
		
		for(int i = 0; i < listCharacterInLocation.size(); i++) {
			if (listCharacterInLocation.get(i) != null);
		}

			return false;
		
	}
	
	
	
	
	
	
	public String getLocation(){
		return locationName;
	}
	
	public List<Character> getListCharacter(){
		return listCharacterInLocation;
	}
	public List<Item> getListItem(){
		return listItemInLocation;
	}
	
}
