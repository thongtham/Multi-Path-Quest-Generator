package gameObject;

import java.util.ArrayList;
import java.util.List;

public class Item {
	
	String itemName;
	
	Location currentLocation;
	
	int value;
	
	String ownerName;
	String typeOfItem; // supply , food , weapon , luxury
	String typeOfFunction; // consumable , equipment , object , container?
	
	List<Property> listProperty = new ArrayList<Property>();
	
	
	
	public Item(){
		
	}
	public Item(String name){
		itemName = name;
	}
	
}
