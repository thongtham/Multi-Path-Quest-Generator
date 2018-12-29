package gameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
	
	
	int itemID = 0;	 //  EXPLAINING WHY WE NEED THIS  in [player:itemInInventory:itemName:Variable2:Desire2:ITEMID]
					 //
					 // *** Although each item has it's own unique ID, we still use [itemName] in checking itemName:ETC:TEC ***
					 // *** This is because the checking of player and location, it check all item in inventory anyway, so when 
					 // *** checking, it will check all item anyway
					 //
					 // *** Even generic item with same name (ex. berry), when checking for specific property, the code should check
				 	 // *** all item and just ignore berry that didn't pass the check and move to next berry
					 //
					 // *** In case of backward assigning from Prolog to Java, now that's where we need the ID if we are to assign
					 // *** the value to the item correctly.
	
	
	
	// Generic property that all Item with the same name share
	String itemName;
	ArrayList<String> typeOfItem = new ArrayList<String>(); 	 // supply , food , weapon , luxury
	ArrayList<String> typeOfFunction = new ArrayList<String>();; // consumable , equipment , object , container?
	
	
	//property that vary from Item to Item, making them unique from each other
	String holderName;
	String ownerName;
	boolean isOnGround;
	String currentLocation;
	ArrayList<String> listProperty = new ArrayList<String>();
	
	
	// Each object (character, item, etc.) should have levelQuest to determine what is 'acceptable' as quest
	// objective depended on quest giver (farmer shouldn't give quest to kill dragon)
	int levelQuest;
	
	// Some object (character, item, etc.) can give quest 
	// Ex. most character can give quest
	// Ex2. Some item can also give quest (treasure map, ancient tomb, secret letter)
	boolean isQuestGiver;
	
	
	
	//////////////// Constructor ///////////////////
	
	public Item(){
		
	}
	public Item(String name)
	{
		itemName = name;
	}
	
	public Item (Item inputItem) {
		
		itemID = inputItem.getID();	
		itemName = inputItem.getName();
		
		typeOfItem = new ArrayList<String>(); 	 // supply , food , weapon , luxury
		for (String str : inputItem.getListTypeOfItem()) {
			typeOfItem.add(str);
		}
		
		typeOfFunction = new ArrayList<String>(); // consumable , equipment , object , container?
		for (String str : inputItem.getListTypeOfFunction()) {
			typeOfFunction.add(str);
		}
		
		//property that vary from Item to Item, making them unique from each other
		holderName = inputItem.getHolderName();
		ownerName = inputItem.getOwnerName();
		isOnGround = inputItem.isItemOnGround();
		currentLocation = inputItem.getCurrentLocation();
		
		listProperty = new ArrayList<String>();
		for (String str : inputItem.getListProperty()) {
			typeOfFunction.add(str);
		}
		
		levelQuest = inputItem.getLevelQuest();
		isQuestGiver = inputItem.getisQuestGiver();
	}
	
	
	///////////////////Set item ////////////////
	
	
	public void setNewItem( int id, String inputName, 
							String[] inputType, String[] inputFunction, 
							String inputOwner, String[] inputProperty,
							int inputQuestLevel, boolean isQG)
	{
		
		List al = Arrays.asList(inputType);
		List al2 = Arrays.asList(inputFunction);
		List al3 = Arrays.asList(inputProperty);
		
		itemID = id;
		itemName = inputName;
		typeOfItem = new ArrayList<String>();
		typeOfFunction = new ArrayList<String>();
		typeOfItem.addAll(al);
		typeOfFunction.addAll(al2);
		
		//property that vary from Item to Item, making them unique from each other
		ownerName = inputOwner;
		isOnGround = false;
		currentLocation = "";
		ArrayList<String> listProperty = new ArrayList<String>();
		listProperty.addAll(al3);
		
		levelQuest = inputQuestLevel;
		isQuestGiver = isQG;
		
		
	}
	
	
	
	public void SetLocation(String location)
	{
		isOnGround = true;
		currentLocation = location;
		holderName = null;
	}
	
	public void setHolder(String holder)
	{
		holderName = holder;
		currentLocation = null;
	}
	
	public void setQuestLevel(int newQL)
	{
		levelQuest = newQL;
	}
	
	public void setIsQuestGiver(boolean inputB)
	{
		isQuestGiver = inputB;
	}
	
	
	//////////////////////// add,remove,change item property ///////////////////////
	
	public void addProperties(String inputProperty)
	{
		if (listProperty.contains(inputProperty))
		{
			//do nothing
		}
		else {
			listProperty.add(inputProperty);
		}
	}
	
	// for removing that EXCAT newItem 
	public void removeProperty(String inputProperty)
	{
		listProperty.remove(inputProperty);
		
	}

	
	///////////////// get info //////////////////
	public String getName()
	{
		return itemName;
	}
	
	public int getID()
	{
		return itemID;
	}
	
	public String getOwnerName()
	{
		return ownerName;
	}
	public boolean isOwned()
	{
		if (ownerName == "") return false;
		else if (ownerName == null) return false;
		else return true;
	}
	
	public String getHolderName()
	{
		return holderName;
	}
	
	public String getCurrentLocation()
	{
		if (!isOnGround) 
		{
			return "Not_On_Ground";
		}
		else
		{
			return currentLocation;
		}
	}
	
	public boolean isItemOnGround()
	{
		return isOnGround;
	}
	
	public ArrayList<String> getListProperty()
	{
		return listProperty;
	}
	
	public ArrayList<String> getListTypeOfItem()
	{
		return typeOfItem;
	}
	
	public ArrayList<String> getListTypeOfFunction()
	{
		return typeOfFunction;
	}
	
	public boolean propertyExist(String inputProp)
	{
		return listProperty.contains(inputProp);
	}

	public int getLevelQuest()
	{
		return  levelQuest;
	}
	
	public boolean getisQuestGiver()
	{
		return isQuestGiver;
	}
	
	
	
	////////////////get toString ///////////////////
	
	public String getItemProlog() {
		String output = "item(";
		output += typeOfItem;
		output += ",";
		output += itemName;
		output += ")";
		return output;
	}
	
	
	public String simpleToString() {
		return itemName;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	public String toStringProlog(String ownerName, String ownerLocation)
	{
		String output = "";
		
		//1st Item Name		case ("itemName") :
		output += "["; 
		output += ownerName;
		output += ",";
		output += "listItem";
		output += ",";
		output += this.itemName;
		output += ",";
		output += "itemName";
		output += ",";
		output += this.itemName;
		output += ",";
		output += this.itemID;
		output += "],"; 
		
		
		//1st Item Name		case ("ownerName") 
		output += "["; 
		output += ownerName;
		output += ",";
		output += "listItem";
		output += ",";
		output += this.itemName;
		output += ",";
		output += "ownerName";
		output += ",";
		output += this.itemName;
		output += ",";
		output += this.itemID;
		output += "],"; 
		
		//1st Item Name		case ("isOnGround")
		output += "["; 
		output += ownerName;
		output += ",";
		output += "listItem";
		output += ",";
		output += this.itemName;
		output += ",";
		output += "isOnGround";
		output += ",";
		output += String.valueOf(this.isOnGround);
		output += ",";
		output += this.itemID;
		output += "],"; 
		
		//1st Item Name		case ("currentLocation"):
		output += "["; 
		output += ownerName;
		output += ",";
		output += "listItem";
		output += ",";
		output += this.itemName;
		output += ",";
		output += "currentLocation";
		output += ",";
		output += ownerLocation;
		output += ",";
		output += this.itemID;
		output += "],"; 
		
		
		//1st Item Name		case ("typeOfItem") :
		for (String str: this.typeOfItem)
		{
			output += "["; 
			output += ownerName;
			output += ",";
			output += "listItem";
			output += ",";
			output += this.itemName;
			output += ",";
			output += "typeOfItem";
			output += ",";
			output += str;
			output += ",";
			output += this.itemID;
			output += "],"; 
		}
		//1st Item Name		case ("typeOfFunction") : 
		for (String str: this.typeOfFunction)
		{
			output += "["; 
			output += ownerName;
			output += ",";
			output += "listItem";
			output += ",";
			output += this.itemName;
			output += ",";
			output += "typeOfFunction";
			output += ",";
			output += str;
			output += ",";
			output += this.itemID;
			output += "],"; 
		}
		//1st Item Name		case ("listProperty") :
		for (String str: this.listProperty)
		{
			output += "["; 
			output += ownerName;
			output += ",";
			output += "listItem";
			output += ",";
			output += this.itemName;
			output += ",";
			output += "listProperty";
			output += ",";
			output += str;
			output += ",";
			output += this.itemID;
			output += "],"; 
		}
		
		output = output.substring(0, output.length()-1);
		return output;
	}
	
	
	
	
	
	
	
	/////////////////////////OVERRIDE////////////////////////////////
	
    @Override
    public boolean equals(Object o){
    	  if(o instanceof Item){
    		  Item toCompare = (Item) o;
    		  //IF they have same ID, they are the same item
    		  if (itemID == toCompare.getID()) {
    			return true;  
    		  }
    		  //ELSE, even with identical property, it just meant the character has 2
    		  //identical item.
    		  else {
    			  return false;
    		  }
    	  }
    	  return false;
    }
    

	
    
    public int hashCode() {
        int result = 17;
        result = 31 * result + itemName.hashCode();
        result = 31 * result + Integer.hashCode(itemID);
        result = 31 * result + typeOfItem.hashCode();
        result = 31 * result + typeOfFunction.hashCode();
        result = 31 * result + ownerName.hashCode();
        result = 31 * result + Boolean.hashCode(isOnGround);
        result = 31 * result + currentLocation.hashCode();
        result = 31 * result + listProperty.hashCode();
        return result;
    }
    
	
	public String toString()
	{
		String strReturn = "[";
		strReturn += itemName;
		strReturn += ",";
		strReturn += currentLocation;
		strReturn += ",";
		strReturn += itemID;
		strReturn += ",";
		
		if (ownerName == "" || ownerName.isEmpty())
		{
			strReturn += "no_owner";
		}
		else 
		{
			strReturn += ownerName;
		}
		strReturn += ",";
		strReturn += typeOfItem;
		strReturn += ",";
		strReturn += typeOfFunction;
		strReturn += ",";
		strReturn += "[";
		
		
		String result = String.join(",", listProperty);
		strReturn += result;
		
		strReturn += "]]";
		
		//System.out.println("ToString Item");
		
		return strReturn;
		
	}
	
}
