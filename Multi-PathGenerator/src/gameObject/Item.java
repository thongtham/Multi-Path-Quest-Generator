package gameObject;

import java.util.ArrayList;
import java.util.List;

public class Item {
	
	String itemName;
	String currentLocation;
	
	int value;
	
	String ownerName;
	String typeOfItem; // supply , food , weapon , luxury
	String typeOfFunction; // consumable , equipment , object , container?
	
	List<String> listProperty = new ArrayList<String>();
	
	
	
	public Item(){
		
	}
	public Item(String name)
	{
		itemName = name;
	}
	
	public void setItemOnGround(String newItemName,String newTypeOfItem)
	{
		itemName = newItemName;
		typeOfItem = newTypeOfItem;
	}
	
	
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
	
	public String toString()
	{
		String strReturn = "[";
		strReturn += itemName;
		strReturn += ",";
		strReturn += currentLocation;
		strReturn += ",";
		strReturn += value;
		strReturn += ",";
		strReturn += ownerName;
		strReturn += ",";
		strReturn += typeOfItem;
		strReturn += ",";
		strReturn += typeOfFunction;
		strReturn += "[";
		
		
		String result = String.join(",", listProperty);
		strReturn += result;
		
		strReturn += "]]";
		
		//System.out.println("ToString Item");
		
		return strReturn;
		
	}
	
}
