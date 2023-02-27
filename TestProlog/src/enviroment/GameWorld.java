package enviroment;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Relationship;
import gameObject.Skill;
import gameObject.Status;


public class GameWorld {
	
	String gameName;
	
	// List of CURRENT ACTIVE OBJECT in CURRENT GAME WORLD
	ArrayList<Relationship> listRelationship = new ArrayList<Relationship>();
	ArrayList<Location> listLocation = new ArrayList<Location>();
	ArrayList<Character> listCharacter = new ArrayList<Character>();  // Now listCharacter is within listLocation
	
	//Have to be implemented to each character
	int factionRelation;
	int factionRelation2;
	int factionRelation3;
	
	//Have to be implemented to each character
	int Restriction_To_Do_Certain_Action;   //From debuff?
	int Restruction_To_Do_Certain_Action2;  //From faction relation penelty?
	
	
	//////////////// Constructor //////////////
	
	public GameWorld(){
		
	}
	
	public GameWorld(String name){
		this.gameName = name;
	}
	
	public GameWorld(String gameName, ArrayList<Relationship> newListRelationship, ArrayList<Character> newListCharacter, ArrayList<Location> newListLocation) 
	{
		this.gameName = gameName;
		listRelationship = newListRelationship;
		listCharacter = newListCharacter;
		listLocation = newListLocation;
	}
	
	
	
	//////////////////Reset / basic setup for new GameWorld /////////////
	
	
	public void setBasicNewGameWorld()
	{
		listCharacter = new ArrayList<Character>();
		listRelationship = new ArrayList<Relationship>();
		listLocation = new ArrayList<Location>();
		
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
		
		
		//Item
		Item newItem = new Item();
		
		String[] type =		new String[]	{"luxury"};
		String[] function = new String[]	{"object"};	
		String[] property = new String[]	{""};
		newItem.setNewItem(900101,"diamond", type, function, "null", property, 2, true);
		newLocationPalace.addOrUpdateItem(newItem);
		
		type =	 	new String[]	{"weapon"};
		function =  new String[]	{"equipment"};	
		property =  new String[]	{"weapon"};
		newItem = new Item();
		newItem.setNewItem(500101,"dagger", type, function, "null", property, 1, false);		
		newLocationJail.addOrUpdateItem(newItem);
		
		
		type =		new String[]	{"quest"};
		function = new String[]	{"object"};	
		property = new String[]	{""};
		newItem.setNewItem(900201,"treasure_map", type, function, "null", property, 2, true);
		newLocationDungeon.addOrUpdateItem(newItem);

		
		////// Add very generic item (10+ items of same name)
		////// These item start ID at 100000
		
		//Add 1 berry
		//100000
		newItem = new Item();
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"berry"};
		newItem.setNewItem(100101,"berry", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		
		/* REMOVE 19-2-2019 to increase performance
		
		newItem = new Item();
		newItem.setNewItem(100102,"berry", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		newItem = new Item();
		newItem.setNewItem(100102,"berry", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		newItem = new Item();
		newItem.setNewItem(100103,"berry", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		
		*/

		
		//Add 2 poison_plant
		//101000
		newItem = new Item();
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"poison"};
		newItem.setNewItem(100201,"poison_plant", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		newItem = new Item();
		newItem.setNewItem(100202,"poison_plant", type, function, "null", property, 0, false);
		newLocationForest.addOrUpdateItem(newItem);
		newItem = new Item();

		//player
		Character newChar = new Character("player", 1, true,"city", 0, false);
		newChar.setIsPlayer(true);
		listCharacter.add(newChar);
		
		//UNIQUE NPC
		newChar = new Character("mob_NPC_1", 15, true,"city", 0, false);
		listCharacter.add(newChar);

		
		newChar = new Character("king", 20, true,"palace", 3, true);
		newChar.addOccupation("king");
		newChar.addOccupation("noble");
		newChar.addStatus("rich");
		
		listCharacter.add(newChar);


		//Mob character
		newChar = new Character("soldier1", 20, true,"city", 2, true);
		newChar.addOccupation("soldier");
		listCharacter.add(newChar);
		
		// REMOVE to improve performance
		/*
		newChar = new Character("soldier2", 20, true,"jail", 2, true);
		newChar.addOccupation("soldier");
		listCharacter.add(newChar);
		*/
		
		
		newChar = new Character("soldier3", 20, true,"palace", 2, true);
		newChar.addOccupation("soldier");
		listCharacter.add(newChar);
		
		//listLocation.add(newLocationCity);
		//listLocation.add(newLocationDungeon);
		//listLocation.add(newLocationForest);
		//listLocation.add(newLocationJail);
		//listLocation.add(newLocationPalace);
		
		newChar = new Character("doctor1", 20, true,"city", 3, true);
		newChar.addSkill("heal");
		newChar.addOccupation("doctor");
		listCharacter.add(newChar);	
		newChar = new Character("blacksmith1", 20, true,"city", 2, true);
		newChar.addOccupation("blacksmith");
		listCharacter.add(newChar);
		
		
		newChar = new Character("thief1", 20, true,"jail", 2, true);
		newChar.addOccupation("thief");
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"unlock"};
		newItem = new Item();
		newItem.setNewItem(300101,"lockpick", type, function, "thief1", property, 1, false);
		newChar.addOrUpdateItem(newItem);
		listCharacter.add(newChar);
		
		// Remove to improve performance
		/*
		newChar = new Character("messenger1", 20, true,"city", 2, true);
		newChar.addOccupation("messenger");
		listCharacter.add(newChar);
		*/
		
		newChar = new Character("miner1", 20, true,"dungeon", 1, true);
		newChar.addOccupation("miner");
		listCharacter.add(newChar);
		newChar = new Character("lumberjack1", 20, true,"forest", 1, true);
		newChar.addOccupation("lumberjack");
		listCharacter.add(newChar);
		
		/* REMOVE 19-2-2019 to increase performance
		
		newChar = new Character("scout1", 20, true,"forest", 2, true);
		newChar.addOccupation("scout");
		listCharacter.add(newChar);
		newChar = new Character("farmer1", 20, true,"forest", 1, true);
		newChar.addOccupation("farmer");
		listCharacter.add(newChar);
		
		*/
		
		
		newChar = new Character("merchant1", 20, true,"city", 3, true);
		newChar.addOccupation("merchant");
		newChar.addStatus("rich");
		
		// Merchant Item
		//NPC item start at 50000
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"poison"};
		newItem = new Item();
		newItem.setNewItem(200101,"potion_poison", type, function, "merchant1", property, 1, false);
		newChar.addOrUpdateItem(newItem);
		
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"healing"};
		newItem = new Item();
		newItem.setNewItem(200201,"potion_heal", type, function, "merchant1", property, 1, false);
		newChar.addOrUpdateItem(newItem);
		
		type =	 	new String[]	{"supply"};
		function =  new String[]	{"consumable"};	
		property =  new String[]	{"cure_poison"};
		newItem = new Item();
		newItem.setNewItem(200301,"antidote", type, function, "merchant1", property, 1, false);	
		newChar.addOrUpdateItem(newItem);
		
		
		//add merchant to List
		listCharacter.add(newChar);
		
		
		
		//Relation
		Relationship newRelation = new Relationship();
		newRelation.setRelationship("merchant1", "soldier1", "friend");
		listRelationship.add(newRelation);
		
		//
	
	}
	
	
	//////////////////Reset / basic setup for new GameWorld ///////////////////////
	
	
	
	
	
	
	///////////////Below = methods for checking GameState (start state, goal state, etc.)/////////////////////////////
	

	// Return True   IF  gameState is satisfy (Component goal reach / start state reach) 
	// Return False  IF  gameState is not satisfy (Component is yet to be completed, quest still not possible to start)
	public boolean gameStateCheck(GameState inputGameState)
	{
		boolean isGameStateSatisfy = true;
		
		
		ArrayList<String> tempListCharacter = inputGameState.getListStringCharacter();
		ArrayList<String> tempListLocation = inputGameState.getListStringLocation();
		ArrayList<String> tempListRelationship = inputGameState.getListStringRelationship();
		
		//COMMENT 6-12-2018 This is comment out since All Item are now in either Character or in Location
		//ArrayList<String> tempListItem = inputGameState.getListStringItem();

		
		// IF even one of the conditions isn't met, it will set the return value to false;
		/////////////////////////////////////
		boolean isContainCharater = containCharacter(tempListCharacter);
		if(!isContainCharater)
		{
			isGameStateSatisfy = false;
		}
		
		boolean isContainLocation = containLocation(tempListLocation);
		if(!isContainLocation)
		{
			isGameStateSatisfy = false;
		}
		
		// OBSOLETE >> now All item must be in character's inventory or on Location ONLY
		//             AKA >> even to check if item exist, must do through check character & Location
		/*
		boolean isContainItem = ;
		if(!isContainItem)
		{
			isGameStateSatisfy = false;
		}
		
		boolean isContainRelationship = ;
		if(!isContainRelationship)
		{
			isGameStateSatisfy = false;
		}
		*/
		////////////////////////////////////
		
		
		return isGameStateSatisfy;
	}
	
	//Only care if the specific artibute within the desire list are met, all non-mention are ignored
	//
	//RETURN TRUE   IF   the current GameState contain ALL characters that satisfy this 'inputList'
	//RETURN FALSE  IF
	public boolean containCharacter(ArrayList<String> inputList)
	{
		
		String variable;
		String desireValue;
		
		//Character currentChar = new Character();  //15-2-2019
		Character currentChar = null;
		
		
		for (String currentInput : inputList)
		{
			variable = currentInput.substring(0, currentInput.indexOf(":"));
			desireValue = currentInput.substring(currentInput.indexOf(":")+1,currentInput.length());
			
		
			//Looking for the character index
			int indexChar = 0;
			for (int x = 0; x<listCharacter.size();x++)
			{
				if (listCharacter.get(x).getName() == variable) 
					currentChar = listCharacter.get(x);
					indexChar = x;
			}
			
			// IF This is true, it means the character doesn't exist
			if (indexChar == 0)
			{
				System.out.println("Character in GameState's wishlist doesn't exist");
				return false;
			}
			
			//Now consult siwtchCheckCharacter
			//IF even one of them return false, it mean one of condition isn't met
			if (!siwtchCheckCharacter(currentChar, desireValue))
			{
				return false;
			}
			
		}
		return true;
	}
	
	
	
	
	/*  OLD CODE
	 * 
	 * 

	
	public boolean containCharacter(ArrayList<String> inputList)
	{

		ArrayList<String> inputListDesire = new ArrayList<String>();
		boolean returnBool = true;
		
		for (Character inputCharacter : inputList)
		{
			inputListDesire = inputCharacter.getlistDesire();
			String characterName = inputCharacter.getName();
			
			//Looking for the character index
			int indexChar = 0;
			Character currentChar = new Character();
			for (int x = 0; x<listCharacter.size();x++)
			{
				if (listCharacter.get(x).getName() == characterName) 
					currentChar = listCharacter.get(x);
			}

			//Check if all 'desired' attributes of the character are met
			//IF even one is not met, this will return FALSE (the desired game state is not yet met)
			for (String stringDesire : inputListDesire)
			{
				if (!siwtchCheckCharacter(currentChar,stringDesire))
				{
					returnBool = false;
				}
				
			}
		}
		return returnBool;
	}
	
	*
	*/
	

	//RETURN TRUE   IF  there is character that satisfy the 'desire'
	//RETURN FALSE  IF  there is NO character that satisfy the 'desire'
	public boolean siwtchCheckCharacter(Character currentChar,String stringDesire)
	{
		String variable = stringDesire.substring(0, stringDesire.indexOf(":"));
		String desireValue = stringDesire.substring(stringDesire.indexOf(":")+1,stringDesire.length());
		
		
		switch (variable)
			{
			case ("isAlive")
				: 
					//Turn boolean isAlive into String to be compatible to desireValue;
					String curIsAlive = Boolean.toString(currentChar.getIsAlive());
					if (curIsAlive != desireValue) 
						{
						return false;
						}
					break;
					
			case ("currentLocation")
				: 
					for (Character curChar : listCharacter)
					{
						if (currentChar.getLocation() != stringDesire)
						{
							return false; 
						}
					}
					break;
					
			case ("sameLocation")
			: 
				Character CharSameLocation = null;
				for (Character checkChar : listCharacter )
				{
					if (checkChar.getName() == stringDesire)
					{
						CharSameLocation = checkChar;
					}
				}
				if (CharSameLocation == null) return false;
				
				if (currentChar.getLocation() != CharSameLocation.getLocation())
				{
					return false; 
				}
				break;
				
					
			// True if contain the 'desireValue'
			case ("listStatus")
				: 
					if(!currentChar.getListStatus().contains(desireValue))
					{
						return false;
					}
					break;
			
			// True if NOT contain the 'desireValue'		
			case ("listStatusNOT")
			: 
				if(currentChar.getListStatus().contains(desireValue))
				{
					return false;
				}
				break;
					
			case ("listSkill")
			: 
				if(!currentChar.getListSkill().contains(desireValue)) 
					{
					return false;
					}
				break;
				
			case ("listSkillNOT")
			: 
				if(currentChar.getListSkill().contains(desireValue)) 
					{
					return false;
					}
				break;
			
			case ("listOccupation")
			: 
				if(!currentChar.getListOccupation().contains(desireValue))
				{
					return false;
				}
				break;
	
				
			//
			// Since item also has its own list, this case are different from others.
			//
			//
			case ("listItem")
				: 
					if(!checkListItem(currentChar.getListItem(),desireValue))
					{
						return false;
					}
					break;
			
			// In case that variable doesn't exist, it should mean that misspelling happen
			default : 
				System.out.println("containCharacter's variable does not exist, FROM enviroment.GameWorld.containCharacter");
				return false;
			
			}
		// IF none of the switch's RETURN got triggered (to return false)
		// It means that the characters satisfy the conditions; thus return true;
		return true;
	}

	public boolean containLocation(ArrayList<String> inputList)
	{
		String variable;
		String desireValue;
		
		Location currentLo = new Location();
		
		for (String currentInput : inputList)
		{
			variable = currentInput.substring(0, currentInput.indexOf(":"));
			desireValue = currentInput.substring(currentInput.indexOf(":")+1,currentInput.length());
			
		
			//Looking for the character index
			int indexChar = 0;
			for (int x = 0; x<listLocation.size();x++)
			{
				if (listLocation.get(x).getLocationName() == variable) 
					currentLo = listLocation.get(x);
					indexChar = x;
			}
			
			// IF This is true, it means the Location doesn't exist
			if (indexChar == 0)
			{
				System.out.println("Location in GameState's wishlist doesn't exist");
				return false;
			}
			
			//Now consult siwtchCheckCharacter
			//IF even one of them return false, it mean one of condition isn't met
			if (!siwtchCheckLocation(currentLo, desireValue))
			{
				return false;
			}
			
		}
		return true;
	}

	
	///////////////////////////////// Checking Location /////////////////////////////////////////
	
	
	
	/* OLD CODE
	*
	*

	//Only care if the specific artibute within the desire list are met, all non-mention are ignored
	//
	//RETURN TRUE   IF   the current GameState contain ALL characters that satisfy this 'inputList'
	//RETURN FALSE  IF
	
	public boolean containLocation(ArrayList<Location> inputList)
	{
		ArrayList<String> inputListDesire = new ArrayList<String>();
		boolean returnBool = true;
		
		for (Location inputLocation : inputList)
		{
			inputListDesire = inputLocation.getlistDesire();
			String characterName = inputLocation.getLocationName();
			
			//Looking for the character index
			int indexChar = 0;
			Location currentLo = new Location();
			for (int x = 0; x<inputList.size();x++)
			{
				if (inputList.get(x).getLocationName() == characterName) 
					currentLo = inputList.get(x);
			}

			//Check if all 'desired' attributes of the character are met
			//IF even one is not met, this will return FALSE (the desired game state is not yet met)
			for (String stringDesire : inputListDesire)
			{
				if (!siwtchCheckLocation(currentLo,stringDesire))
				{
					returnBool = false;
				}
				
			}
		}
		return returnBool;
	}
	
		
	*
	*
	**/
	
	//RETURN TRUE   IF  there is character that satisfy the 'desire'
	//RETURN FALSE  IF  there is NO character that satisfy the 'desire'
	public boolean siwtchCheckLocation(Location currentLo,String stringDesire)
		{
			String variable = stringDesire.substring(0, stringDesire.indexOf(":"));
			String desireValue = stringDesire.substring(stringDesire.indexOf(":")+1,stringDesire.length());
			
			
			switch (variable)
				{
				case ("locationName"):
					if (currentLo.getLocationName() != desireValue)
					{
						return false;
					}
					break;
					
				case ("locationType"):
					if (currentLo.getLocationType() != desireValue)
					{
						return false;
					}
					break;
					
				case ("locationEnvironment"):
					if (currentLo.getLocationEnvironment() != desireValue)
					{
						return false;
					}
					break;
					
				case ("listConnectLocation"):
					if (!currentLo.getListNearbyLocation().contains(desireValue))
					{
						return false;
					}
					break;
				
				case ("listItemInLocation"):
					//
					// Since item also has its own list, this case are different from others.
					//
					//
					if(!checkListItem(currentLo.getListItem(),desireValue))
					{
						return false;
					}
					break;
					
				default: 
					System.out.println("siwtchCheckLocation's variable does not exist, FROM enviroment.GameWorld.containCharacter");
					return false;
				}
			// IF none of the switch's RETURN got triggered (to return false)
			// It means that the characters satisfy the conditions; thus return true;
			return true;
		}
	

	
	
	// *** This is sharely used by "siwtchCheckCharacter" and "siwtchCheckLocation"
	//
	//
	/////////// Method for switch inside listItem 
	/////////// So that method GameStateCheck don't have [switch inside switch] and easier to read
	//
	// EX: When this was called, the string should look like this [THE_ITEM_NAME:typeOfItem:luxury]
	//
	// RETURN TRUE   IF the desireValue exist
	// RETURN FALSE  IF the desireValue doesn't exist
	public boolean checkListItem(ArrayList<Item> inputListItem, String stringDesire)
	{
		String itemName = stringDesire.substring(0, stringDesire.indexOf(":"));;
		String variable = stringDesire.substring(stringDesire.indexOf(":")+1, stringDesire.lastIndexOf(":"));
		String desireValue = stringDesire.substring(stringDesire.lastIndexOf(":")+1,stringDesire.length());
	
		// Character may has multiple item with the same name, but different stats (sword, but one is broken, but the other aren't).
		// Thus all item with matching name must be checked
		// IF even ONE of the item match, this methods will return true;
		
		ArrayList<Item> listMatchItem = new ArrayList<Item>();

		for (int x = 0; x<inputListItem.size();x++)
		{
			if (inputListItem.get(x).getName() == itemName) 
				listMatchItem.add(inputListItem.get(x));
		}
		
		if (variable == "itemNameNOT" && listMatchItem.size() == 0) {
			System.out.println("Char not contain prohibit item");
			return true;
		}
		
		else if (listMatchItem.size() == 0 ) {
			System.out.println("NO MATCH ITEM IS FOUND");
			return false;
		}

		// If NONE of the item match with the desireValue >>> this method must return FALSE;
		// In order to do this, we use the isOneMatch.
		//
		// This boolean will turn true if one of the item match		
		// At the end of the for loop, the methods will return this boolean
		boolean isOneMatch = false;
		if (variable == "listPropertyNOT")
		{
			isOneMatch = true;
		}
		
		for (Item inputItem : listMatchItem)	
		{
			switch (variable)
			{
				// This is use to check if this item is in inventory
				case ("itemName") 
				:
					if (inputItem.getName() == desireValue) 
						{
						isOneMatch = true;
						}
					break;
					
					
				case ("typeOfItem") 
				:
					if (inputItem.getListTypeOfItem().contains(desireValue)) 
						{
						isOneMatch = true;
						}
					break;
				case ("typeOfFunction") 
				:
					if (inputItem.getListTypeOfFunction().contains(desireValue)) 
						{
						isOneMatch = true;
						}
					break;
				case ("ownerName") 
				:
					if (inputItem.getOwnerName() == desireValue) 
						{
						isOneMatch = true;
						}
					break;
				case ("isOnGround") 
				:
					String curIsAlive = Boolean.toString(inputItem.isItemOnGround());
					if (curIsAlive == desireValue) 
					{
						isOneMatch = true;
					}
					break;
				case ("currentLocation") 
				:
					if (inputItem.getCurrentLocation() == desireValue) 
						{
						isOneMatch = true;
						}
					break;
					
				
				case ("sameLocation")
				: 
					
					
					//-------------FIND ALL ITEM THAT MATCH DESIREVALUE------------------
					
					ArrayList<Item> listMatchItemSameLocation = new ArrayList<Item>();
					
				//	String itemName = stringDesire.substring(0, stringDesire.indexOf(":"));;
				//	String variable = stringDesire.substring(stringDesire.indexOf(":")+1, stringDesire.lastIndexOf(":"));
				//	String desireValue = stringDesire.substring(stringDesire.lastIndexOf(":")+1,stringDesire.length());
			
					for (int x = 0; x<inputListItem.size();x++)
					{
						if (inputListItem.get(x).getName() == desireValue) 
							listMatchItemSameLocation.add(inputListItem.get(x));
					}
					
					if (listMatchItemSameLocation.size() == 0 ) {
						System.out.println("NO MATCH ITEM IS FOUND");
						return false;
					}
					//--------------------------------
					
					Item itemSameLocation = null;
					for (Item checkItem : listMatchItemSameLocation)
					{
						if (checkItem.getCurrentLocation() == inputItem.getCurrentLocation())
						{
							// If some item in match list = in same location, break and return true;
							break;
						}
					}
					// If the code reach this code, it mean no matched item with same location exist, Thus return false;
					return false;
					
					
					
					
				// If contain desireValue, return true;
				case ("listProperty") 
				:
					if (inputItem.getListProperty().contains(desireValue)){
						isOneMatch = true;
					}
					break;
					
				// If NOT contain desireValue, return true;	
				case ("listPropertyNOT") 
				:
					if (!inputItem.getListProperty().contains(desireValue) && isOneMatch == true) {
						break;
					}
					else if (inputItem.getListProperty().contains(desireValue)){
						isOneMatch = false;
					}
					break;
					
					
				// In case that variable doesn't exist, it should mean that misspelling happen
				default 
				: 
					System.out.println("checkListItem's variable does not exist, FROM enviroment.GameWorld.containCharacter");
					return false;
			}
		}
		return isOneMatch;
	}
	
	
	
	
	
	// Check if the EXCAT copy of EVERY objects within inputList exist in THIS GameWorld
	//
	public boolean containRelationship(ArrayList<Relationship> inputList)
	{
		for(Relationship inputRE: inputList)
		{
			if (!listRelationship.contains(inputRE)) return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	//////////Below = variable manipulation methods/////////////////////////////
		
	
		
	public void addChar(Character target)
	{
		boolean isReplace = false;
		
		//Check if 'target' already exist: If yes, replace it with new one
		ListIterator<Character> iterator = listCharacter.listIterator();
		while (iterator.hasNext())
		{
		     Character next = iterator.next();
		     if (next.isSameChararacter(target)) 
		     {
			         //Replace element
			         iterator.set(target);		
			         System.out.println(target.getName()+" already exist, it was replaced");
			         isReplace = true;
		     }
		 }
		
		// If the list doesn't contain Character 'target', add it to the list
		if (!isReplace)
		{
		listCharacter.add(target);
		}
	}
	
	
	public boolean setChar(Character target)
	{
		boolean isReplace = false;
		
		ListIterator<Character> iterator = listCharacter.listIterator();
		while (iterator.hasNext())
		{
		     Character next = iterator.next();
		     if (next.isSameChararacter(target)) 
		     {
			         //Replace element
			         iterator.set(target);		
			         isReplace = true;
		     }
		 }
		return isReplace;
		
		
		// Older code
		/*
		int indexTarget = -1;
		boolean isFound = false;
		for (int x = 0; x < listCharacter.size(); x++)
		{
			Character currentChar = listCharacter.get(x);
			if (currentChar.getName() == target.getName())
			{
				indexTarget = x;
				isFound = true;
			}
		}
		
		if(isFound) 
		{
			listCharacter.remove(indexTarget);
			listCharacter.add(target);
		}
		return isFound;
		*/
	}
	
	
	public void setPlayerLocation(String loc)
	{
		Character curChar = null;
		for (int x = 0; x < listCharacter.size(); x++)
		{
			curChar = listCharacter.get(x);
			if (curChar.getName() == "player")
			{
				curChar.setCurrentLocation(loc);
			}
		}
	}
	
	public String getPlayerLo()
	{
		Character curChar = null;
		for (int x = 0; x < listCharacter.size(); x++)
		{
			curChar = listCharacter.get(x);
			if (curChar.getName() == "player")
			{
				return curChar.getLocation();
			}
		}
		return null;
	}
	
	
	
	public void setListRelationship(ArrayList<Relationship> newListRelationship) 
	{
		listRelationship = newListRelationship;
	}
	public void setListCharacter(ArrayList<Character> newlistCharacter)
	{
		listCharacter = newlistCharacter;
	}	
	public void setListLocation(ArrayList<Location> newListLocation)
	{
		listLocation = newListLocation;
	}
	
	
	
	
	public ArrayList<Relationship> getListRelationship()
	{
		return listRelationship;
	}
	public ArrayList<Character> getListCharacter()
	{
		return listCharacter;
	}
	public ArrayList<Location> getListLocation ()
	{
		return listLocation;
	}
		
	
	
	
	public void addRelationship(Relationship inputRelationship)
	{
		listRelationship.add(inputRelationship);
	}

	
	
	
		
	
	
	/////////////////// Util, get object from this class //////////////////////////
	

	//get list of character in the input location.
	public ArrayList<Character> getListCharacterInLocation(String inputLocation) {

		ArrayList<Character> listOutput = new ArrayList<Character>();
		for (Character curCharacter : listCharacter)
		{
			if (curCharacter.getLocation() == inputLocation)
				listOutput.add(curCharacter);
		}
		return listOutput;
	}
	
	//Check if the input characters are in the same location.
	public boolean isCharacterInSameLocation(Character inputCharA, Character inputCharB) {
		String LocationCharA = inputCharA.getLocation();
		ArrayList<Character> inputListLocation = getListCharacterInLocation(LocationCharA);
		if (inputListLocation.contains(inputCharA) && inputListLocation.contains(inputCharB))
		{
			return true;
		}
		else return false;
	}
	
	public ArrayList<Item> getListItemFromALL_LocationANDchar()
	{
		ArrayList<Item> tempListItem = new ArrayList<Item>();
		tempListItem.addAll(this.getListItemFromLocationAll());
		tempListItem.addAll(this.getListItemFromCharacterALL());
		return tempListItem;
	}
	
	// This is for getting list item from THIS
	public ArrayList<Item> getListItemFromLocationAll()
	{
		ArrayList<Item> tempListItem = new ArrayList<Item>();
		for(int i = 0; i < this.listLocation.size(); i++) 
		{
			Location tempLocation = this.listLocation.get(i);
			tempListItem.addAll(tempLocation.getListItem());
		}
		return tempListItem;
	}
	
	public ArrayList<Item> getListItemFromCharacterALL()
	{
		ArrayList<Item> tempListItem = new ArrayList<Item>();
		for(int i = 0; i < this.listCharacter.size(); i++) 
		{
			Character tempCharacter = this.listCharacter.get(i);
			tempListItem.addAll(tempCharacter.getListItem());
		}
		return tempListItem;
	}
	
	
	// This is for getting item from INPUT
	public ArrayList<Item> getListItemFromLocation(ArrayList<Location> inputListLocation ) {

		ArrayList<Item> tempListItem = new ArrayList<Item>();
		for(int i = 0; i < inputListLocation.size(); i++) {
			Location tempLocation = inputListLocation.get(i);
			tempListItem.addAll(tempLocation.getListItem());
		}
		return tempListItem;
	}
	
	
	
	
	
	public String toStringPrologFormatListLo()
	{
		//memberlist([[a,b,c,d,e,ID],[a,b,c,d,e,ID],[a,b,c,d,e,ID]] , [[a,b]] , ).
		
		String output = "[";

		for (Location lo : this.listLocation)
		{
			output += lo.LocationToStringPrologFormat();
			output += ",";
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		return output;	
	}
	
	public String toStringPrologFormatListChar()
	{
		//(CO,GC,GL,AC,AL,AR,P,PF)
		
		//([],[],[],[],[],[],[],[]).
		//([],[], [[a,b,c,d,e,ID],[a,b,c,d,e,ID],[a,b,c,d,e,ID]] , [] , [] , [] , [] , []).
		
		String output = "[";

		for (Character ch : this.listCharacter)
		{
			output += ch.CharToStringPrologFormat();
			output += ",";
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		
		return output;
	}
	
	public String toStringPrologFormatListRelationship()
	{
		//memberlist([[a,b,c,d,e,ID],[a,b,c,d,e,ID],[a,b,c,d,e,ID]] , [[a,b]] , ).
		
		String output = "[";

		for (Relationship Re : this.listRelationship)
		{
			output += Re.toStringPrologFormat();
			output += ",";
		}
		output = output.substring(0, output.length()-1);
		output += "]";
		return output;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
