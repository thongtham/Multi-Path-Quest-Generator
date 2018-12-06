package mainGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import templateComponent.QuestFrame;
import templateComponent.Template;


//This class will generate QuestFrame, then template, then breakdown
//the <Component> into Component.
public class QuestGenerator {

	

	public QuestGenerator()
	{
		
	}
	
	//This will choose template base on the input-number
	//The input-number will be corresponding to the motivation table (Machado, 2017)
	//tempNumber will be double digits
	// 1st digit = What motivation
	// 2nd digit = What Strategy
	public void generateTemplate(int tempNumber, Template inputTemplate)
	{
		
		ArrayList<String> listComponentRule = new ArrayList<String>();
		
		// Motivation = Knowledge
		//Deliver item to study
		if (tempNumber == 11)
		{
			//<get> <give> 
			listComponentRule.add(0,"<get>");
			listComponentRule.add(1,"<give>");
			inputTemplate.setTemplateComponent(listComponentRule);
			
		}
		//Spy
		else if (tempNumber == 12)
		{
			//<goto> spy <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("spy");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);
		
		}
		//Interview NPC
		else if (tempNumber == 13)
		{
			//<goto> listen <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("listen");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Use item on field
		else if (tempNumber == 14)
		{
			//<get> <goto> use <give> 
			listComponentRule.add("<get>");
			listComponentRule.add("<goto>");
			listComponentRule.add("use");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		
		
		// Motivation = Comfort
		//Obtain luxuries 
		else if (tempNumber == 21)
		{
			//<get> <give> 
			listComponentRule.add("<get>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);
		
		}
		//Kill Pests 
		else if (tempNumber == 22)
		{
			//<goto> <defeat> <report> 	
			listComponentRule.add("<goto>");
			listComponentRule.add("<defeat>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		
		
		// Motivation = Reputation
		//Obtain rare items 
		else if (tempNumber == 31)
		{
			//<get> <give> 
			listComponentRule.add("<get>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);


		}
		//Kill enemies 
		else if (tempNumber == 32)
		{
			//<goto> <defeat> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<defeat>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Visit dangerous place
		else if (tempNumber == 33)
		{
			//<goto> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		
		
		// Motivation = Serenity
		//Revenge, Justice 
		else if (tempNumber == 41)
		{
			//<goto> <defeat> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<defeat>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Capture Criminal 
		else if (tempNumber == 42)
		{
			//<goto> <capture> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<capture>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Check on NPC (1) 
		else if (tempNumber == 43)
		{
			//<goto> listen <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<listen>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Check on NPC (2) 
		else if (tempNumber == 44)
		{
			//<goto> take <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("take");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);


		}
		//Recover lost/ stolen item 
		else if (tempNumber == 45)
		{
			//<get> <give> 
			listComponentRule.add("<get>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Rescue NPC 
		else if (tempNumber == 46)
		{
			//<goto> <rescue> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<rescue>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		
		
		// Motivation = Protection
		//Attack threatening entities 
		else if (tempNumber == 51)
		{
			//<goto> <defeat> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<defeat>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Capture Criminal 
		else if (tempNumber == 52)
		{
			//<goto> <capture> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<capture>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Treat or Repair (l ) 
		else if (tempNumber == 53)
		{
			//<get> <goto> use <report> 
			listComponentRule.add("<get>");
			listComponentRule.add("<goto>");
			listComponentRule.add("use");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Treat or Repair (2 ) 
		else if (tempNumber == 54)
		{
			//<goto> repair <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("repair");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Create Diversion (l) 
		else if (tempNumber == 55)
		{

			//<get> <goto> use <report> 
			listComponentRule.add("<get>");
			listComponentRule.add("<goto>");
			listComponentRule.add("use");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Create Diversion (2) 
		else if (tempNumber == 56)
		{
			//<goto> damage <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("damage");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}		
		//Assemble fortification 
		else if (tempNumber == 57)
		{
			//<goto> repair <report>
			listComponentRule.add("<goto>");
			listComponentRule.add("repair");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Guard Entity 
		else if (tempNumber == 58)
		{

			//<goto> defend <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("defend");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Recruit 
		else if (tempNumber == 59)
		{

			//<goto> listen <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("listen");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
			
		}		
		
		// Motivation = Conquest
		//Attack enemy 
		else if (tempNumber == 61)
		{
			//<goto> <defeat> <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<defeat>");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Steal stuff 
		else if (tempNumber == 62)
		{

			//<goto> <steal> <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<steal>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Recruit 
		else if (tempNumber == 63)
		{

			//<goto> listen <report> 
			listComponentRule.add("<goto>");
			listComponentRule.add("listen");
			listComponentRule.add("<report>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		
		// Motivation = Wealth
		// Gather raw materials 
		else if (tempNumber == 71)
		{
			//<goto> <get> <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<get>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Steal valuables for resale 
		else if (tempNumber == 72)
		{

			//<goto> <steal> <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("<steal>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Make valuables for resale 
		else if (tempNumber == 73)
		{

			//<goto> repair <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("repair");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}		
		
		// Motivation = Ability
		// Assemble tool for new skill 
		else if (tempNumber == 81)
		{
			//<goto> repair use 
			listComponentRule.add("<goto>");
			listComponentRule.add("repair");
			listComponentRule.add("use");
			inputTemplate.setTemplateComponent(listComponentRule);


		}
		//Obtain training materials 
		else if (tempNumber == 82)
		{
			//<get> use 
			listComponentRule.add("<get>");
			listComponentRule.add("use");
			inputTemplate.setTemplateComponent(listComponentRule);


			
		}
		//Use existing tools 
		else if (tempNumber == 83)
		{
			//<goto> use 
			listComponentRule.add("<goto>");
			listComponentRule.add("use");
			inputTemplate.setTemplateComponent(listComponentRule);

			

		}
		//Practice Combat 
		else if (tempNumber == 84)
		{
			//<goto> damage 
			listComponentRule.add("<goto>");
			listComponentRule.add("damage");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Practice skill 
		else if (tempNumber == 85)
		{
			//<goto> use 
			listComponentRule.add("<goto>");
			listComponentRule.add("use");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Research a skill (1) 
		else if (tempNumber == 86)
		{
			//<get> use 
			listComponentRule.add("<get>");
			listComponentRule.add("use");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Research a skill (2) 
		else if (tempNumber == 87)
		{
			//<get> experiment 
			listComponentRule.add("<get>");
			listComponentRule.add("experiment");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		
		// Motivation = Equipment
		// Assemble 
		else if (tempNumber == 91)
		{
			//<goto> repair <give> 
			listComponentRule.add("<goto>");
			listComponentRule.add("repair");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		// Deliver supplies 
		else if (tempNumber == 92)
		{

			//<get> <give> 
			listComponentRule.add("<get>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

		}
		//Steal supplies 
		else if (tempNumber == 93)
		{

			//<steal> <give> 
			listComponentRule.add("<steal>");
			listComponentRule.add("<give>");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		//Trade for supplies
		else if (tempNumber == 94)
		{

			//<get> <goto> exchange 
			listComponentRule.add("<get>");
			listComponentRule.add("<goto>");
			listComponentRule.add("exchange");
			inputTemplate.setTemplateComponent(listComponentRule);

			
		}
		else 
		{
			System.out.println("Incorrect tempNumber");
		}
		
		
	}
	
	
	//This will choose template base on the input-number
	//The input-number will be corresponding to the motivation table (Machado, 2017)
	//tempNumber will be double digits
	// 1st digit = What motivation
	// 2nd digit = What Strategy
	public void GenerateComponent(Template inputTemplate)
	{
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
				/////Finish Component breakdown 1st
				/////
				/////
			}
			else
			{
				listOutputComponentRule.add(currentInputComponent);
			}
				
			
		}
		
	}
	
	//This will received 1 ComponentRule from QuestFrame, then return proper set 
	//of Component.
	//The QuestFrame will be the one who will organized the return
	//of this function & when to consider that all ComponentRule are broken down
	public ArrayList<String> ComponentRuleBreakdown(String inputComponentRule)
	{
		ArrayList<String> stringOutput = new ArrayList<String>();

		// After this process is done, don't forget to use
		// [.removeAll(Collections.singleton(null));] at the caller 
		// to remove all null when create the quest from Components
		if (inputComponentRule == "NULL")
		{
			return null;
		}
		
		//0
		else if (inputComponentRule == "<QUEST>")
		{

			
			// nextInt is normally exclusive of the top value,
			// so add 1 to make it inclusive
			int randomNum = ThreadLocalRandom.current().nextInt(1, 9 + 1);
			
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
			
			
		}
		
		//1-2
		else if (inputComponentRule == "<subquest>")
		{
			int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			if (randomNum == 1) {stringOutput.add("NULL"); 
								}
			if (randomNum == 2) {stringOutput.add("<QUEST>"); 
								stringOutput.add("goto"); 
								}
		}
		
		//3-10
		else if (inputComponentRule == "<goto>")
		{
			int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
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
			if (randomNum == 6) {stringOutput.add("<stealth"); 
								}
			if (randomNum == 7) {stringOutput.add("<learn>"); 
								stringOutput.add("<goto>"); 
								}
			if (randomNum == 8) {stringOutput.add("<prepare>"); 
								stringOutput.add("<goto>"); 
								}
		}
		//11-15
		else if (inputComponentRule == "<learn>")
		{
			
			
			int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
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

		}
		//16
		else if (inputComponentRule == "<prepare>")
		{
			stringOutput.add("<goto>"); 
			stringOutput.add("<subquest>"); 
		}
		//17-22
		else if (inputComponentRule == "<get>")
		{
			
			int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
			
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
		}
		//23-24
		else if (inputComponentRule == "<steal>")
		{
			
			int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			
			if (randomNum == 1) {stringOutput.add("<goto>"); 
								stringOutput.add("stealth"); 
								stringOutput.add("take"); 
								}
			if (randomNum == 2) {stringOutput.add("<goto>");
								stringOutput.add("<kill>"); 
								stringOutput.add("take"); 
								}

		}
		//25-27
		else if (inputComponentRule == "<capture>")
		{
			
			int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
			
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
		}
		//28-29
		else if (inputComponentRule == "<defeat>")
		{
			int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			
			if (randomNum == 1) {stringOutput.add("<goto>"); 
								stringOutput.add("damage"); 
								}
			if (randomNum == 2) {stringOutput.add("<goto>");
								stringOutput.add("kill"); 
								}									
			
		}
		//30-31
		else if (inputComponentRule == "<report>")
		{
			int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			
			if (randomNum == 1) {stringOutput.add("NULL");
								}
			if (randomNum == 2) {stringOutput.add("<goto>");
								stringOutput.add("report"); 
								}	
		}
		//32-33
		else if (inputComponentRule == "<give>")
		{
			int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
			
			if (randomNum == 1) {stringOutput.add("NULL"); 
								}
			if (randomNum == 2) {stringOutput.add("<goto>");
								stringOutput.add("report"); 
								}	
		}
		//34-37
		else if (inputComponentRule == "<rescue>")
		{

			int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
			
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
		}
		else 
		{
			System.out.println("The input Component has illegal systex");
		}
		return stringOutput;
	}
	
	
	
	
	
	
	
}
