package enviroment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.Spring;

import enviroment.GameState;
import enviroment.GameWorld;
import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Relationship;
import gameObject.Skill;
import gameObject.Status;

import templateComponent.AtomAction;
import templateComponent.AtomItem;
import templateComponent.AtomParent;
import templateComponent.Component;
import templateComponent.QuestFrame;
import templateComponent.Template;






// *****Everything not stated in this GameState == "not care" and can be anything

// This is mainly use for "Start State" and "Goal State"equal




//All list have to be converted into 'prolog form' before doing quarry.
//
//Relationship >> 					relationship(etc...) >> [Then append to main]
//listCharacter.friendlist >> 		relationship(friend,char,target) >> [Then add to main relationship]
//listCharacter.ability >> 			ability(ability,char)
//listCharacter.item >> 			item(item,char)
//listItemOnLand >> 				item(item,no_owner)

public class GameState {

	//DEBUG Attribute, may later overhaul project so it nolonger depended on these makeshift workaround
	
	boolean isError = false;
	
	
	//***

	//The front string = what variable to check, the later string = what the variable must be
	//EX: ["CharNAME:isAlive:false","CharNAME:currentLocation:market",""]
	//EX: for ITEM ["CharNAME:listItem:THE_ITEM_NAME:typeOfItem:luxury"]  item has to be 5 steps
	ArrayList<GameCondition> listDesireCharacter = new ArrayList<GameCondition>();

	//EX: ["LocationNAME:locationType:City","LocationNAME:locationEnvironment:night",""]
	//EX: for ITEM ["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"]  item has to be 5 steps

	ArrayList<GameCondition> listDesireLocation = new ArrayList<GameCondition>();
	ArrayList<GameCondition> listDesireRelationship = new ArrayList<GameCondition>();

	// 21-11-2018 REMOVE since Item will now be either store in Character or Location
	//
	//ArrayList<GameCondition> listDesireItem = new ArrayList<GameCondition>();

	
	public GameState(Boolean boo) {
		isError = true;
	}
	
	public GameState() 
	{
		listDesireCharacter = new ArrayList<GameCondition>();
		//listDesireItem = new ArrayList<GameCondition>();
		listDesireLocation = new ArrayList<GameCondition>();
		listDesireRelationship = new ArrayList<GameCondition>();
	}

	public GameState(GameState inputGS) 
	{
		listDesireCharacter = new ArrayList<GameCondition>();
		for (GameCondition GC : inputGS.getListGameConditionCharacter()) {
			String str = GC.getDesireState();
			int comlvl = GC.getComponentLevel();
			String str2 = GC.getTypeOfObject();
			GameCondition newGC = new GameCondition(str,comlvl,str2);
			listDesireCharacter.add(newGC);
		}
		/*
		listDesireItem = new ArrayList<GameCondition>();
		for (GameCondition GC : inputGS.getListGameConditionItem()) {
			String str = GC.getDesireState();
			int comlvl = GC.getComponentLevel();
			String str2 = GC.getTypeOfObject();
			GameCondition newGC = new GameCondition(str,comlvl,str2);
			listDesireCharacter.add(newGC);
		}
		 */

		listDesireLocation = new ArrayList<GameCondition>();
		for (GameCondition GC : inputGS.getListGameConditionLocation()) {
			String str = GC.getDesireState();
			int comlvl = GC.getComponentLevel();
			String str2 = GC.getTypeOfObject();
			GameCondition newGC = new GameCondition(str,comlvl,str2);
			listDesireCharacter.add(newGC);
		}

		listDesireRelationship = new ArrayList<GameCondition>();
		for (GameCondition GC : inputGS.getListGameConditionRelationship()) {
			String str = GC.getDesireState();
			int comlvl = GC.getComponentLevel();
			String str2 = GC.getTypeOfObject();
			GameCondition newGC = new GameCondition(str,comlvl,str2);
			listDesireCharacter.add(newGC);
		}

	}

	public GameState(ArrayList<GameCondition> newListCharacter, ArrayList<GameCondition> newListItem, ArrayList<GameCondition> newListLocation, ArrayList<GameCondition> newListRelationship) 
	{
		listDesireCharacter = newListCharacter;
		//listDesireItem = newListItem;
		listDesireLocation = newListLocation;
		listDesireRelationship = newListRelationship;
	}




	////////////////////// GET INFO ////////////////////////

	//DEBUG
	public boolean getIsError(){
		return isError;
	}
	
	//
	
	
	
	public ArrayList<GameCondition> getListGameConditionCharacter(){
		return listDesireCharacter;
	}

	/*
	public ArrayList<GameCondition> getListGameConditionItem(){
		return listDesireItem;
	}
	 */

	public ArrayList<GameCondition> getListGameConditionLocation(){
		return listDesireLocation;
	}

	public ArrayList<GameCondition> getListGameConditionRelationship(){
		return listDesireRelationship;
	}


	public ArrayList<String> getListStringCharacter(){
		ArrayList<String> returnList = new ArrayList<String>();
		for (GameCondition GC : listDesireCharacter)
		{
			returnList.add(GC.getDesireState());
		}
		return returnList;
	}

	/*
	public ArrayList<String> getListStringItem(){
		ArrayList<String> returnList = new ArrayList<String>();
		for (GameCondition GC : listDesireItem)
		{
			returnList.add(GC.getDesireState());
		}
		return returnList;
	}
	 */

	public ArrayList<String> getListStringLocation(){
		ArrayList<String> returnList = new ArrayList<String>();
		for (GameCondition GC : listDesireLocation)
		{
			returnList.add(GC.getDesireState());
		}
		return returnList;
	}

	public ArrayList<String> getListStringRelationship(){
		ArrayList<String> returnList = new ArrayList<String>();
		for (GameCondition GC : listDesireRelationship)
		{
			returnList.add(GC.getDesireState());
		}
		return returnList;
	}


	public void addDesire(ArrayList<GameCondition> inputGC_List) {
		for (GameCondition GC : inputGC_List) {
			GameCondition GC_new = new GameCondition(GC);
			addDesire(GC_new);
		}
	}



	//Conflict calculating LIKE THIS.....
	//
	//1.	 Add to, Goal State, from lowest level Component to highest.
	//2.	 GameCondition is added to Full Condition State one by one.
	//2.1. IF conflict is found this way, declare the quest to be 'llegal'


	// If hard-conflict, return false to force new quest restart 
	public boolean addDesireConflictCheck(GameCondition inputGC) {


		ArrayList<GameCondition> sum_GC_This = new ArrayList<GameCondition>();
		String stringDesire = inputGC.getDesireState();

		//EX: ITEM ["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury"]  
		//EX:  [: number]        1                  2             3          4       

		String stringDesire_2 = "";
		String stringDesire_3 = "";
		String stringDesire_4 = "";

		String HOLD_Name = "";
		String variable_1 = "";
		String desireValue_1 = "";   // Is desire Value, OR in case of item, the Item NAME

		String variable_2 = "";		// exclusive for ITEM ONLY
		String desireValue_2 = "";	// exclusive for ITEM ONLY

		String typeOfObject = inputGC.getTypeOfObject();


		// Temp variable for switch()
		//
		boolean isDuplicated = false;
		boolean isPairSameVariable = false;
		int GCnowComLevel = 999;
		GameCondition GCnow1 = new GameCondition();
		//
		//

		switch (typeOfObject)
		{
		// If [Character] or [Item in character inventory]
		case "Character":

			HOLD_Name = inputGC.getName();
			variable_1 = inputGC.getVariable1();
			desireValue_1 = inputGC.getDesireValue1();


			switch (variable_1) 
			{

			//IGNORE, do not pay attention to conflict in this variable
			case ("listOccupation"):
				isDuplicated = false;
			for (GameCondition GCnow : listDesireCharacter) {
				if ((GCnow.getName() == HOLD_Name) && (GCnow.getVariable1() == variable_1)) {
					if (GCnow.getDesireValue1() == inputGC.getDesireValue1()) {
						isDuplicated = true;
					}
				}
			}
			if (!isDuplicated) {
				this.listDesireCharacter.add(inputGC);
			}
			return true;
			// End of IGNORE CASE()



			//OVERRIDE USING ONE WITH LOWER LEVEL	
			case ("currentLocation"):	
				isDuplicated = false;
			for (GameCondition GCnow : listDesireCharacter) {
				if ((GCnow.getName() == HOLD_Name) && (GCnow.getVariable1() == variable_1)) {
					if (GCnow.getDesireValue1() == inputGC.getDesireValue1()) {
						isDuplicated = true;
					}
					else {
						GCnowComLevel = GCnow.getComponentLevel();
						GCnow1 = GCnow;
					}
				}
			}
			if (!isDuplicated) {
				if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
				{
					this.listDesireCharacter.remove(GCnow1);
					this.listDesireCharacter.add(inputGC);
				}
				else 
				{
					// if the input has higher, just ignore it.
				}
			}
			return true;

			//conflict if same case & diff variable
			case ("isAlive"):
				isDuplicated = false;

			for (GameCondition GCnow : listDesireCharacter) {

				if (GCnow.getName() == HOLD_Name) {
					if (GCnow.getDesireValue1() != inputGC.getDesireValue1()) {
						return false;
					}
					else if ( (GCnow.getDesireValue1() == inputGC.getDesireValue1()) 
							&&(GCnow.getVariable1() == variable_1)) {
						isDuplicated = true;
					}
				}
			}

			if (!isDuplicated) {
				this.listDesireCharacter.add(inputGC);
			}
			return true;
			// End of case(isAlive)


			//conflict if same variable after case in pair
			case ("listStatus")	:
			case ("listStatusNOT"):

				isDuplicated = false;

			for (GameCondition GCnow : listDesireCharacter) {

				if (GCnow.getName() == HOLD_Name) {
					if ((GCnow.getVariable1() == "listStatus") && (inputGC.getVariable1() == "listStatusNOT")) {
						if(GCnow.getDesireValue1() == inputGC.getDesireValue1())
						{
							return false;
						}
					}

					else if ((GCnow.getVariable1() == "listStatusNOT") && (inputGC.getVariable1() == "listStatus")) {
						if(GCnow.getDesireValue1() == inputGC.getDesireValue1())
						{
							return false;
						}
					}

					else if (((GCnow.getVariable1() == "listStatusNOT") && (inputGC.getVariable1() == "listStatusNOT")) || ((GCnow.getVariable1() == "listStatus") && (inputGC.getVariable1() == "listStatus"))) 
					{
						if (GCnow.getDesireValue1() == inputGC.getDesireValue1()) {
							isDuplicated = true;
						}
					}
				}
			}

			if (!isDuplicated) {
				this.listDesireCharacter.add(inputGC);
			}
			return true;
			// End of case(listStatusNOT)


			//conflict if same variable after case in pair
			case ("listSkill"):
			case ("listSkillNOT"):

				isDuplicated = false;

			for (GameCondition GCnow : listDesireCharacter) {

				if (GCnow.getName() == HOLD_Name) {
					if ((GCnow.getVariable1() == "listSkill") && (inputGC.getVariable1() == "listSkillNOT")) {
						if(GCnow.getDesireValue1() == inputGC.getDesireValue1())
						{
							return false;
						}
					}

					else if ((GCnow.getVariable1() == "listSkillNOT") && (inputGC.getVariable1() == "listSkill")) {
						if(GCnow.getDesireValue1() == inputGC.getDesireValue1())
						{
							return false;
						}
					}

					else if (((GCnow.getVariable1() == "listSkillNOT") && (inputGC.getVariable1() == "listSkillNOT")) || ((GCnow.getVariable1() == "listSkill") && (inputGC.getVariable1() == "listSkill"))) 
					{
						if (GCnow.getDesireValue1() == inputGC.getDesireValue1()) {
							isDuplicated = true;
						}
					}
				}
			}

			if (!isDuplicated) {
				this.listDesireCharacter.add(inputGC);
			}
			return true;
			// End of case(listSkillNOT)


			//Conflict if same object after case + match ITEM CONFLICT RULE (ITEM TOOK PIORITY?)
			case ("listItem"):

				HOLD_Name 		= inputGC.getName();
			variable_1		= inputGC.getVariable1();
			desireValue_1 	= inputGC.getDesireValue1();


			for (GameCondition GCnow : listDesireCharacter)
			{	
				if (GCnow.getName() == HOLD_Name) 
				{
					if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable1() == variable_1))
					{
						variable_2 		= inputGC.getVariable2();
						desireValue_2	= inputGC.getDesireValue2();

						switch (variable_2)
						{
						//IGNORE
						case ("typeOfItem") :
						case ("typeOfFunction") : 

							isDuplicated = false;

						if ((GCnow.getDesireValue2() == desireValue_2) && (GCnow.getVariable2() == variable_2)) {
							isDuplicated = true;
						}

						if (!isDuplicated) {
							this.listDesireCharacter.add(inputGC);
						}
						return true;
						// End of IGNORE CASE()


						//OVERRIDE USING ONE WITH LOWER LEVEL	
						case ("currentLocation"):	
							isDuplicated = false;
						if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
							if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
								isDuplicated = true;
							}
							else {
								GCnowComLevel = GCnow.getComponentLevel();
								GCnow1 = GCnow;
							}
						}
						if (!isDuplicated) {
							if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
							{
								this.listDesireCharacter.remove(GCnow1);
								this.listDesireCharacter.add(inputGC);
							}							
							else 
							{
								// if the input has higher, just ignore it.
							}
						}
						return true;


						//conflict if same case & diff variable
						//OVERRIDE USING ONE WITH LOWER LEVEL	
						case ("ownerName") :

							isDuplicated = false;
						if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
							if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
								isDuplicated = true;
							}
							else {
								GCnowComLevel = GCnow.getComponentLevel();
								GCnow1 = GCnow;
							}
						}
						if (!isDuplicated) {
							if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
							{
								this.listDesireCharacter.remove(GCnow1);
								this.listDesireCharacter.add(inputGC);
							}							
							else 
							{
								// if the input has higher, just ignore it.
							}
						}
						return true;



						case ("isOnGround") :

							isDuplicated = false;
						if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
							if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
								isDuplicated = true;
							}
							else {
								GCnowComLevel = GCnow.getComponentLevel();
								GCnow1 = GCnow;
							}
						}
						if (!isDuplicated) {
							if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
							{
								this.listDesireCharacter.remove(GCnow1);
								this.listDesireCharacter.add(inputGC);
							}							
							else 
							{
								// if the input has higher, just ignore it.
							}
						}
						return true;




						//conflict if same variable after case in pair
						case ("itemName") :
						case ("itemNameNOT"):
							isDuplicated = false;
						if ((GCnow.getDesireValue1() == desireValue_1)) {
							if ((GCnow.getVariable2() == "itemName") && (inputGC.getVariable2() == "itemNameNOT")) {
								if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
								{
									return false;
								}
							}

							else if ((GCnow.getVariable2() == "itemNameNOT") && (inputGC.getVariable2() == "itemName")) {
								if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
								{
									return false;
								}
							}

							else if (((GCnow.getVariable2() == "itemName") && (inputGC.getVariable2() == "itemName")) || ((GCnow.getVariable2() == "itemNameNOT") && (inputGC.getVariable2() == "itemNameNOT"))) 
							{
								if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
									isDuplicated = true;
								}
							}
						}


						if (!isDuplicated) {
							this.listDesireCharacter.add(inputGC);
						}
						return true;
						// End of case(itemName)



						case ("listProperty") :
						case ("listPropertyNOT") : 

							isDuplicated = false;
						if ((GCnow.getDesireValue1() == desireValue_1)) {
							if ((GCnow.getVariable2() == "listProperty") && (inputGC.getVariable2() == "listPropertyNOT")) {
								if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
								{
									return false;
								}
							}

							else if ((GCnow.getVariable2() == "listPropertyNOT") && (inputGC.getVariable2() == "listProperty")) {
								if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
								{
									return false;
								}
							}

							else if (((GCnow.getVariable2() == "listProperty") && (inputGC.getVariable2() == "listProperty")) || ((GCnow.getVariable2() == "listPropertyNOT") && (inputGC.getVariable2() == "listPropertyNOT"))) 
							{
								if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
									isDuplicated = true;
								}
							}
						}


						if (!isDuplicated) {
							this.listDesireCharacter.add(inputGC);
						}
						return true;
						// End of case(itemName)

						default:
							System.out.println("ERROR, wrong Chararacter_ITEM Variable input in GameState:AddGameConditionRestriction");
							return false;
						}
					}
				}
			}
			return true;
			// End of case(itemList)


			default:
				System.out.println("ERROR, wrong Chararacter Variable input in GameState:AddGameConditionRestriction");
				return false;
			}
			// END of case "Character"




			//////////////////////////////////////////////////////////////////

			// If [Location] or [Item in Location on ground]
		case "Location":

			HOLD_Name 		= inputGC.getName();
			variable_1		= inputGC.getVariable1();
			desireValue_1 	= inputGC.getDesireValue1();
			//variable_2 	= inputGC.getVariable2();
			//desireValue_2	= inputGC.getDesireValue2();

			switch (variable_1) 
			{
			//IGNORE (ACTUALLY conflict if same case, but current build no way to manipulate these attribute)
			case ("locationName"):
			case ("locationType"):
			case ("locationEnvironment"):
			case ("listConnectLocation"):
				return true;

			//OVERRIDE USING ONE WITH LOWER LEVEL


			//Conflict if same object after case + match ITEM CONFLICT RULE (ITEM TOOK PIORITY)
			case ("listItemInLocation"):
			{

				HOLD_Name 		= inputGC.getName();
				variable_1		= inputGC.getVariable1();
				desireValue_1 	= inputGC.getDesireValue1();



				for (GameCondition GCnow : listDesireLocation)
				{
					if (GCnow.getName() == HOLD_Name) 
					{
						if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable1() == variable_1))
						{
							variable_2 		= inputGC.getVariable2();
							desireValue_2	= inputGC.getDesireValue2();

							switch (variable_2)
							{
							//IGNORE
							case ("typeOfItem") :
							case ("typeOfFunction") : 

								isDuplicated = false;

							if ((GCnow.getDesireValue2() == desireValue_2) && (GCnow.getVariable2() == variable_2)) {
								isDuplicated = true;
							}

							if (!isDuplicated) {
								this.listDesireCharacter.add(inputGC);
							}
							return true;
							// End of IGNORE CASE()


							//OVERRIDE USING ONE WITH LOWER LEVEL	
							case ("currentLocation"):	
								isDuplicated = false;
							if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
								if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
									isDuplicated = true;
								}
								else {
									GCnowComLevel = GCnow.getComponentLevel();
									GCnow1 = GCnow;
								}
							}
							if (!isDuplicated) {
								if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
								{
									this.listDesireCharacter.remove(GCnow1);
									this.listDesireCharacter.add(inputGC);
								}							
								else 
								{
									// if the input has higher, just ignore it.
								}
							}
							return true;


							//conflict if same case & diff variable
							//OVERRIDE USING ONE WITH LOWER LEVEL	
							case ("ownerName") :

								isDuplicated = false;
							if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
								if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
									isDuplicated = true;
								}
								else {
									GCnowComLevel = GCnow.getComponentLevel();
									GCnow1 = GCnow;
								}
							}
							if (!isDuplicated) {
								if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
								{
									this.listDesireCharacter.remove(GCnow1);
									this.listDesireCharacter.add(inputGC);
								}							
								else 
								{
									// if the input has higher, just ignore it.
								}
							}
							return true;



							case ("isOnGround") :

								isDuplicated = false;
							if ((GCnow.getDesireValue1() == desireValue_1) && (GCnow.getVariable2() == variable_2)) {
								if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
									isDuplicated = true;
								}
								else {
									GCnowComLevel = GCnow.getComponentLevel();
									GCnow1 = GCnow;
								}
							}
							if (!isDuplicated) {
								if (GCnowComLevel > inputGC.getComponentLevel()) // If the input has lower component level, override the current gameCondition with input Condition
								{
									this.listDesireCharacter.remove(GCnow1);
									this.listDesireCharacter.add(inputGC);
								}							
								else 
								{
									// if the input has higher, just ignore it.
								}
							}
							return true;




							//conflict if same variable after case in pair
							case ("itemName") :
							case ("itemNameNOT"):
								isDuplicated = false;
							if ((GCnow.getDesireValue1() == desireValue_1)) {
								if ((GCnow.getVariable2() == "itemName") && (inputGC.getVariable2() == "itemNameNOT")) {
									if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
									{
										return false;
									}
								}

								else if ((GCnow.getVariable2() == "itemNameNOT") && (inputGC.getVariable2() == "itemName")) {
									if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
									{
										return false;
									}
								}

								else if (((GCnow.getVariable2() == "itemName") && (inputGC.getVariable2() == "itemName")) || ((GCnow.getVariable2() == "itemNameNOT") && (inputGC.getVariable2() == "itemNameNOT"))) 
								{
									if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
										isDuplicated = true;
									}
								}
							}


							if (!isDuplicated) {
								this.listDesireCharacter.add(inputGC);
							}
							return true;
							// End of case(itemName)



							case ("listProperty") :
							case ("listPropertyNOT") : 

								isDuplicated = false;
							if ((GCnow.getDesireValue1() == desireValue_1)) {
								if ((GCnow.getVariable2() == "listProperty") && (inputGC.getVariable2() == "listPropertyNOT")) {
									if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
									{
										return false;
									}
								}

								else if ((GCnow.getVariable2() == "listPropertyNOT") && (inputGC.getVariable2() == "listProperty")) {
									if(GCnow.getDesireValue2() == inputGC.getDesireValue2())
									{
										return false;
									}
								}

								else if (((GCnow.getVariable2() == "listProperty") && (inputGC.getVariable2() == "listProperty")) || ((GCnow.getVariable2() == "listPropertyNOT") && (inputGC.getVariable2() == "listPropertyNOT"))) 
								{
									if (GCnow.getDesireValue2() == inputGC.getDesireValue2()) {
										isDuplicated = true;
									}
								}
							}


							if (!isDuplicated) {
								this.listDesireCharacter.add(inputGC);
							}
							return true;
							// End of case(itemName)

							default:
								System.out.println("ERROR, wrong Chararacter_ITEM Variable input in GameState:AddGameConditionRestriction");
								return false;
							}
						}
					}
				
					
					
					
					

				}
			}
		}  // END OF case(listItemInLocation)
			


		case "Relationship":
			System.out.println("ERROR, wrong RELATIONSHIP Variable input in GameState:AddGameConditionRestriction");
			return false;

		default:
			System.out.println("ERROR, wrong TYPE OF GAMECONDITION input in GameState:AddGameConditionRestriction");
			return false;
		}

	}


		// --------------------------- Compare GameCondition if they override each other-----------------------------------


		public static ArrayList<GameCondition> createOverride(ArrayList<GameCondition> inputList_A ,ArrayList<GameCondition> inputList_B)
		{
			ArrayList<GameCondition> returnList = new ArrayList<GameCondition>();


			for (GameCondition GC_depth_A : inputList_A)
			{
				for (GameCondition GC_depth_B : inputList_B)
				{
					//1st, check if same Char/location, if not, go to next
					String desire_A = GC_depth_A.getDesireState();
					String desire_B = GC_depth_B.getDesireState();
					String name_A = GC_depth_A.getName();
					String name_B = GC_depth_B.getName();
					if (name_A != name_B) {
					}
					else {

						// TO BE CODED
						// TO BE CODED
						// TO BE CODED
						// TO BE CODED
						// TO BE CODED
						// TO BE CODED
						// TO BE CODED
					}
				}
			}
			return returnList;

		}













		public void addDesire(GameCondition inputGC){
			String GCtypeOfObject = inputGC.getTypeOfObject();


			switch (GCtypeOfObject)
			{
			case "Character":
				listDesireCharacter.add(inputGC);
				break;
			case "Location":
				listDesireLocation.add(inputGC);
				break;
			case "Relationship":
				listDesireRelationship.add(inputGC);
				break;

			default:
				System.out.println("ERROR, typeOfObjectIllgeal_GameState_addDesire()");

			}

		}














		/*/////////////   OLD CODE BELOW  ////////////////
		 * 
		 * 
		 * 

	// This will (re)set the GameState to a basic one 
	// for either testing/debugging or quick setup


	public void setBasicNewGameState()
	{
		listCharacter = new ArrayList<Character>();
		listRelationship = new ArrayList<Relationship>();
		listItemOnLand = new ArrayList<Item>();


		//Item
		Item newItem = new Item();



		newItem.CreateItemOnGround("food", "provision");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("water", "provision");
		listItemOnLand.add(newItem);

		newItem.CreateItemOnGround("weapon", "equipment");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("armor", "equipment");
		listItemOnLand.add(newItem);

		newItem.CreateItemOnGround("potion", "medicine");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("bondage", "medicine");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("alcohol", "medicine");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("tonic", "medicine");
		listItemOnLand.add(newItem);

		newItem.CreateItemOnGround("gem", "luxury");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("gold", "luxury");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("fancyWare", "luxury");
		listItemOnLand.add(newItem);

		newItem.CreateItemOnGround("book", "information");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("letter", "information");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("note", "information");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("document", "information");
		listItemOnLand.add(newItem);
		newItem.CreateItemOnGround("tape", "information");
		listItemOnLand.add(newItem);





		//player
		Character newChar = new Character("player", 1, true,"market");
		listCharacter.add(newChar);

		//NPC
		newChar = new Character("jack", 15, true,"market");
		listCharacter.add(newChar);
		newChar = new Character("john", 15, true,"market");
		listCharacter.add(newChar);
		newChar = new Character("jill", 15, true,"market");
		listCharacter.add(newChar);
		newChar = new Character("bob", 20, true,"capital");
		listCharacter.add(newChar);

		//Mob character
		newChar = new Character("soldier1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("doctor1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("merchant1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("blacksmith1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("thief1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("messenger1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("scout1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("farmer1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("miner1", 20, true,"capital");
		listCharacter.add(newChar);
		newChar = new Character("lumberjack1", 20, true,"capital");
		listCharacter.add(newChar);

		//Relation
		Relationship newRelation = new Relationship();
		newRelation.setRelationship("jack", "john", "friend");
		listRelationship.add(newRelation);

		//

	}




	///////////Below = variable manipulation methods/////////////////////////////

	public void addChar(Character target)
	{
		boolean isReplace = false;

		//Check if 'target' already exist: If yes, replace it with new one
		ListIterator<Character> iterator = listCharacter.listIterator();
		while (iterator.hasNext())
		{
		     Character next = iterator.next();
		     if (next.sameChararacter(target)) 
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
		     if (next.sameChararacter(target)) 
		     {
			         //Replace element
			         iterator.set(target);		
			         isReplace = true;
		     }
		 }
		return isReplace;


		// Older code

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

	}


	public void addRelationship(Relationship inputRelationship)
	{
		listRelationship.add(inputRelationship);
	}
	public void addItem(Item inputItem)
	{
		listItemOnLand.add(inputItem);
	}


	public void setListRelationship(ArrayList<Relationship> newListRelationship) 
	{
		listRelationship = newListRelationship;
	}
	public void setListCharacter(ArrayList<Character> newlistCharacter) {
		listCharacter = newlistCharacter;
	}	
	public void setListItemOnLand(ArrayList<Item> newListItemOnLand) {
		listItemOnLand = newListItemOnLand;
	}
	public List<Relationship> getListRelationship(){
		return listRelationship;
	}
	public List<Character> getListCharacter(){
		return listCharacter;
	}
	public List<Item> getListItemOnLand  (){
		return listItemOnLand ;
	}





	/////////////GET AS PROLOG FORMAT
	/////////////
	/////////////

	//[character(jack,15,dead,weak,market),character(player,1,alive,strong,market)]

	public String getCharListAsString()
	{
		String strReturn = "[";

		for (Character curChar : listCharacter)
		{
			strReturn += curChar.toStringPrologFormat();
			strReturn += ",";
		}
		strReturn = strReturn.substring(0, strReturn.length() - 1);
		strReturn += "]";
		return strReturn;
	}

	public String getCharListAsGoal()
	{
		String strReturn = "";

		for (Character curChar : listCharacter)
		{
			strReturn += curChar.toStringPrologFormat();
			strReturn += ",";
		}
		strReturn = strReturn.substring(0, strReturn.length() - 1);
		strReturn += "";
		return strReturn;
	}

	//relationship(jack,john,friend)
	public String getRelationshipListAsString()
	{
		String strReturn = "[";

		for (Relationship curRelation : listRelationship)
		{
			strReturn += curRelation.getRelationship();
			strReturn += ",";

		}
		strReturn = strReturn.substring(0, strReturn.length() - 1);
		strReturn += "]";
		return strReturn;
	}

	public String getItemListAsString()
	{
		String strReturn = "[";

		for (Item curItem : listItemOnLand)
		{
			strReturn += curItem.getItemProlog();
			strReturn += ",";

		}
		strReturn = strReturn.substring(0, strReturn.length() - 1);
		strReturn += "]";
		return strReturn;
	}

		 */
		
		
		
		
		///////////Below = override Java Utility methods/////////////////////////////


		@Override
		public String toString()
		{

				String strReturn = "";
				strReturn += "\n";
				strReturn += ",CHARACTER: {";
				String result = listDesireCharacter.stream().map(Object::toString).collect(Collectors.joining(", "));
				strReturn += result;
				strReturn += "} ";
				strReturn += "\n";
				strReturn += ",Location/ITEM: {";
				result = listDesireLocation.stream().map(Object::toString).collect(Collectors.joining(", "));
				strReturn += result;
				strReturn += "} ";
				strReturn += "\n";
				strReturn += "RELATIONSHIP: {";
				result = listDesireRelationship.stream().map(Object::toString).collect(Collectors.joining(", "));
				strReturn += result;
				strReturn += "} ";
				strReturn += "\n";
				return strReturn;


		}
		

		
		
		
		
		
		
		
		
		
		
		
	}
