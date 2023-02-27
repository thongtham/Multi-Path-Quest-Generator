




// BACKUP 26-2-2019 ,

// Backup before deleting all Prolog inside, and replace with ConnectorProlog
















package backup_QuestGenerator;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.cs3.prolog.connector.Connector;
import org.cs3.prolog.connector.common.QueryUtils;
import org.cs3.prolog.connector.process.PrologProcess;
import org.cs3.prolog.connector.process.PrologProcessException;
import org.cs3.prolog.connector.session.PrologSession;

import com.pengyifan.commons.collections.tree.TreeNode;

import enviroment.GameCondition;
import enviroment.GameState;
import enviroment.GameWorld;
import gameObject.Character;
import gameObject.Item;
import gameObject.Location;
import templateComponent.Token;
import templateComponent.Component;
import templateComponent.QuestFrame;
import templateComponent.Template;


//This class will generate QuestFrame, then template, then breakdown
//the <Component> into Component.
public class QuestGenerator {

	static GameWorld mainGameWorld;
	static ArrayList<Token> tokenList;
	
	//Token for quest generation
	
	public static void main(String args[])
	{
		mainGameWorld = new GameWorld();
		tokenList = new ArrayList<Token>();
				
		System.out.println("breakpoint 1");
		mainGameWorld.setBasicNewGameWorld();
		
		Template newGeneratedTemplate;
		
		// This is the main loop, when the generated quest does not satisfy the condition 
		// (Conflict conditions, out of acceptable size/length, too much Component repeating,etc.)
		// The 'judge' (code that check these conditions) will fire a 'continue' to bring the 
		// system back to the start of this while loop to start a new quest.
		
		
		outterloop:
		while (true)
			{
			System.out.println("////////////////");
			System.out.println("Start new QUEST GENERATING");
			System.out.println("////////////////");
			
			newGeneratedTemplate = new Template("NEW QUEST");
			tokenList = new ArrayList<Token>();
			
			//generateTemplate( 32, newGeneratedTemplate);
			
			generateTemplate( Integer.parseInt(args[0]), newGeneratedTemplate);


			//////// 1st step, generate and breakdown ComponentRule //////////
			
			// OLD CODE 6-9-2018 , New CODE BELOW THIS
			/*
			//This while loop will only exist using break;
			boolean isComponentRuleLeft = false;
			while (true)
			{
				breakdownComponent(newGeneratedTemplate);
				ArrayList<String> templateRule = newGeneratedTemplate.getComponentRuleString(newGeneratedTemplate.getComponentTree());
				for (String tempStr : templateRule)
				{
					// Since ComponentRule will be represented with "<[Component]>", thus 
					// if either < or > exist, it means there's still some ComponentRule left
					if (tempStr.contains("<") || tempStr.contains(">")) isComponentRuleLeft = true;
				}
					
				//If there's still ComponentRule left, the template must resolve them.
				if (isComponentRuleLeft)
				{
					// set to false. This is to reset the boolean, preparing for the next check'
					isComponentRuleLeft = false;
				}
				else 
				{
					//Exist while loop, no more ComponentRule to breakdown
					break;
				}
			}
			*/
			
			//////// 1st step, generate and breakdown ComponentRule //////////
			
			System.out.println("breakpoint 2, before breakdownComponent");
					
			while (breakdownComponent(newGeneratedTemplate)) {
				
			}
				
			
			//--------------------- 2nd step, create token  ----------------------/	
			///////////////////////////////////////////////////////////////////////
			

			
			
			
			// EDIT SINCE 6-12-2018  >>>  THIS is for levelQuest, special token selected as quest starter
			//
			//
			//
			//
			//
			int current_QuestLevel = 0;
			
			// 1 = character/ item in char, 2 = location/ item in location
			int QuestStarterObjectType = 0;
			
			//Select a random object within the game world
			Object randomObjectQuestStarter = new Object();
			
			//Character curCharQG = new Character();	15-2-2019
			Character curCharQG = null;
			
			int randomNumQuestStarter = -1;
			int randomQuestStarterObjNum = -1;
			
			boolean isQuestGiverOK = false;
			
			System.out.println("Select QuestGiver");
			
			while(!isQuestGiverOK)
			{
				//Random integer from [1] to [3]
				randomNumQuestStarter = ThreadLocalRandom.current().nextInt(1,3+1);
				System.out.println(randomNumQuestStarter);
			
				// get item from Location
				if (randomNumQuestStarter ==1)
				{
					randomQuestStarterObjNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromLocationAll().size()+1);
					randomObjectQuestStarter = mainGameWorld.getListItemFromLocationAll().get(randomQuestStarterObjNum-1);
					current_QuestLevel = mainGameWorld.getListItemFromLocationAll().get(randomQuestStarterObjNum-1).getLevelQuest();
					QuestStarterObjectType = 2;
					isQuestGiverOK = mainGameWorld.getListItemFromLocationAll().get(randomQuestStarterObjNum-1).getisQuestGiver();

					//DEBUG
					//
					System.out.println(mainGameWorld.getListItemFromLocationAll().get(randomQuestStarterObjNum-1).getName());
					System.out.println(mainGameWorld.getListItemFromLocationAll().get(randomQuestStarterObjNum-1).getisQuestGiver());
				}
				
				// get item from chararacter's inventory
				else if (randomNumQuestStarter == 3)
				{
					randomQuestStarterObjNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromCharacterALL().size()+1);
					randomObjectQuestStarter = mainGameWorld.getListItemFromCharacterALL().get(randomQuestStarterObjNum-1);
					current_QuestLevel = mainGameWorld.getListItemFromCharacterALL().get(randomQuestStarterObjNum-1).getLevelQuest();
					QuestStarterObjectType = 1;
					isQuestGiverOK = mainGameWorld.getListItemFromCharacterALL().get(randomQuestStarterObjNum-1).getisQuestGiver();
					
					//DEBUG
					//
					System.out.println(mainGameWorld.getListItemFromCharacterALL().get(randomQuestStarterObjNum-1).getName());
					System.out.println(mainGameWorld.getListItemFromCharacterALL().get(randomQuestStarterObjNum-1).getisQuestGiver());
					//System.out.println(isQuestGiverOK);
				}
				
				else if (randomNumQuestStarter == 2)
				{
					boolean isPlayer =true;
					while(isPlayer) {
						randomQuestStarterObjNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListCharacter().size()+1);
						randomObjectQuestStarter = mainGameWorld.getListCharacter().get(randomQuestStarterObjNum-1);
						curCharQG = (Character) randomObjectQuestStarter;
						
						// Check if chosen character is player
						if (curCharQG.isPlayer() == true)  // If it is player, must select new character.
						{
							isPlayer = true; 
						}
						else 
						{
							current_QuestLevel = curCharQG.getLevelQuest();
							QuestStarterObjectType = 1;
							isQuestGiverOK = curCharQG.getisQuestGiver();
							isPlayer = false; 
						}
					}
					
					//DEBUG
					//
					System.out.println("QUSET GIVER");
					System.out.println(curCharQG.getName());
					System.out.println(curCharQG.getisQuestGiver());
					System.out.println("QUSET GIVER");
				}
				
				
			
			}
			//
			//
			//
			//
			//
			//
			// END EDIT SINCE 6-12-2018 
			
			
			
			
			
			
			TreeNode rootNode = new TreeNode();
			rootNode = newGeneratedTemplate.getComponentTree();	
			List<TreeNode> itr = rootNode.preorderList();
			
			
			//1st, set each Component's preorder number
			int preorderCounter = 0;
			for (int x = 0; x < itr.size(); x++) {
				TreeNode curNode = itr.get(x);
				preorderCounter += 1;
				Component curCom = (Component) curNode.getObject();
				curCom.setPreorderNumber(preorderCounter);
				
			}
			
			//2nd, create token from the preOrder list
			for (int x = 0; x < itr.size(); x++) {
				TreeNode curNode = itr.get(x);
				Component curCom = (Component) curNode.getObject();
				String componentName = curCom.getComponentName();
				
				
				
				//IF "ROOT NODE"
				//IF TRUE, generate 2 token for 1st 2 children the NODE HAS
				if (componentName.equalsIgnoreCase("ROOT NODE"))
				{
					List<TreeNode> curNodeChildren = curNode.children();
					int numberOfTokenMade = 0;
					
					
					tokenloop: // If token has higher level of quest Level than QuestGiver, loop it all again
					for (int y = 0; y < curNodeChildren.size(); y++) {
						TreeNode curNode2ndLayer = curNodeChildren.get(y);
						Component curComLayer2 = (Component) curNode2ndLayer.getObject();
						
						//For root, we get 2 tokens out of it
						if (numberOfTokenMade >= 2) break;
						numberOfTokenMade++;
						
						//Select a random object within the game world
						Object randomObject = new Object();
						
						//Random integer from [1] to [2]
						int randomNum = ThreadLocalRandom.current().nextInt(1,3+1);
						
					
						// get item from Location
						if (randomNum == 1)
						{
							randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromLocationAll().size()+1);
							randomObject = mainGameWorld.getListItemFromLocationAll().get(randomNum-1);
							if (current_QuestLevel > mainGameWorld.getListItemFromLocationAll().get(randomNum-1).getLevelQuest())
							{
								continue tokenloop;
							}
						}
						
						// get item from chararacter's inventory
						else if (randomNum == 3)
						{
							randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromCharacterALL().size()+1);
							randomObject = mainGameWorld.getListItemFromCharacterALL().get(randomNum-1);
							if (current_QuestLevel > mainGameWorld.getListItemFromCharacterALL().get(randomNum-1).getLevelQuest())
							{
								continue tokenloop;
							}
						}
						
						else if (randomNum == 2)
						{
							boolean isPlayer =true;
							while(isPlayer) {
								randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListCharacter().size()+1);
								randomObject = mainGameWorld.getListCharacter().get(randomNum-1);
								Character curChar = (Character) randomObject;
								
								// Check if chosen character is player
								if (curChar.isPlayer() == false)  // If it is player, must select new character.
								{
									isPlayer = false; 
								}
								
								if (current_QuestLevel > curChar.getLevelQuest())
								{
									continue tokenloop;
								}
							}
						}
						
						// Using Location as token seem counter-intuitive.  
						
						//else if (randomNum == 3)
						//{
						//
						//randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListLocation().size()+1);
						//
						//DEBUG
						//System.out.println(randomNum);
						//System.out.println(mainGameWorld.getListLocation().toString());
						//
						//
						//randomObject = mainGameWorld.getListLocation().get(randomNum-1);
						//}
						
						Token newToken = new Token(curComLayer2.getPreorderNumber(), randomObject);
						tokenList.add(newToken);
					}
				}
				//IF "<QUEST>" (<SUBQUSET> will direct to <QUEST> 1st to select a random quest) 
				//Generate only 1 token for the 1st child
				
				else if (componentName.equalsIgnoreCase("<QUEST>"))
				{

					List<TreeNode> curNodeChildren = curNode.children();
					int numberOfTokenMade = 0;
					for (int y = 0; y < curNodeChildren.size(); y++) {
						TreeNode curNode2ndLayer = curNodeChildren.get(y);
						Component curComLayer2 = (Component) curNode2ndLayer.getObject();
						
						// ONLY 1 token for sub-quest
						if (numberOfTokenMade >= 1) break;  
						numberOfTokenMade++;
						
						//Select a random object within the game world
						Object randomObject = new Object();
						
						//Random integer from [1] to [2]
						int randomNum = ThreadLocalRandom.current().nextInt(1,2+1);
						
						
						//IF = NULL, don't put token to it
						Component curComChild = (Component) curNode2ndLayer.getObject();
						String curComName = curComChild.getComponentName();
						if (curComName.equalsIgnoreCase("NULL")) {
							continue;
						}
						
						else if (randomNum == 1)
						{
							randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromLocationAll().size()+1);
							randomObject = mainGameWorld.getListItemFromLocationAll().get(randomNum-1);

						}
						else if (randomNum == 2)
						{
							boolean isPlayer =true;
							while(isPlayer) {
								randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListCharacter().size()+1);
								randomObject = mainGameWorld.getListCharacter().get(randomNum-1);
								Character curChar = (Character) randomObject;
								
								// Check if chosen character is player
								if (curChar.isPlayer() == false) isPlayer = false; // If it is player, must select new character.
							}
						}
						
						// Using Location as token seem counter-intuitive.  
						
						//else if (randomNum == 3)
						//{
						//	randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListItemFromLocationAll().size()+1);
						//	randomObject = mainGameWorld.getListLocation().get(randomNum);
						//	
						//}
						
						Token newToken = new Token(curComLayer2.getPreorderNumber(), randomObject);
						tokenList.add(newToken);
					}
				
					
				}
				
			}
			//////// 3rd step, create fill in object into each leaves Components  /////////////////////	
			
			//System.out.println(newGeneratedTemplate.toString());
			
			System.out.println("Template size: "+ newGeneratedTemplate.getComponentTree().getLeaves().size());
			//System.out.println(newGeneratedTemplate.getComponentTree().getLeaves().toString());
			
			System.out.println("token size: "+tokenList.size());
			//System.out.println(tokenList.toString());
			
				
			////////////////////////// 4th step, checking if the template quest is within config-ed range ////////////////////////////////////////////

			
			int templateLength = newGeneratedTemplate.getComponentTree().getLeaves().size();

			//Count how many NULL there is, since we only count non-Null Component to check if in acceptable range
			List<TreeNode> questTreeLeaves = newGeneratedTemplate.getComponentTree().getLeaves();
			
			for (int x = 0; x < questTreeLeaves.size(); x++) {
				TreeNode curNode = questTreeLeaves.get(x);
				Component curCom = (Component) curNode.getObject();
				if (curCom.getComponentName().equalsIgnoreCase("NULL")){
					templateLength--;
				}
			}
			
			
			if ( Integer.parseInt(args[1])  <= templateLength &&  templateLength <= Integer.parseInt(args[2]))
				
			{
				// IF in range, does nothing
				
				//DEBUG BREAK
				//Remove to proceed next step
				System.out.println("break");
				//break;
			}
			else
			{
				//Continue to return back at the start and generate new quest

				//DEBUG BREAK
				System.out.println("continue");
				continue;
			}

			
			////////////////////////// 5th step, put token in this  ////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			// This list is to check if the current Node activate the next token yet?
			ArrayList<Integer> tokenInt = new ArrayList<Integer>();
			// Add number of PreOrder that trigger tokens.... 
			for (int x = 0; x < tokenList.size(); x++) {
				Token curToken = tokenList.get(x);
				tokenInt.add(curToken.getPreOrderNumber());
			}
			
			if (tokenList.size() == 0)
			{
				continue outterloop;
			}
			
			// This list is the list of token that is 'activated' 
			ArrayList<Token> activateToken = new ArrayList<Token>();
			
			//DEBUG TO BE DELETED
			System.out.println("-----------------");
			System.out.println(tokenInt.toString());
			System.out.println(activateToken.toString());
			
			int currentPo = 0;
			int currentPreOrder = tokenList.get(currentPo).getPreOrderNumber();
			
			// This is the main for loop that add Token to all Components
			
			for (int x = 0; x < questTreeLeaves.size(); x++) {
				TreeNode curNode = questTreeLeaves.get(x);
				System.out.println("NEXT NODE");
				Component curComponent = (Component) curNode.getObject();
				
				
				// add token to activateToken depend on the current PreOrder number.
				int comPO = curComponent.getPreorderNumber();
				
				while (currentPreOrder <= comPO)
				{

					//System.out.println("CurPreOrderNUM: " + currentPreOrder);
					//System.out.println("ComponentPONUM: " + curComponent.getPreorderNumber());
					
					//System.out.println(activateToken.toString());
					//System.out.println("still in while");
					
					if (currentPo >= tokenList.size()) {
						break;
					}
					if (tokenList.get(currentPo).getPreOrderNumber() > comPO)
					{
						break;
					}
					
					
					else {
						currentPreOrder = tokenList.get(currentPo).getPreOrderNumber();
						activateToken.add(tokenList.get(currentPo));
						
						//System.out.println("TokenIntNUM: " + tokenList.get(currentPo).getPreOrderNumber());
						//System.out.println(activateToken.toString());
						
						//System.out.println("CurPreOrderNUM: " + currentPreOrder);
						//System.out.println("ComponentPONUM: " + comPO);
						//System.out.println(activateToken.toString());
						//System.out.println("still in while");
						
						//System.out.println(tokenInt.get(currentPo));
						//System.out.println(tokenList.get(currentPo));
						
						//OBSOLETE TO BE DELETED
						//
						//tokenInt.remove(0);
						//tokenList.remove(0);
					}
					
					currentPo++;
				}
				String currentComponentName = curComponent.getComponentName();
				
				System.out.println("out of while");
				System.out.println(activateToken.toString());
				
				
				//Add token to Component if it isn't NULL
				if (currentComponentName.equalsIgnoreCase("NULL"))
				{
					//IF NULL, skip it
					continue;
				}
				
				else 
				{	
					// Some temporary variable to traverse the list to retrive the token
					int currentTokenNumFromLast = 1;
					
					@SuppressWarnings("unused")
					boolean option1Active = false;
					
					// These are group on how each Component can / cannot use specific token
					switch (currentComponentName)
					{
						// ANY = OK
						case "explore":
						case "listen":
						//case "report":
						case "goto":
							
							currentTokenNumFromLast = 1;
							while (true) 
							{

								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
							
								Token latestToken;
								
								if ( currentTokenInt == -1 ) {
									System.out.println("No available token with free slot");
									
									//Option 1: use the latest token anyway
									// But if no token exist, start new quest
									if ((activateToken.size()-1) == -1)
									{
										continue outterloop;
									}
									latestToken = activateToken.get(activateToken.size()-1);
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
									/*
									//Choice 2: start generate the quest again
									continue outterloop;
									*/
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								if (latestToken.getTokenInfo()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(1);
									break;
								}
								else if (latestToken.getTokenLoc()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(2);
									break;
								}
								else if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}
								// If == 0; it means last token is reached

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}

								
							break;
							
						// ONLY = [Itself]
						case "damage" :
						case "defend" :
							
							currentTokenNumFromLast = 1;
							
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								// If == 0; it means last token is reached
								if (currentTokenInt == -1 ) {
									System.out.println("No available token with free ITSELF slot");
									
									//Option 1: use the latest token anyway
									// But if no token exist, start new quest
									if ((activateToken.size()-1) == -1)
									{
										continue outterloop;
									}
									latestToken = activateToken.get(activateToken.size()-1);
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
									/*
									//Choice 2: start generate the quest again
									continue outterloop;
									*/
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							
							
							break;
							
							
						// ONLY = [item, Itself]
						case "read" :
						case "experiment" :
						case "use" :
							
							
							currentTokenNumFromLast = 1;
							option1Active = false;
							while (true) 
							{
								
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println("No available item REU");
									
									
									//Option 1: use the latest item token anyway
									currentTokenNumFromLast = 1;
									
									while(true)
									{
										if ( currentTokenInt == -1 ) {
											continue outterloop;
											// If no item token available, start generate new quest;
										}
										// If exhaust full token list and no token available, just use the latest item token
										latestToken = activateToken.get(activateToken.size()-1);
										
										if (!(latestToken.getTokenObject() instanceof Item)) {
											currentTokenNumFromLast++;
											continue;
										}
										else {
										curComponent.setObject(latestToken.getTokenObject());
										curComponent.setTypeOfToken(3);
										}
									}
									/*
									//Choice 2: start generate the quest again
									continue outterloop;
									*/
									
								}
								
								latestToken = activateToken.get(currentTokenInt);
							
								//If the object isn't Item, look for the next one
								if (!(latestToken.getTokenObject() instanceof Item)) {
									currentTokenNumFromLast++;
									continue;
								}
								
								else if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							break;
							
							
							
							
							
							
							
							
							
						// ONLY [NPC, Itself]
						case "escort":
						case "follow":
						case "stealth":
						case "kill" :
						case "report" :
						case "spy":
							
							currentTokenNumFromLast = 1;
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;

								if ( currentTokenInt == -1 ) {
									System.out.println("No available character EFSK");
									System.out.println("SELECT RANDOM CHARACTER");
									
									ArrayList<Character> TEMPcharList = mainGameWorld.getListCharacter();
									int listSize = TEMPcharList.size();
									int randomNum = ThreadLocalRandom.current().nextInt(1, listSize + 1);
									Character randomChar = TEMPcharList.get(randomNum -1);
									
									curComponent.setObject(randomChar);
									curComponent.setTypeOfToken(3);
									break;
									// EDIT DATE: 20-11-2018 change from start new quest to select random character (too much re-generate)
									//
									//System.out.println("No available character EFSK");
									// start generate the quest again since the quest doesn't make sense
									//continue outterloop;
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								// DEBUG
								/*
								System.out.println("EFSK"+(currentTokenInt));
								if (currentTokenInt == (0))
								{
									System.out.println("BREAKPOINT 0");
									System.out.println("BREAKPOINT 0");
								}
								if (currentTokenInt == (-1))
								{
									System.out.println("BREAKPOINT -1");
									System.out.println("BREAKPOINT -1");
								}
								*/
								// DEBUG END
								
								
								//If the object isn't NPC, look for the next one
								if (!(latestToken.getTokenObject() instanceof Character)) {
									currentTokenNumFromLast++;
									continue;
								}
								else if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}
								// If == 0; it means last token is reached

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							break;
							
						// ONLY [NPC -> captured, Itself]
						case "free" :
							
							currentTokenNumFromLast = 1;
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println("No available character FREE");
									
									// start generate the quest again since the quest doesn't make sense
									continue outterloop;
								}
								
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								//If the object isn't NPC, look for the next one
								if (!(latestToken.getTokenObject() instanceof Character)) {
									currentTokenNumFromLast++;
									continue;
								}
								//Now check if the character is captured. If not, go to next token
								Character curCharacter = (Character) latestToken.getTokenObject();
								if (!curCharacter.getListStatus().contains("Capture")) {
									currentTokenNumFromLast++;
									continue;
								}
								
								//Check if the 'captured' character's ITSELF is available
								if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							break;
							
							// ONLY [NPC -> freed, Itself]
						case "capture" :	
							
							currentTokenNumFromLast = 1;
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println("No available character CAPTURE");
									
									// start generate the quest again since the quest doesn't make sense
									continue outterloop;
									
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								
							
								//If the object isn't NPC, look for the next one
								if (!(latestToken.getTokenObject() instanceof Character)) {
									currentTokenNumFromLast++;
									continue;
								}
								//Now check if the character is free. (AKA not contain capture)
								Character curCharacter = (Character) latestToken.getTokenObject();
								if (curCharacter.getListStatus().contains("Capture")) {
									currentTokenNumFromLast++;
									continue;
								}
								
								//If yes = can use this token
								if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							break;
							

						//ONLY [ ITEM , itself ]
						case "gather" :
						case "repair" :
							
					
							currentTokenNumFromLast = 1;
							option1Active = false;
							while (true) 
							{
								
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println("No available character GRT");
									
									//Option 1: use the latest item token anyway
									currentTokenNumFromLast = 1;
									
									while(true)
									{
										if ( currentTokenInt == -1 ) {
											continue outterloop;
											// If no item token available, start generate new quest;
										}
										// If exhaust full token list and no token available, just use the latest item token
										latestToken = activateToken.get(activateToken.size()-1);
										
										if (!(latestToken.getTokenObject() instanceof Item)) {
											currentTokenNumFromLast++;
											continue;
										}
										else {
										curComponent.setObject(latestToken.getTokenObject());
										curComponent.setTypeOfToken(3);
										
										
										
										// Make the item broken so it can be repaired
										if(currentComponentName == "repair") {
											boolean itemExist = false;
											Item tokenItem = (Item) latestToken.getTokenObject();
											//damaged
											ArrayList<Item> allItemList = mainGameWorld.getListItemFromALL_LocationANDchar();
											
											for (int y = 0; y < allItemList.size(); y++) {
												Item itemITR = allItemList.get(y);
												if (tokenItem == itemITR)
												{
													itemITR.addProperties("damaged");
													itemExist = true;
												}
											}
											
											if (!itemExist)
											{
												System.out.println("ERROR ERROR ERROR ERROR ERROR");
												System.out.println("repair don't have match Item");
											}
										
										}
										
										}
									}
									/*
									//Choice 2: start generate the quest again
									continue outterloop;
									*/
									
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								// DEBUG
								/*
								if (k == 0)
								{
									System.out.println("BREAKPOINT");
									System.out.println("BREAKPOINT");
								}
								
								if (k == (-1))
								{
									System.out.println("BREAKPOINT");
									System.out.println("BREAKPOINT");
								}
								*/
								// DEBUG END
								
								
								//If the object isn't Item, look for the next one
								if (!(latestToken.getTokenObject() instanceof Item)) {
									currentTokenNumFromLast++;
									continue;
								}
								
								else if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							break;
							

						//ONLY [Clues = not use token]
						case "examine" :
							
							//examine don't use token
							curComponent.setTypeOfToken(4);
							
							break;
						
							
						// ONLY [Itself, Item,  2nd = latest NPC token, even after used up?]
						// MainObjective = Item
						// SecondaryObjective = NPC
						case "give" :

							//------------1st select a item to give -----
							
							currentTokenNumFromLast = 1;
							option1Active = false;
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println("No available item GIVE");
									
									//Option 1: use the latest item token anyway
									currentTokenNumFromLast = 1;
									
									// If exhaust full token list and no token available, just use the latest item token
									latestToken = activateToken.get(activateToken.size()-1);
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
									
										
									
									
									/*
									//Choice 2: start generate the quest again
									continue outterloop;
									*/
									
								}
								
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								//If the object isn't Item, look for the next one
								if (!(latestToken.getTokenObject() instanceof Item)) {
									currentTokenNumFromLast++;
									continue;
								}
								

								
								else if (latestToken.getTokenItself()) {
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							//------------2nd select a NPC to receive --------
							
							currentTokenNumFromLast = 1;
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken;
								
								// If == 0; it means last token is reached
								if ( (activateToken.size()-currentTokenNumFromLast) == 0 ) {
									System.out.println("No available character GIVE");
									
									currentTokenNumFromLast = 1;
									option1Active = true;
									
									// If exhaust full token list and no token available, just use the latest NPC token
									latestToken = activateToken.get(activateToken.size()-1);	
									curComponent.setObject(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}
								
								
								latestToken = activateToken.get(currentTokenInt);
								
								//If the object isn't NPC, look for the next one
								if (!(latestToken.getTokenObject() instanceof Character)) {
									currentTokenNumFromLast++;
									continue;
								}
								
								
								else if (latestToken.getTokenItself()) {
									curComponent.setObjectSecondary(latestToken.getTokenObject());
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							break;
							
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//********************************************//
							//******************** CURRENTLY, Exchange use Code from give, without  3rd objective************************//
							//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//						//********************************************//
							//********************************************//
							
						// ONLY [Itself, Item,  2nd = latest NPC token, even after used up?, 3rd = random Item in that NPC?]
						// MainObjective = Item
						// SecondaryObjective = NPC
						// ThirdObjective = Item
						case "exchange" :
						case "take" :
							
							
							//------------1st select a NPC to receive / taken from --------
							
							currentTokenNumFromLast = 1;
							
							//Character selectedChar = new Character();    	15-2-2019							Character selectedChar = null;
							Character selectedChar = null;
							
							while (true) 
							{
								int currentTokenInt = activateToken.size()-currentTokenNumFromLast;
								Token latestToken = new Token();
								
								// If == 0; it means last token is reached
								if ( currentTokenInt == -1 ) {
									System.out.println(" EX");
									currentTokenNumFromLast = 1;

									// If exhaust full token list and no token available, just use the latest NPC token
									// However, if tokensize is only 1, start new quest
									if ((activateToken.size()-1) == -1)
									{
										continue outterloop;
									}
									
									latestToken = activateToken.get(activateToken.size()-1);
									
									// However, if no such NPC or any token exist, start new quest
									if (!(latestToken.getTokenObject() instanceof Character)) {
										continue outterloop;
									}
									
									curComponent.setObjectSecondary(latestToken.getTokenObject());
									selectedChar = (Character) latestToken.getTokenObject();
									curComponent.setTypeOfToken(3);
									break;
								}
								
								latestToken = activateToken.get(currentTokenInt);
								
								
								//If the object isn't NPC, look for the next one
								if (!(latestToken.getTokenObject() instanceof Character)) {
									currentTokenNumFromLast++;
									continue;
								}
								
								
								else if (latestToken.getTokenItself()) {
									curComponent.setObjectSecondary(latestToken.getTokenObject());
									selectedChar = (Character) latestToken.getTokenObject();
									curComponent.setTypeOfToken(3);
									break;
								}

								else {
									//continue loop;
									currentTokenNumFromLast++;
								}
							}
							
							//------------2nd select a item to get from the NPC -----
							
							currentTokenNumFromLast = 1;
							
							
							while (true) 
							{
								ArrayList<Item> listItemRandomChar = selectedChar.getListItem();
								

								
								if (listItemRandomChar.isEmpty()) {
									System.out.println("TAKE / EXCHANGE selected char has no item: start generate the quest again");
									System.out.println("TAKE / EXCHANGE selected char has no item: start generate the quest again");
									// start generate the quest again since the quest doesn't make sense
									continue outterloop;
								}

								else {
									int listSize = listItemRandomChar.size();
									int randomNum = ThreadLocalRandom.current().nextInt(1, listSize + 1);
									
									curComponent.setObject(listItemRandomChar.get(randomNum-1));
									curComponent.setTypeOfToken(3);
								}
							}
							
							
						// IF NULL OR Wait = do nothing
						case "wait" :
						case "NULL" :
							break;
							
						default: 
							System.out.println("Component token distribution: Compoenent doesn't exist: "+currentComponentName);
							break;
					}	
				}
			}
			System.out.println();
			System.out.println("TOKEN BELOW");
			System.out.println();
			for (TreeNode curNode : questTreeLeaves){
				
				System.out.println(curNode);
				
				//Component curComponent = (Component) curNode.getObject();
				
				//-------Debug Check object class-----------
				//
				//if (curComponent.getComponentObject() == null) continue;
				//String aab = curComponent.getComponentObject().getClass().toString();
				//System.out.println(aab);
				
				//-------Debug Check if intanceof work-----------
				//
				//if (curComponent.getComponentObject() instanceof Character) System.out.println("Char");
				//else if (curComponent.getComponentObject() instanceof Item) System.out.println("Item");
				//else if (curComponent.getComponentObject() instanceof Location) System.out.println("Location");
				//else System.out.println("ERROR");
			}
			
			
			
			
			
			
			
			////////////////////////// 6th step, create GameConditions + ALL GameState and Full StateCondition for each Components ////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// Reverse list since the earlier Component must also contain 
			// condition from later Component (so that finishing current Component's actual objective won't make the next Component impossible).
			List<TreeNode> questTreeLeavesReverse = new ArrayList<TreeNode>();
			ListIterator<TreeNode> itrTreeNode = questTreeLeaves.listIterator(questTreeLeaves.size());
			
			while (itrTreeNode.hasPrevious()) {
				questTreeLeavesReverse.add(itrTreeNode.previous());
			}
			// End reverse
			
			
			GameState startConditions = new GameState();
			GameState goalConditions = new GameState();

			// This will store what the GameConditions MUST BE (or NOT BE) so that the action of the component
			// make sense (in [GIVE BERRY to NPC_A], NPC_A must not has Berry in inventory at start so this make sense)
			
//			GameState resolveConditions = new GameState();
			
			// This will store what the GameConditions  MUST ACTUALLY BE (Figure 28 in proposal)
			// Create by Combining both GameStates above.
			GameState restrictionConditions = new GameState();
			
			//This = collect all 'previous GameConditions' that must be met by 'current' Full State Condition
			//REMEMBER >> from back to front, the 'front' condition must contain condition from 'back'.
			//
			// When next FSC is create, this is added in addition to the current condition.
			GameState listDesireRestrictionMainLoop = new GameState();

			
			
			
			
			//Store List<String> from the QuestGeneratorUtility.start/goal/etc() method
			//Will be 1st used to create GameConditions
			ArrayList<String> startConditionList = new ArrayList<String>();
			ArrayList<String> goalConditionList = new ArrayList<String>();
			ArrayList<String> restrictConditionList = new ArrayList<String>();
			
			//Store inputDesire_String + the level of Condition
//			ArrayList<GameCondition> inputDesireCondition = new ArrayList<GameCondition>();
			
			
			int componentLevel = questTreeLeavesReverse.size();
			
//			QuestGeneratorUtility QGU = new QuestGeneratorUtility();

			
			System.out.println("AAA");
			System.out.println("AAA");
			System.out.println("AAA");
			System.out.println(questTreeLeavesReverse.toString());
			
			for (int x = 0; x < questTreeLeavesReverse.size(); x++) {
				TreeNode curNode = questTreeLeavesReverse.get(x);
				System.out.println("NEXT TREENODE");
				Component curCom = (Component) curNode.getObject();
				curCom.setComponentLevel(componentLevel);
				
				// RESET To Avoid shallow copy
				startConditions = new GameState();
				goalConditions = new GameState();
//				resolveConditions = new GameState();
				
				// THIS WILL NOT BE RESET SINCE 
				//restrictionConditions = new GameState();
				
				
				// MUST WRITE TEST CASE THIS
				goalConditionList = QuestGeneratorUtility.CreateGoalCondition(curCom, mainGameWorld);
				startConditionList = QuestGeneratorUtility.CreateStartCondition(curCom, mainGameWorld);
				restrictConditionList = QuestGeneratorUtility.CreateRestriction(curCom, mainGameWorld);
				
				
				if (goalConditionList.contains("ERROR") || startConditionList.contains("ERROR") || restrictConditionList.contains("ERROR"))
				{
					System.out.println ("ERRORR FOUND IN EITHER GOAL/START/RESTRICT");
					System.out.println ("ERRORR FOUND IN EITHER GOAL/START/RESTRICT");
					System.out.println ("Start new quest");
					continue outterloop;
				}
				
				// This is to add goal_Condition to the Component
				for (int y = 0; y < goalConditionList.size(); y++) {
					String str = goalConditionList.get(y);
					if (str == "NULL")
					{
						continue;
					}
					GameCondition GC = QuestGeneratorUtility.CreateGameConditionFromDesire(str,componentLevel);
					goalConditions.addDesire(GC);
				}
				curCom.setGoalGameState(goalConditions);
				
				
				// This is to add start_Condition to the Component
				for (int y = 0; y < startConditionList.size(); y++) {
					String str = startConditionList.get(y);
					if (str == "NULL")
					{
						continue;
					}
					GameCondition GC = QuestGeneratorUtility.CreateGameConditionFromDesire(str,componentLevel);
					startConditions.addDesire(GC);
				}
				curCom.setStartGameState(startConditions);
				
				
				// This is to add restriction_Condition to the Component
				for (int y = 0; y < restrictConditionList.size(); y++) {
					String str = restrictConditionList.get(y);
					if (str == "NULL")
					{
						continue;
					}
					GameCondition GC = QuestGeneratorUtility.CreateGameConditionFromDesire(str,componentLevel);
					// Add to current Component for data storage
					restrictionConditions.addDesire(GC);
					// Add to main list that will contain ALL desire;
					listDesireRestrictionMainLoop.addDesire(GC);
				}
				curCom.setRestrictionStateOnlyItsLevel(restrictionConditions); // This is only for data tracking, not really used except for debug
				
				
				
				GameState FullConditionState = QuestGeneratorUtility.createFullConditionState(listDesireRestrictionMainLoop, curCom);
				
				// If error, it mean the 'createFullConditionState()' above found conflict GameState
				if (FullConditionState.getIsError())
				{
					// Create new quest from start
					System.out.println("Found conflict GameCondition in creating FullConditionState");
					continue outterloop;
				}
				curCom.setFullConditionState(FullConditionState);
				
				
				componentLevel--;
				//
				//
				//
				// --- NOW, convert  ResolveConditions  into  RequiredConditions
				// EX: [give Berry to NPC_A] >>> ResolveCondition = [NPC_A HAS Berry in inventory] >>> RequiredConditions = [NPC_A NOT HAS Berry in inventory]
				//
				//
				//
				
				//GameCondition gc = new GameCondition(str,componentLevel);
				//inputDesireCondition.add(gc);
			
				
			//END FOR LOOP TO PUT GAMECONDITION IN COMPONENT	
			}
			
			
			////////////////////////// 7th step, Display completed Quest ////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			// Now reverse the quest back to normal
			//
			List<TreeNode> questTreeFinal = new ArrayList<TreeNode>();
			ListIterator<TreeNode> itrTreeNode2 = questTreeLeavesReverse.listIterator(questTreeLeavesReverse.size());
			while (itrTreeNode2.hasPrevious()) {
				questTreeFinal.add(itrTreeNode2.previous());
			}
			//
			// DONE REVERSE QUESET BACK
			
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			System.out.println("DONE QUEST: PRINTOUT QUEST BELOW **********");
			
			
			//Printout ALL QUEST
			for (int x = 0; x < questTreeFinal.size(); x++) {
				TreeNode finalTreeNode = questTreeFinal.get(x);
				Component curCom = (Component) finalTreeNode.getObject();
				if (curCom.getComponentName() == "NULL")
				{
					continue;
				}
				System.out.println(curCom.toString());
				System.out.println("-------------------------------");
			}
			
			
			
			
			System.out.println(randomObjectQuestStarter.toString());
			System.out.println(randomNumQuestStarter);
			
			
			
			
			////////////////////////// 8th step, Push to Prolog ////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////
			

		
			//*************  DEBUG  *********************
			
			//(CO,GC,GL,AC,AL,AR,P,PF)
			
			//([],[],[],[],[],[],[],[]).
			//([],[], [[a,b,c,d,e,ID],[a,b,c,d,e,ID],[a,b,c,d,e,ID]] , [] , [] , [] , [] , []).
			
			
			System.out.println();
			
			String gameWorldstr = "(";
			
			gameWorldstr += mainGameWorld.toStringPrologFormatListChar();
			gameWorldstr += "AAAA,AAAA";
			gameWorldstr += mainGameWorld.toStringPrologFormatListLo();
			gameWorldstr += ") END";
			
			
			System.out.println(gameWorldstr);
			
			System.out.println("-----------");
			
			
			for (int x = 0; x < questTreeFinal.size(); x++) {
				TreeNode finalTreeNode = questTreeFinal.get(x);
				Component CCTest = (Component) finalTreeNode.getObject();
				if (CCTest.getComponentName() == "NULL")
				{
					continue;
				}
				String goalState = "";
				goalState = CCTest.getPrologFormatFullCondition();
				System.out.println(goalState);
			}
			//*************  DEBUG  END  ****************
			

			
			//*************  Start Prolog Query  ****************
			
			Component curCom = null;
			boolean isFirstQuery = true;
			String queryValue = "";

			//int numberOfComponent = 0;  // This is used to determine which file is last
			
			String path = "[a,0]"; // The 1st folder name  

			// Get each Component, we are sending to Prolog one Component at a time
			for (int x = 0; x < questTreeFinal.size(); x++) {

//				numberOfComponent = x;

				TreeNode finalTreeNode = questTreeFinal.get(x);
				curCom = (Component) finalTreeNode.getObject();
				if (curCom.getComponentName() == "NULL")
				{
					continue;
				}

				// Start query process......
		        try {
		            PrologProcess process = Connector.newPrologProcess();
		  
		            // fill the factbase
		            fillFactbaseWithDemoData3(process);
				
		            if (isFirstQuery)
			        {	
			            String queryTest  = "startQuestPath(GC,AC,AR,LA,P,PF)";
						
			            queryValue = "startQuestPath(";
						
			            //GC
						queryValue += curCom.getPrologFormatFullCondition();
						queryValue += ",";
						//AC
						queryValue += mainGameWorld.toStringPrologFormatListChar();
						queryValue 	= queryValue.substring(0, queryValue.length()-1);
						queryValue += ",";
						String temp = mainGameWorld.toStringPrologFormatListLo();
						temp 		= temp.substring(1, temp.length());
						queryValue += temp;
						//AR
						queryValue += ",";
						queryValue += mainGameWorld.toStringPrologFormatListRelationship();
						//LA
						queryValue += ",";
						queryValue += "[]";
						//P
						queryValue += ",";
						queryValue += path;
						//PF
						queryValue += ",";
						queryValue += "PF";
						queryValue += ").";
			            
			            
			            PrologSession session = null;
			            try {
			                // create a session for the current process
			                session = process.getSession();
			                // execute a lot of queries in a short period
			                for (int i = 0; i < 1; i++) {
			                	
			                	System.out.println("perform queryOnce");
			                	System.out.println(queryValue); 
								Map<String, Object> resultA = process.queryOnce(queryTest);
					            System.out.println(resultA);
					            //process.queryAll(queryTest);
					            //System.out.println(resultAll); 
			 	
			                	
			                }
			            } catch (PrologProcessException e) {
			                e.printStackTrace();
			            } finally {
			                // make sure to close the session after the queries are finished
			                if (session != null) {
			                    session.dispose();
			                }
			            }
						
						//Map<String, Object> resultA = process.queryOnce(queryTest);
			            //System.out.println(resultA);
			            //process.queryAll(queryTest);
			            //System.out.println(resultAll); 
	
						

			            
			            isFirstQuery = false;
			            
		            }else ////////// Second query and after ///////////////////////////
		            {   
		            	// 1st, get path to the current folder
		    			String folderDirectory = "C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/";
		    			String pathPureString1 = path.replaceAll("[", "");
		    			String pathPureString2 = pathPureString1.replaceAll("]", "");
		    			folderDirectory += pathPureString2;
		    			
		    			// Access the folder and list of all file
		    			File folder = new File(folderDirectory);
		            	ArrayList<String> fileList = listFilesForFolder(folder);
		            	System.out.println(fileList.toString());
		            	
		            	Scanner sc = null;
		            	
		            	// Now, create query for each 
		            	for (String fileName : fileList)
		            	{
		            		sc = new Scanner(new File(fileName));

				            String P = "";
				            String AC = "";
				            String AR = "";
				            String LA = "";
				            
				            //String queryTest  = "startQuestPath(GC,AC,AR,LA,P,PF)";
				            
				            while(sc.hasNextLine()){
				       		P  = (sc.nextLine());
				            AC = (sc.nextLine());
				            AR = (sc.nextLine());
				            LA = (sc.nextLine());
				            }
				            
				            sc.close();
			            	
				            queryValue = "startQuestPath(";
							
				            //GC
							queryValue += curCom.getPrologFormatFullCondition();
							//AC
							queryValue += ",";
							queryValue += AC;
							//AR
							queryValue += ",";
							queryValue += AR;
							//LA
							queryValue += ",";
							queryValue += LA;
							//P
							queryValue += ",";

							
//------------------------------------------------------------------------------------------------------------
//----------------------------CHECK BELOW IF PATH IS ----------------------------------------------------------------------------------			
//-------------------------check if added to most front--------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------
							String alphabet = toAlphabetic(x);
							path = P;
							path += ",";
							path += alphabet;
							queryValue += path;
							
							//PF
							queryValue += ",";
							queryValue += "PF";
							queryValue += ")";
				            
							System.out.println("//////////////////////////////////"); 
							System.out.println(queryValue); 
							System.out.println("//////////////////////////////////"); 
							 
		            	}
			            
		            	
		            }
		            
				
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				
			}
			
			
			
			// DEBUG TEST ON QUERY, only 1 time
			
			System.out.println("-----------");
			
	        try {
	            PrologProcess process = Connector.newPrologProcess();
	 
	            // fill the factbase
	            fillFactbaseWithDemoData3(process);
			
	            String queryTest  = "startQuestPath(GC,GL,AC,AL,AR,LA,P,PF)";
				queryValue = "startQuestPath(";
				
				queryValue += curCom.getPrologFormatFullCondition();
				queryValue += ",";
						
				queryValue += mainGameWorld.toStringPrologFormatListChar();
				queryValue 	= queryValue.substring(0, queryValue.length()-1);
				queryValue += ",";
				String temp = mainGameWorld.toStringPrologFormatListLo();
				temp 		= temp.substring(1, temp.length());
				queryValue += temp;
				queryValue += ",";
				queryValue += mainGameWorld.toStringPrologFormatListRelationship();
				queryValue += ") END";
	            
				//new Character()
				
	            System.out.println(queryValue);
				
	            Map<String, Object> resultA = process.queryOnce(queryTest);
	            System.out.println(resultA);
	            //List<Map<String, Object>> resultAll = process.queryAll(queryTest);
	            //System.out.println(resultAll); 
			
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			
			
			System.out.println("END OF MAIN LOOP");
			break;
			// END OF MAIN WHILE LOOP
		}
		
		
		
		
		
		System.out.println("END OF MAIN BODY");
		// END OF MAIN BODY
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------
	//--------------------------END OF MAIN, START OF METHODS----------------------------------------
	//-----------------------------------------------------------------------------------------------
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	//This will choose template base on the input-number
	//The input-number will be corresponding to the motivation table (Machado, 2017)
	//tempNumber will be double digits
	// 1st digit = What motivation
	// 2nd digit = What Strategy
	public static void generateTemplate(int tempNumber, Template inputTemplate)
	{
		
		ArrayList<String> listComponentRule = new ArrayList<String>();
		TreeNode curTemplateTree = inputTemplate.getComponentTree();
		
		// Motivation = Knowledge
		//Deliver item to study
		
		switch (tempNumber)
		
			{
			case 11:
				//<get> <give> 
				listComponentRule.add(0,"<get>");
				listComponentRule.add(1,"<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Spy
			case 12:
				//<goto> spy <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("spy");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Interview NPC
			case 13:
				//<goto> listen <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("listen");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			//Use item on field
			case 14:
				//<get> <goto> use <give> 
				listComponentRule.add("<get>");
				listComponentRule.add("<goto>");
				listComponentRule.add("use");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			
			// Motivation = Comfort
			//Obtain luxuries 
			case 21:
				//<get> <give> 
				listComponentRule.add("<get>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			//Kill Pests 
			case 22:
				//<goto> <defeat> <report> 	
				listComponentRule.add("<goto>");
				listComponentRule.add("<defeat>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			
			// Motivation = Reputation
			//Obtain rare items 
			case 31:
				//<get> <give> 
				listComponentRule.add("<get>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
	
			
			//Kill enemies 
			case 32:
				//<goto> <defeat> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<defeat>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Visit dangerous place
			case 33:
				//<goto> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			
			
			// Motivation = Serenity
			//Revenge, Justice 
			case 41:
				//<goto> <defeat> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<defeat>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Capture Criminal 
			case 42:
				//<goto> <capture> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<capture>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			
			//Check on NPC (1) 
			case 43:
				//<goto> listen <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<listen>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Check on NPC (2) 
			case 44:
				//<goto> take <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("take");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
	
			//Recover lost/ stolen item 
			case 45:
				//<get> <give> 
				listComponentRule.add("<get>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Rescue NPC 
			case 46:
				//<goto> <rescue> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<rescue>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
	
			
			// Motivation = Protection
			//Attack threatening entities 
			case 51:
				//<goto> <defeat> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<defeat>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Capture Criminal 
			case 52:
				//<goto> <capture> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<capture>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Treat or Repair (l ) 
			case 53:
				//<get> <goto> use <report> 
				listComponentRule.add("<get>");
				listComponentRule.add("<goto>");
				listComponentRule.add("use");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Treat or Repair (2 ) 
			case 54:
				//<goto> repair <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("repair");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Create Diversion (l) 
			case 55:
	
				//<get> <goto> use <report> 
				listComponentRule.add("<get>");
				listComponentRule.add("<goto>");
				listComponentRule.add("use");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Create Diversion (2) 
			case 56:
				//<goto> damage <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("damage");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Assemble fortification 
			case 57:
				//<goto> repair <report>
				listComponentRule.add("<goto>");
				listComponentRule.add("repair");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			
			//Guard Entity 
			case 58:
	
				//<goto> defend <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("defend");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Recruit 
			case 59:
	
				//<goto> listen <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("listen");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
				
			// Motivation = Conquest
			//Attack enemy 
			case 61:
				//<goto> <defeat> <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<defeat>");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			
			//Steal stuff 
			case 62:
				//<goto> <steal> <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<steal>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Recruit 
			case 63:
				//<goto> listen <report> 
				listComponentRule.add("<goto>");
				listComponentRule.add("listen");
				listComponentRule.add("<report>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
				
			// Motivation = Wealth
			// Gather raw materials 
			case 71:
				//<goto> <get> <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<get>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Steal valuables for resale 
			case 72:
				//<goto> <steal> <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("<steal>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Make valuables for resale 
			case 73:
				//<goto> repair <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("repair");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
				
			
			// Motivation = Ability
			// Assemble tool for new skill 
			case 81:
				//<goto> repair use 
				listComponentRule.add("<goto>");
				listComponentRule.add("repair");
				listComponentRule.add("use");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
	
			
			//Obtain training materials 
			case 82:
				//<get> use 
				listComponentRule.add("<get>");
				listComponentRule.add("use");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
	
			//Use existing tools 
			case 83:
				//<goto> use 
				listComponentRule.add("<goto>");
				listComponentRule.add("use");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Practice Combat 
			case 84:
				//<goto> damage 
				listComponentRule.add("<goto>");
				listComponentRule.add("damage");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Practice skill 
			case 85:
				//<goto> use 
				listComponentRule.add("<goto>");
				listComponentRule.add("use");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Research a skill (1) 
			case 86:
				//<get> use 
				listComponentRule.add("<get>");
				listComponentRule.add("use");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Research a skill (2) 
			case 87:
				//<get> experiment 
				listComponentRule.add("<get>");
				listComponentRule.add("experiment");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
				
			// Motivation = Equipment
			// Assemble 
			case 91:
				//<goto> repair <give> 
				listComponentRule.add("<goto>");
				listComponentRule.add("repair");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			// Deliver supplies 
			case 92:
				//<get> <give> 
				listComponentRule.add("<get>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
			
			//Steal supplies 
			case 93:
				//<steal> <give> 
				listComponentRule.add("<steal>");
				listComponentRule.add("<give>");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
				
			//Trade for supplies
			case 94:
				//<get> <goto> exchange 
				listComponentRule.add("<get>");
				listComponentRule.add("<goto>");
				listComponentRule.add("exchange");
				inputTemplate.setTemplateComponent(listComponentRule,curTemplateTree);
				break;
		
			default: 
				System.out.println("Incorrect tempNumber");
				break;
			}
		
	}
	
	//Check if all Component leaves broken down yet.
	//Return True if still non-terminal Component left
	public static boolean breakdownComponent(Template inputTemplate){
		boolean isRuleLeft = false;
		TreeNode rootNode = inputTemplate.getComponentTree();
		ArrayList<TreeNode> listLeaves = new ArrayList<TreeNode>(rootNode.getLeaves());
		
		for (TreeNode curLeaf : listLeaves){
			Component curCom = (Component) curLeaf.getObject();
			String curComName = curCom.getComponentName();
		
			//If contain "<", it meant that this Component isn't terminal yet
			if(curComName.contains("<"))
			{
				ComponentRuleBreakdown(curLeaf);
				isRuleLeft = true;
			}
			else
			{
				//Does nothing, it's already terminal
			}
			
		}
		return isRuleLeft;
	}
	
	
	// OLD VERSION 6-9-2018
	/*
	public static void breakdownComponent(Template inputTemplate){
		List<String> listInputComponentRule = new ArrayList<String>();
		List<String> listOutputComponentRule = new ArrayList<String>();
		String currentInputComponent = "";
		
		listInputComponentRule = inputTemplate.getComponentRule();
		
		for (int x = 0; x<listInputComponentRule.size(); x++)
		{
			currentInputComponent = listInputComponentRule.get(x);
			
			//Check if the current input is ComponentRule or not (sometime Template has terminal Component)
			if(currentInputComponent.contains("<"))
			{
				/////
				/////Finish ComponentRule breakdown 1st
				/////
				/////
			}
			else
			{
				listOutputComponentRule.add(currentInputComponent);
			}
		}
	}
	*/
	
	
	
	//This will received 1 ComponentRule from QuestFrame, then return proper set 
	//of Component.
	//The QuestFrame will be the one who will organized the return
	//of this function & when to consider that all ComponentRule are broken down
	public static void ComponentRuleBreakdown(TreeNode inputNode)
	{
		ArrayList<String> stringOutput = new ArrayList<String>();
		Component curCom = (Component) inputNode.getObject();
		String inputComponentRule = curCom.getComponentName();
		
		// After this process is done, don't forget to use
		// [.removeAll(Collections.singleton(null));] at the caller 
		// to remove all null when create the quest from Components
		
		// Integer for randomizing integer number
		int randomNum;
		
		switch (inputComponentRule)
			{
			
			case "NULL": 
				break;
				
			//0
			case "<QUEST>":
				// nextInt is normally exclusive of the top value,
				// so add 1 to make it inclusive
				
			
				//Temp code for testing **********

				stringOutput.add("<goto>");
				stringOutput.add("<defeat>");
				stringOutput.add("<report>");
				
				//REAL CODE UNFINISH***************
				/*
				 * 
				randomNum = ThreadLocalRandom.current().nextInt(1, 9 + 1);
				
				//<Knowledge> | <Comfort>  | <Reputation> | 
				//<Serenity> |  <Protection> | <Conquest> | 
				//<Wealth> |  <Ability> | <Equipment> 
				
				if (randomNum == 1) {randomNum = ThreadLocalRandom.current().nextInt(1, 9 + 1); } 
				if (randomNum == 2) {}
				if (randomNum == 3) {}
				if (randomNum == 4) {}
				if (randomNum == 5) {}
				if (randomNum == 6) {}
				if (randomNum == 7) {}
				if (randomNum == 8) {}
				if (randomNum == 9) {}
				*
				*/
				
				break;
			
			
			//1-2
			case "<subquest>" :
			
				randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				if (randomNum == 1) {stringOutput.add("NULL"); 
									}
				if (randomNum == 2) {stringOutput.add("<QUEST>"); 
									stringOutput.add("goto"); 
									}
				break;
			
				
			//3-10
			case "<goto>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
				if (randomNum == 1) {stringOutput.add("NULL"); 
									}
				if (randomNum == 2) {stringOutput.add("goto"); 
									}
				if (randomNum == 3) {stringOutput.add("wait"); 
									}
				if (randomNum == 4) {stringOutput.add("explore"); 
									}
				if (randomNum == 5) {stringOutput.add("follow"); 
				}
				if (randomNum == 6) {stringOutput.add("stealth"); 
									}
				if (randomNum == 7) {stringOutput.add("<learn>"); 
									stringOutput.add("<goto>"); 
									}
				if (randomNum == 8) {stringOutput.add("<prepare>"); 
									stringOutput.add("<goto>"); 
									}
				break;
			
			//11-15
			case "<learn>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
				if (randomNum == 1) {stringOutput.add("NULL"); 
									}
				if (randomNum == 2) {stringOutput.add("<goto>"); 
									stringOutput.add("<subquest>"); 
									stringOutput.add("listen"); 
									}
				if (randomNum == 3) {stringOutput.add("<get>"); 
									stringOutput.add("read");
									}
				if (randomNum == 4) {stringOutput.add("<get>"); 
									stringOutput.add("<give>"); 
									stringOutput.add("listen"); 
									}
				if (randomNum == 5) {stringOutput.add("<goto>"); 
									stringOutput.add("<subquest>"); 
									stringOutput.add("examine"); 
									}
				break;
			
			//16
			case "<prepare>" :
				stringOutput.add("<goto>"); 
				stringOutput.add("<subquest>"); 
				break;
				
			//17-22
			case "<get>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
				
				if (randomNum == 1) {stringOutput.add("NULL"); 
									}
				if (randomNum == 2) {stringOutput.add("<steal>");
									}
				if (randomNum == 3) {stringOutput.add("<goto>"); 
									stringOutput.add("gather");
									}
				if (randomNum == 4) {stringOutput.add("<goto>"); 
									stringOutput.add("take"); 
									}
				if (randomNum == 5) {stringOutput.add("<get>"); 
									stringOutput.add("<goto>"); 
									stringOutput.add("exchange"); 
									}
				if (randomNum == 6) {stringOutput.add("<get>"); 
									stringOutput.add("<subquest>");
									}
				break;
			
			//23-24
			case "<steal>":
				randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				
				if (randomNum == 1) {stringOutput.add("<goto>"); 
									stringOutput.add("stealth"); 
									stringOutput.add("take"); 
									}
				if (randomNum == 2) {stringOutput.add("<goto>");
									stringOutput.add("kill"); 
									stringOutput.add("take"); 
									}
				break;
			
			//25-27
			case "<capture>":
				randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
				
				if (randomNum == 1) {stringOutput.add("<goto>"); 
									stringOutput.add("use"); 
									stringOutput.add("capture"); 
									}
				if (randomNum == 2) {stringOutput.add("<goto>");
									stringOutput.add("damage"); 
									stringOutput.add("capture"); 
									}
				if (randomNum == 3) {stringOutput.add("<goto>");
									stringOutput.add("capture"); 
									}
				break;
				
			//28-29
			case "<defeat>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				
				if (randomNum == 1) {stringOutput.add("<goto>"); 
									stringOutput.add("damage"); 
									}
				if (randomNum == 2) {stringOutput.add("<goto>");
									stringOutput.add("kill"); 
									}									
				break;
				
			
			//30-31
			case "<report>" :
			randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			
				if (randomNum == 1) {stringOutput.add("NULL");
									}
				if (randomNum == 2) {stringOutput.add("<goto>");
									stringOutput.add("report"); 
									}	
				break;
			//32-33
			case "<give>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				
				if (randomNum == 1) {stringOutput.add("NULL"); 
									}
				if (randomNum == 2) {stringOutput.add("<goto>");
									stringOutput.add("report"); 
									}	
				break;
			//34-37
			case "<rescue>" :
				randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
				
				if (randomNum == 1) {stringOutput.add("free"); 
									}
				if (randomNum == 2) {stringOutput.add("<defeat>");
									stringOutput.add("free"); 
									}
				if (randomNum == 3) {stringOutput.add("escort");
									}
				if (randomNum == 4) {stringOutput.add("<defeat>");
									stringOutput.add("escort"); 
									}
				break;
			
			default:
				System.out.println(inputComponentRule+": The input Component has illegal systex");
				break;
				
			}
		
		for (String curString : stringOutput)
		{
			Component newCom = new Component(curString);
			TreeNode newNode = new TreeNode(newCom);
			inputNode.add(newNode);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/* Obsolete code since 6-9-2018, after implement token system.
	
	
	//// Assign proper 'object' into Component according to the 'ComponentName' (ComponentRule)
	public void ComponentRuleBreakdown(TreeNode inputNode)
	{
		Object installingObject = null;
		Object installingObjectSecondary = null;
		int randomNum;
		Component currentCom = (Component)inputNode.getObject();
		
		
		//Some Component are just 'empty' that does nothing and only exist to breakdown
		//into other <Rule>, these Component's object will just took object from parent
		//to pass on to its children.
		Component parentCom = (Component)inputNode.getParent().getObject();
		
		switch (currentCom.getComponentName())
			{
			//////////////////////// 1st set, ComponentRule (Terminal Component later)
		
			case "NULL": 
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
					
			//0
			case "<QUEST>":
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
			
			//1-2
			case "<subquest>" :
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
			
				
			//3-10
			case "<goto>" :
				//If parentObject isn't Location, must choose random location
				if (parentCom.getComponentObject() instanceof Location)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it's a item, just get the item's location
				else if(parentCom.getComponentObject() instanceof Item)
				{
					Item curItem = (Item) parentCom.getComponentObject();
					String curItemName = curItem.getName();
					ArrayList<Location> curListLocation = mainGameWorld.getListLocation();
					
					for (Location curLocation : curListLocation)
					{
						if (curLocation.isSpecificItemExist(curItem) || curLocation.isItemWithThisNameExist(curItemName))
						{
							installingObject = parentCom.getComponentObject();
							installingObjectSecondary = parentCom.getComponentObjectSecondary();
						}
						//IF no location contain the item, just choose random location?
						else 
						{
							randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListLocation().size()+1);
							installingObject = mainGameWorld.getListLocation().get(randomNum);
							installingObjectSecondary = parentCom.getComponentObjectSecondary();
						}
					}
				}
				//
				//Else, just choose a random location?????
				//**
				//**THIS HAVE TO BE REWRITE 
				//**
				//**GameWorld has to be rewrite, so that Character also 'store' in Location
				//**
				//**
				else {
				randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListLocation().size()+1);
				installingObject = mainGameWorld.getListLocation().get(randomNum);
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				break;
			
				
			//11-15
			case "<learn>" :
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
			
			//16
			case "<prepare>" :
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
				
			//17-22
			case "<get>" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it isn't a item, just a random item in random location
				else 
				{
					
					randomNum = ThreadLocalRandom.current().nextInt(1, mainGameWorld.getListLocation().size()+1);
					Location randomizedLocation = mainGameWorld.getListLocation().get(randomNum);
					
					int randomNum2 = ThreadLocalRandom.current().nextInt(1, randomizedLocation.getListItem().size()+1);
					Item randomizedItem = randomizedLocation.getListItem().get(randomNum2);
					installingObject = randomizedItem;
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				break;
			
			//23-24
			case "<steal>":
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it isn't a item, just a random item processed by the character
				else if (parentCom.getComponentObject() instanceof Character)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a item or character, select a random item???
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*
					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}

				break;
			
			//25-27
			case "<capture>":
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//just select a random character from the location
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after location's character finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a item or character, select a random character???
				else 
				{
					//*
					//*TO BE CODED after location's character finish
					//*
					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
			//28-29
			case "<defeat>" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//just select a random character from the location
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after location's character finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a item or character, select a random character???
				else 
				{
					//*
					//*TO BE CODED after location's character finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}								
				break;
				
			
			//30-31
			case "<report>" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//just select a random character from the location
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after location's character finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a item or character, select a random character???
				else 
				{
					//*
					//*TO BE CODED after location's character finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}								
			break;	
			
			//32-33
			case "<give>" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is character, select a random item to give to that character?
				else if (parentCom.getComponentObject() instanceof Character)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a item or character, select a random item???
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
			//34-37
			case "<rescue>" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, rescue a random character?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
			
				
				//////////////////////// 2st set, Terminal Component /////////////////////
				
			//Capture X
			case "capture" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, capture a random character there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a totally random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Damage X	
			case "damage" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select a random character there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a totally random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;

				
			//Defend X
			case "defend" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select a random character there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a totally random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
			//Escort X
			case "escort" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select a random character there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a totally random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
			//Examine X, Y
			case "examine" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select a random OBJECT there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it isn't a character or location, select a totally random item?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
			//Exchange X|Y, Z
			case "exchange" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is item, trade it?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
					//*
					//*TO BE CODED after character'item inventory finish
					//*
				}
				//If it isn't a character or location, select a totally random character?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
			
			//Experiment X, Y
			case "experiment" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select a random OBJECT there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Character, select a totally random item?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
			
				
			//Explore X
			case "explore" :
				if (parentCom.getComponentObject() instanceof Location)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select the location that the item is?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Character, select a totally random item?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Follow X
			case "follow" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select the character that own it?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Location, select a totally random character there?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Free X
			case "free" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random prisoner?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Item, select a totally random prisoner somewhere?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Gather X
			case "gather" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random item?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Item, select a totally random prisoner somewhere?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Give X, Y
			case "give" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Character, select a random item?
				else if (parentCom.getComponentObject() instanceof Character)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Location, select random character there?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Goto X
			case "goto" :
				if (parentCom.getComponentObject() instanceof Location)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select the location that the item exist?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is CHaracter, select the location taht the item exist?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Kill X
			case "kill" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random character?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Item, select the owner of the item?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Listen X, Y
			case "listen" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random character?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Item, select the owner of the item?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Read X, Y
			case "read" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random item that give info?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Character, select the random item it own?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Repair X
			case "repair" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random broken item?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Character, select the random item it own?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Report X, Y
			case "report" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random character?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Item, select owner?? or just select root quest giver?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Spy X, Y
			case "spy" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select that item owner?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Location, select random character there?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Stealth X
			case "stealth" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select that item owner?
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Location, select random character there?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Take X, Y
			case "take" :
				if (parentCom.getComponentObject() instanceof Character)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Item, select that item owner
				else if (parentCom.getComponentObject() instanceof Item)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Location, select random character there and random item they own?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//Use X
			case "Use" :
				if (parentCom.getComponentObject() instanceof Item)
				{
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();
				}
				//If it is Location, select random item there?
				else if (parentCom.getComponentObject() instanceof Location)
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				//If it is Character, just select him?
				else 
				{
					//*
					//*TO BE CODED after character'item inventory finish
					//*					
					// Temp code to just pass object to child to avoid null
					installingObject = parentCom.getComponentObject();
					installingObjectSecondary = parentCom.getComponentObjectSecondary();

				}
				break;
				
				
			//wait
			case "wait" :
				installingObject = parentCom.getComponentObject();
				installingObjectSecondary = parentCom.getComponentObjectSecondary();
				break;
	
				
			default:
				System.out.println("The input Component has illegal systex");
				installingObject = null;
				installingObjectSecondary = null;
				break;
				
			}
		
		currentCom.setObject(installingObject);
		currentCom.setObjectSecondary(installingObjectSecondary);
		inputNode.setObject(currentCom);
	}
	
	*/

	
	
	
	
	
	
	
	
	
	
	//---------------------------Utility Method()--------------------------------------//
	//---------------------------Utility Method()--------------------------------------//

	public static String toAlphabetic(int i) {
	    if( i<0 ) {
	        return "-"+toAlphabetic(-i-1);
	    }

	    int quot = i/26;
	    int rem = i%26;
	    char letter = (char)((int)'A' + rem);
	    if( quot == 0 ) {
	        return ""+letter;
	    } else {
	        return toAlphabetic(quot-1) + letter;
	    }
	}
	
	
    public static ArrayList<String> listFilesForFolder(final File folder) {
    	ArrayList<String> returnSTR = new ArrayList<String>();
    	
	    for (final File fileEntry : folder.listFiles()){
	        if (fileEntry.isDirectory()){
	            listFilesForFolder(fileEntry);
	        } 
	        else{
	        	returnSTR.add(fileEntry.getName());
	            //System.out.println(fileEntry.getName());
	        }
	    }
	    return returnSTR;
    }
	
	
    public static ArrayList<String> findFoldersInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
    	
        FileFilter directoryFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
    		
        File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
        ArrayList<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
        for (File directoryAsFile : directoryListAsFile) {
            foldersInDirectory.add(directoryAsFile.getName());
        }

        return foldersInDirectory;
    }
    
	//---------------------------Prolog Connector ()--------------------------------------//

    private static void fillFactbaseWithDemoData3(PrologProcess process) throws PrologProcessException, FileNotFoundException {

        String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/TestPrologPDT/QuestGeneratorMain.pl'");
        process.queryOnce(consultQuery);
    }

    
}
