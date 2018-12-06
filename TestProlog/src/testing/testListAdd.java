package testing;

import java.util.ArrayList;

import gameObject.Item;
import gameObject.Location;

public class testListAdd {

	public static void main (String args[]) {
		
		//
		// This test confirm that after adding a location to the listLation,
		// Then add a item to the location without updating listLation
		// ** STILL UPDATE ITEM LIST of the location within the LIST.
		
		ArrayList<Location> listLocation = new ArrayList<Location>();
		
		//Location;
		
		Location newLocationCity = new Location();
		newLocationCity.setToGenericCity();
		Location newLocationDungeon = new Location();
		newLocationDungeon.setToGenericDungeon();
		Location newLocationForest = new Location();
		newLocationForest.setToGenericForest();
		Location newLocationJail = new Location();
		newLocationJail.setToGenericJail();
		Location newLocationPalace = new Location();
		newLocationPalace.setToGenericPalace();
		
		listLocation.add(newLocationCity);
		listLocation.add(newLocationDungeon);
		listLocation.add(newLocationForest);
		listLocation.add(newLocationJail);
		listLocation.add(newLocationPalace);
		
		System.out.println(listLocation);
		
		for(Location curLo : listLocation) {
			System.out.println(curLo.getListItem());
		}
		
		System.out.println("DONE BEFORE ADD ITEM");
		
		//Item
		Item newItem = new Item();
		
		String[] type =		new String[]	{"luxury"};
		String[] function = new String[]	{"object"};	
		String[] property = new String[]	{""};
		newItem.setNewItem(1,"Diamond", type, function, "", property);
		
		newLocationCity.addOrUpdateItem(newItem);

		/*
		listLocation.add(newLocationCity);
		listLocation.add(newLocationDungeon);
		listLocation.add(newLocationForest);
		listLocation.add(newLocationJail);
		listLocation.add(newLocationPalace);
		*/
		
		for(Location curLo : listLocation) {
			System.out.println(curLo.getListItem());
		}
		
	}
}
