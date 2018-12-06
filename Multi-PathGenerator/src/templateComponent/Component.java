package templateComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import enviroment.Relationship;
import enviroment.GameState;
import enviroment.GameWorld;

import templateComponent.AtomAction;
import templateComponent.AtomItem;
import templateComponent.AtomParent;
import templateComponent.Component;
import templateComponent.QuestFrame;
import templateComponent.Template;

import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Skill;
import gameObject.Status;

public class Component {
	
	String componentName;
	GameState startState;  	// Null startState = anything OK
	GameState goalState;
	
	
	
	
	public Component(){
		
	}
	
	public Component(String name)
	{
		componentName = name;
	}
	
	public void setStartGameState(GameState newStartState) 
	{
		startState = newStartState;
	}
	
	public void setGoalGameState(GameState newGoalState) 
	{
		goalState = newGoalState;
	}
	
	public GameState getStartGameState()
	{
		return startState;
	}
	public GameState getGoalGameState()
	{
		return goalState;
	}
	
	
	//If [targetName] die = complete
	public void setComponentAs_SimpleKill(String targetCharName, String Location)
	{	
		//Set Start state
		componentName = "Simple Kill Component";
		GameState GS = new GameState();
		//////
		Character target = new Character();
		target.setToGenericHuman(targetCharName, Location);
		//////
		GS.addChar(target);
		
		Relationship testRelation = new Relationship();
		testRelation.setRelationship("jack", "jill", "friend");
		GS.addRelationship(testRelation);

		startState = GS;
		
		//Set goal state
		GS = new GameState();
		//////
		target = new Character();
		target.setToGenericHuman(targetCharName, Location);
		target.setIsAlive(false);
		//////
		GS.addChar(target);
		componentName = "SimpleKill";
		goalState = GS;
		
		//System.out.println("set Simple Kill");
	}
	
	//Continue from [after targetName die]; 
	public void setComponentAs_SimpleKill_Part2(String targetCharName, String Location)
	{	
			//still no idea
	}
	
	
	
	
	public void setComponentAs_SimpleHeal(String targetCharName, String Location)
	{
		//Set StartState
		componentName = "Simple Heal Component";
		GameState GS = new GameState();
		Character target = new Character();
		target.setToGenericHuman(targetCharName, Location);
		target.setIsAlive(true);
		target.addStatus("status("+target.getName()+",sick)");
		GS.addChar(target);
		componentName = "SimpleHeal";
		startState = GS;
		
		//Set GoalState
		target.removeStatus("status("+target.getName()+",sick)");
		GS.setChar(target);
		goalState = GS;
	}
	
	public void setComponentAs_ClearHandKill(String targetCharName)
	{

	}
	
	@Override
	public String toString()
	{
		String strReturn = "*";
		strReturn += componentName;
		strReturn += "*";
		strReturn += "(START:";
		strReturn += startState.toString();
		strReturn += ")(GOAL:";
		strReturn += goalState.toString();
		strReturn += ")";
		return strReturn;
	}
	
	
	
}
