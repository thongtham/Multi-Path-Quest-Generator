package gameObject;

import java.util.ArrayList;
import java.util.List;

import enviroment.GameCondition;


//There should be a list of 'final' locations class 
//All thing that contain "String location" will compare the "location name"
//To this list, then the list will return the matched location name
//Then the return will be of "location" class

//This is to avoid each character and item containing location within itself and
//create location class with similar name but different attribute.

public class Location {

	String locationName;
	String locationType; //urban , forest , mountain , ocean
	String locationEnvironment; //rain , night, storm

	ArrayList<Item> listItemInLocation = new ArrayList<Item>();
	// This is fixed according to the map name (City_Morocco always connect to Desert, etc.)
	// What connect what is designed by hand on worldmap (using hand)
	// AKA: there's limit number of location, and their 'neighbor.' 
	ArrayList<String> listConnectLocation = new ArrayList<String>();

	//ArrayList<Character> listCharacterInLocation = new ArrayList<Character>();
	

	public Location(){
		// EMPTY
	}
	
	public Location(String name){
		locationName = name;
	}
	
	public Location(Location inputLocation) {
		
		locationName = inputLocation.getLocationName();
		locationType = inputLocation.getLocationType(); 
		locationEnvironment = inputLocation.getLocationEnvironment(); 

		ArrayList<Item> listItemInLocation = new ArrayList<Item>();
		for (Item itr : inputLocation.getListItem()) {
			Item itrDeepCopy = new Item(itr);
			listItemInLocation.add(itrDeepCopy);
		}
		
		ArrayList<String> listConnectLocation = new ArrayList<String>();
		for (String itr : inputLocation.getListConnectLocation()) {
			listConnectLocation.add(itr);
		}
		
	}
	
	
	
	public boolean compareLocation(Location comparedLocation){
		if (comparedLocation.getLocationName() == locationName){
			return true;
		}
		else{
			return false;
		}
	}
	

	
	/////////////////////////////////// Set Location //////////////////////////////
	
	public void setToGenericForest(){
		
		locationName = "Forest";
		locationType = "forest"; 
		locationEnvironment = "rain";

		listItemInLocation = new ArrayList<Item>();
		//listCharacterInLocation = new ArrayList<Character>();
		listConnectLocation = new ArrayList<String>();
		
		listConnectLocation.add("City");
		listConnectLocation.add("Dungeon");
	}
	
	public void setToGenericCity(){
		locationName = "City";
		locationType = "urban"; 
		locationEnvironment = "sunny";

		listItemInLocation = new ArrayList<Item>();
		//listCharacterInLocation = new ArrayList<Character>();
		listConnectLocation = new ArrayList<String>();
		
		listConnectLocation.add("Forest");
		listConnectLocation.add("Jail");
		listConnectLocation.add("Palace");
	}
	
	public void setToGenericJail(){
		locationName = "Jail";
		locationType = "urban"; 
		locationEnvironment = "night";

		listItemInLocation = new ArrayList<Item>();
		//listCharacterInLocation = new ArrayList<Character>();
		listConnectLocation = new ArrayList<String>();
		
		listConnectLocation.add("City");
	}
	
	public void setToGenericPalace(){
		locationName = "Palace";
		locationType = "urban"; 
		locationEnvironment = "sunny";

		listItemInLocation = new ArrayList<Item>();
		//listCharacterInLocation = new ArrayList<Character>();
		listConnectLocation = new ArrayList<String>();
		
		listConnectLocation.add("City");
	}
	
	public void setToGenericDungeon(){
		locationName = "Dungeon";
		locationType = "cave"; 
		locationEnvironment = "night";

		listItemInLocation = new ArrayList<Item>();
		//listCharacterInLocation = new ArrayList<Character>();
		listConnectLocation = new ArrayList<String>();
		
		listConnectLocation.add("Forest");
	}
	
	
	/////////////////////////////////// Set Location //////////////////////////////

	
	
	////////////////////manipulate Inventory ////////////////////
	
	
	public void addOrUpdateItem(Item newItem)
	{
		newItem.SetLocation(locationName);
		//IF there's a item with same id, this is update, thus old one will be removed
		//and new one replace it. (the Item class has @override equals)
		if (listItemInLocation.contains(newItem))
		{
			// ASK AJ, if override 'equals' also effect 'remove'?
			//**
			//**
			// Ask AJ, will this below 2 lines work? will it remove the 'old' and add
			// the new item?
			listItemInLocation.remove(newItem);
			listItemInLocation.add(newItem);
		}
		else {
			listItemInLocation.add(newItem);
		}
	}
	
	
	// for removing that EXCAT newItem 
	public void removeSpecificItem(Item inputItem)
	{
		// ASK AJ, if override 'equals' also effect 'remove'?
		//**
		//**
		listItemInLocation.remove(inputItem);
	}
	
	// for removing all item with the same name as newItem?	
	public void removeItemWithNameAll(String inputName)
	{
		ArrayList<Item> newListItem = new ArrayList<Item>();
		
		for (int x = 0; x<listItemInLocation.size();x++){
			if (listItemInLocation.get(x).getName() == inputName) {	
			}
			else{
				newListItem.add(listItemInLocation.get(x));
			}
		}
		listItemInLocation = newListItem;
	}
	
	//////////////////// get info ////////////////////
	
	public boolean isSpecificItemExist(Item inputItem) {
		return listItemInLocation.contains(inputItem);
	}
	
	public boolean isItemWithThisNameExist(String inputItemName) {
		boolean isItemExist = false;
		
		for (int x = 0; x<listItemInLocation.size();x++){
			if (listItemInLocation.get(x).getName() == inputItemName) 
			{	
				isItemExist = true;
			}
		}
		
		return isItemExist;
	}
	
	
	public String getLocationName(){
		return locationName;
	}
	
	public String getLocationType() {
		return locationType;
	}

	public String getLocationEnvironment() {
		return locationEnvironment;
	}
	
	public ArrayList<String> getListNearbyLocation(){
		return listConnectLocation;
	}
	
	public ArrayList<Item> getListItem(){
		return listItemInLocation;
	}
	
	public ArrayList<String> getListConnectLocation(){
		return listConnectLocation;
	}
	/*
	public ArrayList<String> getlistDesire(){
		return listStringDesire;
	}
	*/
	
	
	/*
	public boolean isCharacterInLocation(Character NPC) {
		
		for(int i = 0; i < listCharacterInLocation.size(); i++) {
			if (listCharacterInLocation.get(i) != null);
		}

			return false;
		
	}
	*/
	
	
	
	
	
	
	
	
	public String LocationToStringPrologFormat()
	{

		//1st return name		case ("locationName"):
		String output = "["; 
		
		output += this.locationName;
		output += ",";
		output += "locationName";
		output += ",";
		output += this.locationName;
		output += ",";
		output += "z";
		output += ",";
		output += "zz";
		output += ",";
		output += "zzz";
		output += "],"; 
		
		
		//1st return name		case ("locationType"):
		
		output += "["; 
		output += this.locationName;
		output += ",";
		output += "locationType";
		output += ",";
		output += this.locationType;
		output += ",";
		output += "z";
		output += ",";
		output += "zz";
		output += ",";
		output += "zzz";
		output += "],"; 
		
		
		//1st return name		case ("locationEnvironment"):
		output += "["; 
		output += this.locationName;
		output += ",";
		output += "locationEnvironment";
		output += ",";
		output += this.locationEnvironment;
		output += ",";
		output += "z";
		output += ",";
		output += "zz";
		output += ",";
		output += "zzz";
		output += "],"; 
		
		
		//1st return name		case ("listConnectLocation"):
		for (String str: this.listConnectLocation)
		{
			output += "["; 
			output += this.locationName;
			output += ",";
			output += "listConnectLocation";
			output += ",";
			output += str;
			output += ",";
			output += "z";
			output += ",";
			output += "zz";
			output += ",";
			output += "zzz";
			output += "],"; 
		}
		
		if(this.listItemInLocation.isEmpty())
		{
			output = output.substring(0, output.length()-1);
		}
		else
		{
			//1st return name		case ("listItemInLocation"):
			for(Item IC : this.listItemInLocation)
			{
				output += IC.toStringProlog(this.locationName,this.locationName);
			}
		}
					

		
		return output;
	
	}
	
	
	/////////////////////////////// OVERRIDE //////////////////////////////////

	public String toString()
	{
		String strReturn = "[";
		strReturn += locationName;
		strReturn += ":{";
		strReturn +=  locationType; 		
		strReturn += "},{";
		strReturn +=  locationEnvironment; 		
		strReturn += "}";
		strReturn += "]";
		return strReturn;
	}
	
	
}














//IGNORE (ACTUALLY conflict if same case, but current build no way to manipulate these attribute)
//case ("locationName"):
//case ("locationType"):
//case ("locationEnvironment"):
//case ("listConnectLocation"):

//case ("listItemInLocation"):


