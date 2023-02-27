package mainGenerator;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import enviroment.GameCondition;
import enviroment.GameState;
import enviroment.GameWorld;
import gameObject.Character;
import gameObject.Item;
import gameObject.Location;
import templateComponent.Component;

public class QuestGeneratorUtility {

	public QuestGeneratorUtility()
	{
		
	}
	
	
	
	
	//The front string = what variable to check, the later string = what the variable must be
	//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
	//EX: for ITEM ["CHARNAME:listItem:THE_ITEM_NAME:typeOfItem:luxury"]  item has to be 4 steps
	//EX: ["LocationNAME:locationType:City","LocationNAME:locationEnvironment:night",""]
	//EX: for ITEM ["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"]  item has to be 4 steps
	
	
	//---- SPECIAL CASE ----
	// In case of checking if the NPC is at the same location as a certain NPC, use the following string instead
	// EX: ["CharNAME:sameLocation:Char2NAME"]
	//

	
	public static ArrayList<String> CreateStartCondition(Component componentinput, GameWorld inputGameWorld) {


		
		ArrayList<String> outputList = new ArrayList<String>();
		String objectType = getTypeOfObject(componentinput);

		// 1 = info
		// 2 = loc
		// 3 = itself
		// 4 = Examine = empty >>> the Object also empty 
		int typeOfToken = componentinput.gettypeOfToken();

		// 1 = Character
		// 2 = Item
		// 3 = Location
		// 4 = NULL
		int typeOfObject = 0;

		
		switch (objectType) {
		
		case "Character":
			//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
						typeOfObject = 1;
						break;
						
		case "Item":
						typeOfObject = 2;
						break;
						
		case "Location":
						typeOfObject = 3;
						break;
						
		case "NULL":
						//if null >>>> do nothing
						typeOfObject = 4;
						break;
		
		default: 
			// T
			System.out.println("ERROR, INCORRECT OBJECT TYPE in CreateRestriction");
			break;
				
		
		}
		
		String tempInt = Integer.toString(typeOfToken);
		tempInt += Integer.toString(typeOfObject);
		
		
		
		// 11
		// Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12
		// Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13
		// Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		//
		// 21 
		// Loc >>> Loc of Item				= Get the Location of the Item, and condition = [player at that location]?
		// 22
		// Loc >>> Loc of Character			= Get the Location of the Character, and condition = [player at that location]?
		// 23
		// Loc >>> Loc of Location			= just [player same location]?
		//
		// 31
		// itself >>> itself of Item		= Depend on Component type (dmg, give, etc.)
		// 32
		// itself >>> itself of Character	= Depend on Component type (capture, free, etc.)
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		//
		//
		// IF(contain '4') == null and ignore >> go to next one.
		
		
		Object componentObjectONE = componentinput.getComponentObject();
		Object componentObjectSECOND = componentinput.getComponentObjectSecondary();
		
		
		// Int to store random number to select random object
		int randomNum;
		// Object to store ANY random object
		Object randomObject;
		// Character to store ANY(1) Random Character
		Character RandomCharacter1 = new Character();
		
		Item componentObject_1_ITEM = new Item();
		Character componentObject_1_Character = new Character();
		Location componentObject_1_Location = new Location();
		
		String output = "";
		String strLocation = "";
		
		switch (tempInt)
		{
		// 11 = Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12 = Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13 = Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		case "11":
		case "12":
		case "13":
			// In CreateResolvedCondition(), this was used for selecting random Character
			// Normally the startState should also stated that the selected (randomed) character
			// should be alive, but to also received input (List<String>) from ResolvedCondition
			// so that this know what character is selected would be too much problem.
			// Thus let skill this part for the sake of simplicity, since when RestrictionStated is
			// finally created, the character condition Must_Be_Alive would be added anyway

			// Therefore, just return empty list;
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
			
		// 21
		// Loc >>> Loc of Item			= Get the Location of the Item, and condition = [player at that location]?
		case "21":
			
			// Same reasoning as above
			// Also because currently, there's no plan for action that let player destroy whole place, so no sense
			// to check if place exist;
			outputList.clear();
			outputList.add("NULL");
			return outputList;

			
		// 22
		// Loc >>> Loc of Character		= Get the Location of the Character, and condition = [player at that location]?		
		case "22":
			
			// Same reasoning as above
			// Also because currently, there's no plan for action that let player destroy whole place, so no sense
			// to check if place exist;
			outputList.clear();
			outputList.add("NULL");
			return outputList;
		
			
		// 23
		// Loc >>> Loc of Location		= just [player same location]?
			
		case "23":
			
			// Same reasoning as above
			// Also because currently, there's no plan for action that let player destroy whole place, so no sense
			// to check if place exist;
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
			
			
		// 31
		// itself >>> itself of Character	= Depend on Component type (dmg, give, etc.)
		case "31":
			componentObject_1_Character = (Character) componentObjectONE;
			break;
			
			
			
		// 32
		// itself >>> itself of Item	= Depend on Component type (capture, free, etc.)
		case "32":
			componentObject_1_ITEM = (Item) componentObjectONE;
			break;
		
			
			
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		case "33":
			componentObject_1_Location = (Location) componentObjectONE;
			break;
			
		default: 
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		}
		
		
		
		String currentCompoentName = componentinput.getComponentName();
		Character curChar = new Character();
		Item curItem = new Item();
		
	////  ---------BELOW = switch for each component that group with how their token are turned into GOAL-----------
	////
		switch (currentCompoentName)
		{
			// Must make sure the target =  capture >>>> Therefore target must not be currently be captured
			case "capture":
				
				// config *********************
				//        = Let not put NOT_Capture as StartCondition, it should be in ResolvedCondition*contain
				//        *********************
				/*
				curChar = (Character) componentinput.getComponentObject();
				output = "";
				output += curChar.getName();
				output += ":listStatusNOT:" + "Captured";
				outputList.add(output);
				*/
				
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
			// Must make sure the target =  free >>>> Therefore target must be currently be captured
				
			case "free" :
				
				// config *********************
				//        = Let not put NOT_Capture as StartCondition, it should be in ResolvedCondition*contain
				//    
				/*
				curChar = (Character) componentinput.getComponentObject();
				output = "";
				output += curChar.getName();
				output += ":listStatus:" + "Captured"; 
				outputList.add(output);
				*/
				
				
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
				
			// Must make sure the target object = damaged status >>>> Thus it must not currently be damaged?
			case "damage" :
				

				// 1 == Character
				if (typeOfObject == 1)
				{
					curChar = (Character) componentinput.getComponentObject();
					output = "1:";
					output += curChar.getName();
					output += ":listStatusNOT:" + "damaged";
					outputList.add(output);
					
					// Only damaged, but not kill
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					
					outputList.add(output);
					
					
					return outputList;
				}
				// 2 == Item
				else if (typeOfObject == 2)
				{
					curItem = (Item) componentinput.getComponentObject();

					//"LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"
					//"CharNAME:listItem:THE_ITEM_NAME:listProperty:luxury"
					//
					
					// If on_ground => on location
					if (curItem.isItemOnGround())
					{
						output = "2:";
						output += curItem.getCurrentLocation();
						output += ":listItemInLocation:" + curItem.getName();
						output += ":listPropertyNOT:" + "damaged";
						outputList.add(output);
						return outputList;
					}
					// if not on_ground => in some char inventory
					else 
					{
						
						output = "1:";
						output += curItem.getHolderName();
						output += ":listItem:" + curItem.getName();
						output += ":listPropertyNOT:" + "damaged";
						outputList.add(output);
						return outputList;
					}
				}
				else 
				{
					System.out.println("case \"damage\" error, at CreateStartCondition method");
					outputList.add("ERROR");
					return outputList;
				}
				
			
			// Kill >>>> Must be alive at start
			case "kill" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				return outputList;
				
				
				
			case "defend" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				
				outputList.add(output);
				return outputList;
				
				
				//
				//
				// 
				// Let add new Condition that check player's stat (level, etc.) to simulate that player has to be this good to 
				// be able to protect the NPC from another NPC. (maybe add 2nd objective that simulate as enemy, player has to has
				// higher lvl or overall stat than the 2nd object???)
				//
				//
				
				
				
			case "escort" :
			case "follow" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				
				
				outputList.add(output);
				return outputList;
				
				
				
				
				
				// These = just be in same place as token's object = pass goal
			case "report":

				// For now, just let the character be alive, and condition that player = same place?
				
				
				// 1 == Character
				if (typeOfObject == 1)
				{
					
					// Only damaged, but not kill for report
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					outputList.add(output);
					
					return outputList;
				}
				
				else 
				{
					System.out.println("case \"report\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
				
				
				// These = just be in same place as token's object = pass goal
			case "examine":
			case "explore":
			case "listen":
				
			case "goto":
				
				// For now, just let the character be alive, and condition that player = same place?
				
				
				// 1 == Character
				if (typeOfObject == 1)
				{
					outputList.clear();
					outputList.add("NULL");
					return outputList;
				}
				
				// 2 == Item
				else if (typeOfObject == 2)
				{
					curItem = (Item) componentinput.getComponentObject();

					
					if (curItem.isItemOnGround())
					{
						outputList.clear();
						outputList.add("NULL");
						return outputList;
					}
					
					
					// if not on_ground => in some char inventory
					else 
					{		
						outputList.clear();
						outputList.add("NULL");
						return outputList;
					}
				}
				
				else 
				{
					System.out.println("case \"examine, explore, listen, goto\" error, at CreateStartCondition method");
					outputList.add("ERROR");
					return outputList;
				}
			

				 // Different from above >> must have item in inventory, instead of just being in the same location
			case "read":
			case "experiment":
			case "use":
				
			case "gather" :
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
			// VERY HARD TO SIMULATE, let just use this as temp
			//
			//
			//
			//
			//
			//
			//
			case "spy":
			case "stealth":
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
			// Item must be damaged if it's to be repaired?
				//?
				//?
				//?
				//?
				//?
				//?
				//?
			case "repair" :
				
					curItem = (Item) componentinput.getComponentObject();
					
					output = "1:";
					output += "PLAYER";
					output += ":listItem:" + curItem.getName();
					output += ":listProperty:" + "damaged";
					outputList.add(output);
					
					return outputList;

				
				
			// Target Must not has the Item that is about to be delivered
			case "give" :
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
				
				
			// LET CHANGE THIS TO JUST "Player_HasItem" >>> Exchange is just a 'action' for player to get item
			// Thus let simplify this by just [if player has X_Item from Y] and [Y no longer has that item]
			
			// Target Must  has the Item that is about to be delivered
			case "take" :
			case "exchange":
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				// Check that target nolonger has item
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
		
			default: 
				System.out.println(currentCompoentName);
				System.out.println("ERROR, illegal Component name");
				outputList.clear();
				outputList.add("ERROR");
				break;
		}	
		
		return outputList;
	
	}
	
	
	
	//This method create list of GoalCondition of the Component
	public static ArrayList<String> CreateGoalCondition(Component componentinput , GameWorld inputGameWorld) {

		
		ArrayList<String> outputList = new ArrayList<String>();
		String objectType = getTypeOfObject(componentinput);

		// 1 = info
		// 2 = loc
		// 3 = itself
		// 4 = Examine = empty >>> the Object also empty 
		int typeOfToken = componentinput.gettypeOfToken();

		// 1 = Character
		// 2 = Item
		// 3 = Location
		// 4 = NULL
		int typeOfObject = 0;

		
		switch (objectType) {
		
		case "Character":
			//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
						typeOfObject = 1;
						break;
						
		case "Item":
						typeOfObject = 2;
						break;
						
		case "Location":
						typeOfObject = 3;
						break;
						
		case "NULL":
						//if null >>>> do nothing
						typeOfObject = 4;
						break;
		
		default: 
			// T
			System.out.println("ERROR, INCORRECT OBJECT TYPE in CreateRestriction");
			break;
				
		
		}
		
		String tempInt = Integer.toString(typeOfToken);
		tempInt += Integer.toString(typeOfObject);
		
		
		
		// 11
		// Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12
		// Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13
		// Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		//
		// 21 
		// Loc >>> Loc of Item				= Get the Location of the Item, and condition = [player at that location]?
		// 22
		// Loc >>> Loc of Character			= Get the Location of the Character, and condition = [player at that location]?
		// 23
		// Loc >>> Loc of Location			= just [player same location]?
		//
		// 31
		// itself >>> itself of Item		= Depend on Component type (dmg, give, etc.)
		// 32
		// itself >>> itself of Character	= Depend on Component type (capture, free, etc.)
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		//
		//
		// IF(contain '4') == null and ignore >> go to next one.
		
		
		Object componentObjectONE = componentinput.getComponentObject();
		Object componentObjectSECOND = componentinput.getComponentObjectSecondary();
		
		
		// Int to store random number to select random object
		int randomNum;
		// Object to store ANY random object
		Object randomObject;
		// Character to store ANY(1) Random Character
		Character RandomCharacter1 = new Character();
		
		Item componentObject_1_ITEM = new Item();
		Character componentObject_1_Character = new Character();
		Location componentObject_1_Location = new Location();
		
		String output = "";
		String objectLocation = "";
		
		switch (tempInt)
		{
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		//
		// 11 = Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12 = Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13 = Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		case "11":
		case "12":
		case "13":
			
			// Select Random Character
			//
			boolean isPlayer =true;
			while(isPlayer) {
				randomNum = ThreadLocalRandom.current().nextInt(1, inputGameWorld.getListCharacter().size()+1);
				randomObject = inputGameWorld.getListCharacter().get(randomNum-1);
				RandomCharacter1 = (Character) randomObject;
				
				// Check if chosen character is player
				if (RandomCharacter1.isPlayer() == false) isPlayer = false; // If it is player, must select new character.
			}
			//
			// End select Random Character
			
			String RCLocation = RandomCharacter1.getLocation();
			//"CharNAME:currentLocation:market"
			output = "1:";
			output += "PLAYER";
			output += ":currentLocation:" + RCLocation;
			outputList.add(output);
			
			// The character must also be alive
			output = "1:";
			output += RandomCharacter1.getName();
			output += ":isAlive:" + "true";
			outputList.add(output);
			
			return outputList;
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT			
		// 21 
		// Loc >>> Loc of Character			= Get the Location of the Character, and condition = [player at that location]?
		case "21":
			
			componentObject_1_Character = (Character) componentObjectONE;
			objectLocation = componentObject_1_Character.getLocation();
			
			output = "1:";
			output += "PLAYER";
			output += ":currentLocation:" + objectLocation;
			outputList.add(output);
			return outputList;

			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 22
		// Loc >>> Loc of Item		= Get the Location of the Item, and condition = [player at that location]?		
		case "22":
			componentObject_1_ITEM = (Item) componentObjectONE;
			objectLocation = componentObject_1_ITEM.getCurrentLocation();
			output = "1:";
			output += "PLAYER";
			output += ":currentLocation:" + objectLocation;
			outputList.add(output);
			
			// The character must also be alive
			output = "1:";
			output += componentObject_1_Character.getName();
			output += ":isAlive:" + "true";
			outputList.add(output);
			
			return outputList;
		
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 23
		// Loc >>> Loc of Location		= just [player same location]?
			
		case "23":
			componentObject_1_Location = (Location) componentObjectONE;
			output = "1:";
			output += "PLAYER";
			output += ":currentLocation:" + componentObject_1_Location.getLocationName();
			outputList.add(output);
			return outputList;
			
			
			
		// 31
		// itself >>> itself of Character	= Depend on Component type (dmg, give, etc.)
		case "31":
			componentObject_1_Character = (Character) componentObjectONE;
			break;
			
			
			
		// 32
		// itself >>> itself of Item	= Depend on Component type (capture, free, etc.)
		case "32":
			componentObject_1_ITEM = (Item) componentObjectONE;
			break;
			
			
			
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		case "33":
			componentObject_1_Location = (Location) componentObjectONE;
			break;
			
		default: 
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		}
		
		
		
		String currentCompoentName = componentinput.getComponentName();
		Character curChar = new Character();
		Item curItem = new Item();
		
	////  ---------BELOW = switch for each component that group with how their token are turned into GOAL-----------
	////
		switch (currentCompoentName)
		{
			// Must make sure the target =  capture
			case "capture":
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatus:" + "Captured";
				outputList.add(output);
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
				
			case "free" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatusNOT:" + "Captured"; 
				outputList.add(output);
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
				
			// Must make sure the target object = damaged status
			case "damage" :
				

				// 1 == Character
				if (typeOfObject == 1)
				{
					curChar = (Character) componentinput.getComponentObject();
					output = "1:";
					output += curChar.getName();
					output += ":listStatus:" + "damaged";
					outputList.add(output);
					
					// Only damaged, but not kill
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					
					outputList.add(output);
					
					
					return outputList;
				}
				// 2 == Item
				else if (typeOfObject == 2)
				{
					curItem = (Item) componentinput.getComponentObject();

					//"LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"
					//"CharNAME:listItem:THE_ITEM_NAME:listProperty:luxury"
					//
					
					// If on_ground => on location
					if (curItem.isItemOnGround())
					{
						output = "2:";
						output += curItem.getCurrentLocation();
						output += ":listItemInLocation:" + curItem.getName();
						output += ":listProperty:" + "damaged";
						outputList.add(output);
						return outputList;
					}
					// if not on_ground => in some char inventory
					else 
					{
						
						output = "1:";
						output += curItem.getHolderName();
						output += ":listItem:" + curItem.getName();
						output += ":listProperty:" + "damaged";
						outputList.add(output);
						return outputList;
					}
				}
				else 
				{
					System.out.println("case \"damage\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
				
				
			case "kill" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "false";
				outputList.add(output);
				return outputList;
				
				
				
			case "defend" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				
				output = "1:";
				output += "PLAYER";
				output += ":currentLocation:" + curChar.getLocation();
				outputList.add(output);
				
				outputList.add(output);
				return outputList;
				
				
				//
				//
				// 
				// Let add new Condition that check player's stat (level, etc.) to simulate that player has to be this good to 
				// be able to protect the NPC from another NPC. (maybe add 2nd objective that simulate as enemy, player has to has
				// higher lvl or overall stat than the 2nd object???)
				//
				//
				
				
				
			case "escort" :
			case "follow" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";

				output = "1:";
				output += "PLAYER";
				output += ":currentLocation:" + curChar.getLocation();
				outputList.add(output);
				
				outputList.add(output);
				return outputList;
				
				
				
				
				
				// These = just be in same place as token's object = pass goal
			case "report":

				// For now, just let the character be alive, and condition that player = same place?
				
				
				// 1 == Character
				if (typeOfObject == 1)
				{
					curChar = (Character) componentinput.getComponentObject();
					output = "1:";
					output += "PLAYER";
					output += ":currentLocation:" + curChar.getLocation();
					outputList.add(output);
					
					
					// Only damaged, but not kill for report
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					outputList.add(output);
					
					return outputList;
				}
				
				else 
				{
					System.out.println("case \"damage\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
				
				
				// These = just be in same place as token's object = pass goal
			case "examine":
			case "explore":
			case "listen":
				
			case "goto":
				
				// For now, just let the character be alive, and condition that player = same place?
				
				
				// 1 == Character
				if (typeOfObject == 1)
				{
					curChar = (Character) componentinput.getComponentObject();
					output = "1:";
					output += "PLAYER";
					output += ":currentLocation:" + curChar.getLocation();
					outputList.add(output);
					
					
					return outputList;
				}
				
				// 2 == Item
				else if (typeOfObject == 2)
				{
					curItem = (Item) componentinput.getComponentObject();

					//"LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"
					//"CharNAME:listItem:THE_ITEM_NAME:listProperty:luxury"
					//
					// If on_ground => on location
					if (curItem.isItemOnGround())
					{
						output = "2:";
						output += "PLAYER";
						output += ":currentLocation:" + curItem.getCurrentLocation();
						outputList.add(output);
						
						return outputList;
					}
					
					
					// if not on_ground => in some char inventory
					else 
					{
						String itemHolderName = curItem.getHolderName();
						Character itemHolderCharacter = new Character();
						
						ArrayList<Character> listCharMGW =  inputGameWorld.getListCharacter();
						for (Character itrChar : listCharMGW)
						{
							if (itemHolderName == itrChar.getName())
							{
								itemHolderCharacter = itrChar;
							}
						}
						
						output = "1:";
						output += "PLAYER";
						output += ":currentLocation:" + itemHolderCharacter.getLocation();
						outputList.add(output);
						
						return outputList;
					}
				}
				
				else 
				{
					System.out.println("case \"examine, explore, listen, goto\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
			

				 // Different from above >> must have item in inventory, instead of just being in the same location
			case "read":
			case "experiment":
			case "use":
				
			case "gather" :
			case "take" :
				
				curItem = (Item) componentinput.getComponentObject();
				
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				return outputList;
				
				
			// VERY HARD TO SIMULATE, let just use this as temp
			//
			//
			//
			//
			//
			//
			//
			case "spy":
			case "stealth":
				
				curChar = (Character) componentinput.getComponentObject();
				
				output = "1:";
				output += curChar.getName();
				output += ":listStatus:" + "GOT_SNEAK";
				outputList.add(output);
				
				return outputList;
				
				
				
				
				
				
				
				
			case "repair" :
				
					curItem = (Item) componentinput.getComponentObject();
					
					output = "1:";
					output += "PLAYER";
					output += ":listItem:" + curItem.getName();
					output += ":listPropertyNOT:" + "damaged";
					outputList.add(output);
					
					return outputList;

				
				
			case "give" :
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
				
			// LET CHANGE THIS TO JUST "Player_HasItem" >>> Exchange is just a 'action' for player to get item
			// Thus let simplify this by just [if player has X_Item from Y] and [Y no longer has that item]
			case "exchange":
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				// Check that target nolonger has item
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				// Check that player has the item
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
			case "":
				System.out.println("KKKKK");
				outputList.clear();
				outputList.add("ERROR");
				break;
				
			default: 
				
				//curComponent.
				break;
		}	
		
		return outputList;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	//This method create list of ResolvedCondition
	public static ArrayList<String> CreateResolvedState_Contain(Component componentinput , GameWorld inputGameWorld) {

		
		ArrayList<String> outputList = new ArrayList<String>();
		String objectType = getTypeOfObject(componentinput);

		// 1 = info
		// 2 = loc
		// 3 = itself
		// 4 = Examine = empty >>> the Object also empty 
		int typeOfToken = componentinput.gettypeOfToken();

		// 1 = Character
		// 2 = Item
		// 3 = Location
		// 4 = NULL
		int typeOfObject = 0;

		
		switch (objectType) {
		
		case "Character":
			//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
						typeOfObject = 1;
						break;
						
		case "Item":
						typeOfObject = 2;
						break;
						
		case "Location":
						typeOfObject = 3;
						break;
						
		case "NULL":
						//if null >>>> do nothing
						typeOfObject = 4;
						break;
		
		default: 
			// T
			System.out.println("ERROR, INCORRECT OBJECT TYPE in CreateRestriction");
			break;
				
		
		}
		
		String tempInt = Integer.toString(typeOfToken);
		tempInt += Integer.toString(typeOfObject);
		
		
		
		// 11
		// Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12
		// Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13
		// Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		//
		// 21 
		// Loc >>> Loc of Item				= Get the Location of the Item, and condition = [player at that location]?
		// 22
		// Loc >>> Loc of Character			= Get the Location of the Character, and condition = [player at that location]?
		// 23
		// Loc >>> Loc of Location			= just [player same location]?
		//
		// 31
		// itself >>> itself of Item		= Depend on Component type (dmg, give, etc.)
		// 32
		// itself >>> itself of Character	= Depend on Component type (capture, free, etc.)
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		//
		//
		// IF(contain '4') == null and ignore >> go to next one.
		
		
		Object componentObjectONE = componentinput.getComponentObject();
		Object componentObjectSECOND = componentinput.getComponentObjectSecondary();
		
		
		// Int to store random number to select random object
		int randomNum;
		// Object to store ANY random object
		Object randomObject;
		// Character to store ANY(1) Random Character
		Character RandomCharacter1 = new Character();
		
		Item componentObject_1_ITEM = new Item();
		Character componentObject_1_Character = new Character();
		Location componentObject_1_Location = new Location();
		
		String output = "";
		String strLocation = "";
		
		switch (tempInt)
		{
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		//
		// 11 = Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12 = Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13 = Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		case "11":
		case "12":
		case "13":
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT			
		// 21 
		// Loc >>> Loc of Item			= Get the Location of the Item, and condition = [player at that location]?
		case "21":
			
			outputList.clear();
			outputList.add("NULL");
			return outputList;

			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 22
		// Loc >>> Loc of Character		= Get the Location of the Character, and condition = [player at that location]?		
		case "22":
			outputList.clear();
			outputList.add("NULL");
			return outputList;
		
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 23
		// Loc >>> Loc of Location		= just [player same location]?
			
		case "23":
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
			
			
		// 31
		// itself >>> itself of Character	= Depend on Component type (dmg, give, etc.)
		case "31":
			componentObject_1_Character = (Character) componentObjectONE;
			break;
			
			
			
		// 32
		// itself >>> itself of Item	= Depend on Component type (capture, free, etc.)
		case "32":
			componentObject_1_ITEM = (Item) componentObjectONE;
			break;
			
			
			
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		case "33":
			componentObject_1_Location = (Location) componentObjectONE;
			break;
			
		default: 
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		}
		
		
		
		String currentCompoentName = componentinput.getComponentName();
		Character curChar = new Character();
		Item curItem = new Item();
		
	////  ---------BELOW = switch for each component that group with how their token are turned into GOAL-----------
	////
		switch (currentCompoentName)
		{
			// Must make sure the target =  capture
			case "capture":
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatusNOT:" + "Captured";
				outputList.add(output);
				
				return outputList;
				
				
			case "free" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatus:" + "Captured"; 
				outputList.add(output);

				
				return outputList;
				
				
			// Must make sure the target object = damaged status
			case "damage" :
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
			case "kill" :
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
			case "defend" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
				//
				//
				// 
				// Let add new Condition that check player's stat (level, etc.) to simulate that player has to be this good to 
				// be able to protect the NPC from another NPC. (maybe add 2nd objective that simulate as enemy, player has to has
				// higher lvl or overall stat than the 2nd object???)
				//
				//
				
				
				
			case "escort" :
			case "follow" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
				
				
				
				// These = just be in same place as token's object = pass goal
			case "report":

				// For now, just let the character be alive, and condition that player = same place?
				
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
				// These = just be in same place as token's object = pass goal
			case "examine":
			case "explore":
			case "listen":
				
			case "goto":
				
				// For now, just let the character be alive, and condition that player = same place?
				outputList.clear();
				outputList.add("NULL");
				return outputList;
			

				 // Different from above >> must have item in inventory, instead of just being in the same location
			case "read":
			case "experiment":
			case "use":
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
			case "gather" :
				
				
				curItem = (Item) componentinput.getComponentObject();
				
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				return outputList;
				
				
			case "take" :
				
				curItem = (Item) componentinput.getComponentObject();
				

				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				return outputList;
				
				
			// VERY HARD TO SIMULATE, let just use this as temp
			//
			//
			//
			//
			//
			//
			//
			case "spy":
			case "stealth":
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
				
			case "repair" :
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				

				
				
			case "give" :
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				//PLAYER:listItem:curItem.getName():itemName:curItem.getName()
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
				
			// LET CHANGE THIS TO JUST "Player_HasItem" >>> Exchange is just a 'action' for player to get item
			// Thus let simplify this by just [if player has X_Item from Y] and [Y no longer has that item]
			case "exchange":
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				

				// Check that player has the item
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
			case "":
				System.out.println("KKKKK");
				outputList.clear();
				outputList.add("ERROR");
				break;
				
			default: 
				
				System.out.println(currentCompoentName);
				System.out.println("ERROR, illegal Component name");
				outputList.clear();
				outputList.add("ERROR");
				break;
		}	
		
		return outputList;
	
	}
	
	
	
	public static ArrayList<String> CreateRestriction(Component componentinput , GameWorld inputGameWorld) {

		
		ArrayList<String> outputList = new ArrayList<String>();
		String objectType = getTypeOfObject(componentinput);

		// 1 = info
		// 2 = loc
		// 3 = itself
		// 4 = Examine = empty >>> the Object also empty 
		int typeOfToken = componentinput.gettypeOfToken();

		// 1 = Character
		// 2 = Item
		// 3 = Location
		// 4 = NULL
		int typeOfObject = 0;

		
		switch (objectType) {
		
		case "Character":
			//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
						typeOfObject = 1;
						break;
						
		case "Item":
						typeOfObject = 2;
						break;
						
		case "Location":
						typeOfObject = 3;
						break;
						
		case "NULL":
						//if null >>>> do nothing
						typeOfObject = 4;
						break;
		
		default: 
			// T
			System.out.println("ERROR, INCORRECT OBJECT TYPE in CreateRestriction");
			break;
				
		
		}
		
		String tempInt = Integer.toString(typeOfToken);
		tempInt += Integer.toString(typeOfObject);
		
		
		
		// 11
		// Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12
		// Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13
		// Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		//
		// 21 
		// Loc >>> Loc of Item				= Get the Location of the Item, and condition = [player at that location]?
		// 22
		// Loc >>> Loc of Character			= Get the Location of the Character, and condition = [player at that location]?
		// 23
		// Loc >>> Loc of Location			= just [player same location]?
		//
		// 31
		// itself >>> itself of Item		= Depend on Component type (dmg, give, etc.)
		// 32
		// itself >>> itself of Character	= Depend on Component type (capture, free, etc.)
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		//
		//
		// IF(contain '4') == null and ignore >> go to next one.
		
		
		Object componentObjectONE = componentinput.getComponentObject();
		Object componentObjectSECOND = componentinput.getComponentObjectSecondary();
		
		
		// Int to store random number to select random object
		int randomNum;
		// Object to store ANY random object
		Object randomObject;
		// Character to store ANY(1) Random Character
		Character RandomCharacter1 = new Character();
		
		Item componentObject_1_ITEM = new Item();
		Character componentObject_1_Character = new Character();
		Location componentObject_1_Location = new Location();
		
		String output = "";
		String objectLocation = "";
		
		switch (tempInt)
		{
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		//
		// 11 = Info >>> Info of Item      		= Select Random NPC? and condition = [player at same NPC location]?
		// 12 = Info >>> Info of Character		= Select Random NPC? and condition = [player at same NPC location]?
		// 13 = Info >>> Info of Location		= Select Random NPC? and condition = [player at same NPC location]?
		case "11":
		case "12":
		case "13":
			
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT			
		// 21 
		// Loc >>> Loc of Item			= Get the Location of the Item, and condition = [player at that location]?
		case "21":
			
			outputList.clear();
			outputList.add("NULL");
			return outputList;

			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 22
		// Loc >>> Loc of Character		= Get the Location of the Character, and condition = [player at that location]?		
		case "22":
			outputList.clear();
			outputList.add("NULL");
			return outputList;
		
			
		// NOTE THIS WILL RETURN RESTRICTION STRING AT THIS STEP, WILL NOT GO BELOW TO SPECIFIC COMPONENT
		// 23
		// Loc >>> Loc of Location		= just [player same location]?
			
		case "23":
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
			
			
		// 31
		// itself >>> itself of Character	= Depend on Component type (dmg, give, etc.)
		case "31":
			componentObject_1_Character = (Character) componentObjectONE;
			break;
			
			
			
		// 32
		// itself >>> itself of Item	= Depend on Component type (capture, free, etc.)
		case "32":
			componentObject_1_ITEM = (Item) componentObjectONE;
			break;
			
			
			
		// 33
		// itself >>> itself of Location	= Depend on Component type?????
		case "33":
			componentObject_1_Location = (Location) componentObjectONE;
			break;
			
		default: 
			outputList.clear();
			outputList.add("NULL");
			return outputList;
			
		}
		
		
		
		String currentCompoentName = componentinput.getComponentName();
		Character curChar = new Character();
		Item curItem = new Item();
		
	////  ---------BELOW = switch for each component that group with how their token are turned into GOAL-----------
	////
		switch (currentCompoentName)
		{
			// Must make sure the target =  capture
			case "capture":
				
				
				//curChar:isAlive:True
				//curChar:listStatusNOT:captured
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatusNOT:" + "Captured";
				outputList.add(output);
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
				
			case "free" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":listStatus:" + "Captured"; 
				outputList.add(output);
				
				// The character must also be alive
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				
				return outputList;
				
				
			// Must make sure the target object = damaged status
			case "damage" :

				
				// 1 == Character
				if (typeOfObject == 1)
				{
					curChar = (Character) componentinput.getComponentObject();
					output = "1:";
					output += curChar.getName();
					output += ":listStatusNOT:" + "damaged";
					outputList.add(output);
					
					// Only damaged, but not kill
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					
					outputList.add(output);
					
					return outputList;
				}
				
				
				// 2 == Item
				else if (typeOfObject == 2)
				{
					curItem = (Item) componentinput.getComponentObject();
					
					// If on_ground => on location
					if (curItem.isItemOnGround())
					{
						output = "2:";
						output += curItem.getCurrentLocation();
						output += ":listItemInLocation:" + curItem.getName();
						output += ":listPropertyNOT:" + "damaged";
						outputList.add(output);
						return outputList;
					}
					
					
					// if not on_ground => in some char inventory
					else 
					{
						output = "1:";
						output += curItem.getHolderName();
						output += ":listItem:" + curItem.getName();
						output += ":listPropertyNOT:" + "damaged";
						outputList.add(output);
						return outputList;
					}
				}
				else 
				{
					System.out.println("case \"damage\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
				
				
			case "kill" :
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				outputList.add(output);
				return outputList;
				
				
				
			case "defend" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				
				outputList.add(output);
				return outputList;
				
				
				//
				//
				// 
				// Let add new Condition that check player's stat (level, etc.) to simulate that player has to be this good to 
				// be able to protect the NPC from another NPC. (maybe add 2nd objective that simulate as enemy, player has to has
				// higher lvl or overall stat than the 2nd object???)
				//
				//
				
				
				
			case "escort" :
			case "follow" :
				
				// For now, just let the character be alive, and condition that player = same place?
				
				curChar = (Character) componentinput.getComponentObject();
				output = "1:";
				output += curChar.getName();
				output += ":isAlive:" + "true";
				
				outputList.add(output);
				return outputList;
				
				
				
				
				
				// These = just be in same place as token's object = pass goal
			case "report":

				// For now, just let the character be alive, and condition that player = same place?
				
				
				// 1 == Character
				if (typeOfObject == 1)
				{
					
					output = "1:";
					output += curChar.getName();
					output += ":isAlive:" + "true";
					outputList.add(output);
					
					return outputList;
				}
				
				else 
				{
					System.out.println("case \"report\" error, at CreateRestriction method");
					outputList.add("ERROR");
					return outputList;
				}
				
				
				// These = just be in same place as token's object = pass goal
			case "examine":
			case "explore":
			case "listen":
			case "goto":
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
			

				 // Different from above >> must have item in inventory, instead of just being in the same location
			case "read":
			case "experiment":
			case "use":
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
			case "gather" :

				curItem = (Item) componentinput.getComponentObject();
				
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				return outputList;
				
			
			case "take" :
				
				
				//PLAYER:listItem:curItem.getName():itemNameNOT:curItem.getName()
				
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				//curChar.getName():listItem:curItem.getName():itemName:curItem.getName()
				
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				
				outputList.add(output);
				return outputList;
				
			// VERY HARD TO SIMULATE, let just use this as temp
			//
			//
			//
			//
			//
			//
			//
			case "spy":
			case "stealth":
				
				outputList.clear();
				outputList.add("NULL");
				return outputList;
				
				
				
			case "repair" :

				outputList.clear();
				outputList.add("NULL");
				return outputList;

				
				
			case "give" :
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				
				
				//curChar.getName():listItem:curItem.getName():itemNameNOT:curItem.getName()
			
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				//PLAYER:listItem:curItem.getName():itemName:curItem.getName()
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
				
			// LET CHANGE THIS TO JUST "Player_HasItem" >>> Exchange is just a 'action' for player to get item
			// Thus let simplify this by just [if player has X_Item from Y] and [Y no longer has that item]
			case "exchange":
				
				curItem = (Item) componentinput.getComponentObject();
				curChar = (Character) componentinput.getComponentObjectSecondary();
				

				// Check that target nolonger has item
				output = "1:";
				output += curChar.getName();
				output += ":listItem:" + curItem.getName();
				output += ":itemName:" + curItem.getName();
				outputList.add(output);
				
				// Check that player has the item
				output = "1:";
				output += "PLAYER";
				output += ":listItem:" + curItem.getName();
				output += ":itemNameNOT:" + curItem.getName();
				outputList.add(output);
				
				return outputList;
				
				
			case "":
				System.out.println("KKKKK");
				outputList.clear();
				outputList.add("ERROR");
				break;
				
			default: 
				
				//curComponent.
				break;
		}	
		
		return outputList;
	
	}
	
	
	// Only merge input GameState(goal) + GameState(Restriction of the level)
	public static GameState createFullConditionState(GameState restrictionState,Component input)
	{
		GameState output = new GameState();
		GameState goalConditionInput = new GameState(input.getGoalGameState());
		
		int inputComponentLevel = input.getComponentLevel();
		
		//ArrayList<GameCondition> list_RSGC_Chararacter = restrictionState.getListGameConditionCharacter();
		//ArrayList<GameCondition> list_RSGC_Location = restrictionState.getListGameConditionLocation();
		//ArrayList<GameCondition> list_RSGC_Relationship = restrictionState.getListGameConditionRelationship();

		ArrayList<GameCondition> list_RSGC_Chararacter = new ArrayList<GameCondition>();
		for (int x = 0; x < restrictionState.getListGameConditionCharacter().size(); x++) {
			GameCondition addGC = restrictionState.getListGameConditionCharacter().get(x);
			GameCondition newGC = new GameCondition(addGC);
			list_RSGC_Chararacter.add(newGC);
		}
				
		ArrayList<GameCondition> list_RSGC_Location = new ArrayList<GameCondition>();
		for (int x = 0; x < restrictionState.getListGameConditionLocation().size(); x++) {
			GameCondition addGC = restrictionState.getListGameConditionLocation().get(x);
			GameCondition newGC = new GameCondition(addGC);
			list_RSGC_Location.add(newGC);
		}
		
		ArrayList<GameCondition> list_RSGC_Relationship = new ArrayList<GameCondition>();
		for (int x = 0; x < restrictionState.getListGameConditionLocation().size(); x++) {
			GameCondition addGC = restrictionState.getListGameConditionLocation().get(x);
			GameCondition newGC = new GameCondition(addGC);
			list_RSGC_Relationship.add(newGC);
		}

		
		//NEW
		ArrayList<GameCondition> newList_RSGC_Chararacter = new ArrayList<GameCondition>();
		for (int x = 0; x < goalConditionInput.getListGameConditionCharacter().size(); x++) {
			GameCondition addGC = goalConditionInput.getListGameConditionCharacter().get(x);
			GameCondition newGC = new GameCondition(addGC);
			newList_RSGC_Chararacter.add(newGC);
		}
		
		ArrayList<GameCondition> newList_RSGC_Location = new ArrayList<GameCondition>();
		for (int x = 0; x < goalConditionInput.getListGameConditionLocation().size(); x++) {
			GameCondition addGC = goalConditionInput.getListGameConditionLocation().get(x);
			GameCondition newGC = new GameCondition(addGC);
			newList_RSGC_Location.add(newGC);
		}
		
		ArrayList<GameCondition> newList_RSGC_Relationship = new ArrayList<GameCondition>();
		for (int x = 0; x < goalConditionInput.getListGameConditionLocation().size(); x++) {
			GameCondition addGC = goalConditionInput.getListGameConditionLocation().get(x);
			GameCondition newGC = new GameCondition(addGC);
			newList_RSGC_Relationship.add(newGC);
		}
	
		
		//---------------------ADD GC to new List-----------------------------------
	
		for (int x = 0; x < list_RSGC_Chararacter.size() ; x ++) {
			GameCondition GC = list_RSGC_Chararacter.get(x);
			GameCondition newGC = new GameCondition(GC);
			
			if ( newGC.getComponentLevel() <= inputComponentLevel){
				//If component level lower or equal, do not add to newList;
				continue;
			}
			else {
				newList_RSGC_Chararacter.add(newGC);
			}
		}
		
		for (GameCondition GC : list_RSGC_Location){
			GameCondition newGC = new GameCondition(GC);
			
			if ( newGC.getComponentLevel() <= inputComponentLevel){
				//If component level lower or equal, do not add to newList;
				continue;
			}
			else {
				newList_RSGC_Location.add(newGC);
			}
		}
		
		for (GameCondition GC : list_RSGC_Relationship){
			GameCondition newGC = new GameCondition(GC);
			
			if ( newGC.getComponentLevel() <= inputComponentLevel){
				//If component level lower or equal, do not add to newList;
				continue;
			}
			else {
				newList_RSGC_Relationship.add(newGC);
			}
		}
		
		//---------------------ADD GC to new List END-----------------------------------
		
		
		//---------------------ADD GC to GameState--------------------------------------
		boolean isNOTConflict = true;
		for (GameCondition GCnow : newList_RSGC_Chararacter) {
			if (!output.addDesireConflictCheck(GCnow))
			{
				isNOTConflict = false;
			}
		}
		for (GameCondition GCnow : newList_RSGC_Location) {
			if (!output.addDesireConflictCheck(GCnow))
			{
				isNOTConflict = false;
			}
		}
		for (GameCondition GCnow : newList_RSGC_Relationship) {
			if (!output.addDesireConflictCheck(GCnow))
			{
				isNOTConflict = false;
			}
		}
		
		if (isNOTConflict == false)
		{
			output = new GameState(false);
			return output;
		}
		
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ---------------------------Utility of Utility-----------------------------------

	
	public static String createNameFromDesire(String inputSTR)
	{
		String variable = inputSTR.substring(0, inputSTR.indexOf(":"));
		return variable;
	}
	
	
	public static GameCondition CreateGameConditionFromDesire(String StrIN, int componentLevel)
	{
		String typeOfObject = QuestGeneratorUtility.getTypeofObjectFromDesire(StrIN);
		String desire = QuestGeneratorUtility.getDesire_CutType(StrIN);
		GameCondition GC = new GameCondition(desire, componentLevel,typeOfObject);
		return GC;
	}
	
	
	
	public static String getDesire_CutType(String inputSTR)
	
	{
		String variable = inputSTR.substring(0, inputSTR.indexOf(":"));
		String desireValue = inputSTR.substring(inputSTR.indexOf(":")+1,inputSTR.length());
		return desireValue;
	}
	
	
	public static String getTypeofObjectFromDesire(String inputSTR)
	
	{
		System.out.println(inputSTR);
		String variable = inputSTR.substring(0, inputSTR.indexOf(":"));
		String desireValue = inputSTR.substring(inputSTR.indexOf(":")+1,inputSTR.length());
		
		switch (variable)
		{
		// If [Character] or [Item in character inventory]
		case "1":
			return "Character";
		// If [Location] or [Item in Location on ground]
		case "2":
			return "Location";
		case "3":
			return "Location";
		case "4":
			return "Relationship";
		
		
			
			default:
				return "ERROR, typeOfObjectNumberIllgeal";
		}
	}

	
	//Return type of Component's object as string
	public static String getTypeOfObject(Component componentinput) {
		
		String output = "";
		
		
		// ---------------get type of 1st object------------------------
		//
		if (componentinput.getComponentObject() instanceof Character) {
			output = "Character";
		}
		else if (componentinput.getComponentObject() instanceof Item) {
			output = "Item";
		}
		else if (componentinput.getComponentObject() instanceof Location) {
			output = "Location";
		}
		else if (componentinput.getComponentObject() == null) {
			output = "NULL";
		}
		else {
			System.out.println("ERROR AT: QuestGenerator>>>getTypeOfObject method");
			System.out.println("Illegal type of object");
			
		}
		
		// NOTE: 2nd object == always Character
		// NOTE: 3rd object == always Item
		
		return output;
	}
	
	
	
	
	
	
	
	
	
	
}
