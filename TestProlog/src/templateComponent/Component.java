package templateComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.pengyifan.commons.collections.tree.TreeNode;

import enviroment.GameCondition;
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
import gameObject.Relationship;
import gameObject.Skill;
import gameObject.Status;

public class Component {
	
	//componentName example.... [<goto>, goto, <report>, etc.]
	String componentName;
	
	
	//start & goal state will not be created until the whole quest tree is completed
	//During breakdown, only the componentName (<goto>, etc.) && componentObject will only and created.
	GameState startState;  	// Null startState = anything OK
	GameState goalState;

	GameState restrictionState;
	GameState fullConditionState;

	
	GameState restrictionGameConditionList; // FOR DEBUG ONLY // List for the Restriciton Condition that is created by this Componene alone.
	
	
	//Preorder-Traversal position of this Component
	//Created after the whole root is completed.
	int positionInTree;
	
	//Used in Creating FullConditionState
	int componentLevel;
	
	
	// 1 = Info
	// 2 = Loc
	// 3 = Itself
	// 4 = Examine = empty >>> the Object also empty 
	int typeOfToken;
	
	//Object of Token
	Object componentObject;
	
	//Mostly for 'Exchange' & 'give' , since they required the component to indicate which NPC is the 'trader'.
	Object componentObjectSecondary;
	
	//Mostly for 'Exchange', since it need to know what item to trade for.
	Object componentObjectThird;
	

	
	
	// OBSOLETE 6-9-2018, just check if componentName contain "<" or ">" is easier;
	/*
	boolean isTerminal;         
	*/
	
	
	//-----------OBSOLETE BELOW-----------
	
	// Contain the object (Item/Character/Location/Relationship) that are the main 
	// subject of ***goalState***
	// [ComponentObject + componentName] = goalState
	// v
	//Object componentObject;
	
	// componentObject = always object that are interacted with (item that are given, etc.)
	// componentObjectSecondary = the receiver or actor (ALWAYS Character)
	// ***NOT ALL Component has this.
	// v
	//Object componentObjectSecondary;
	
	//-----------OBSOLETE ABOVE-----------
	
	
	
	
	
	
	public Component(){
		//isTerminal = false;
		
		startState = new GameState(); 
		goalState= new GameState(); 
		restrictionState= new GameState(); 
		fullConditionState= new GameState(); 
	}
	
	public Component(String inputName){
		componentName = inputName;
		//isTerminal = false;
		
		startState = new GameState(); 
		goalState= new GameState(); 
		restrictionState= new GameState(); 
		fullConditionState= new GameState(); 
	}
	
	

	public Component(String name,Object inputObject, Object inputObject2,Object inputObject3)
	{
		componentName = name;
		
		if (name.contains("<")) {
			//isTerminal = false;
		}
		else {
			//isTerminal = true;
		}
		
		componentObject = inputObject;
		componentObjectSecondary = inputObject2;
		componentObjectThird = inputObject3;
		
		startState = new GameState(); 
		goalState= new GameState(); 
		restrictionState= new GameState(); 
		fullConditionState= new GameState(); 
	}
	
	
	public Component(String name,Object inputObject,GameState inputStart,GameState inputGoal)
	{
		componentName = name;
		
		if (name.contains("<")) {
			//isTerminal = false;
		}
		else {
			//isTerminal = true;
		}
		
		
		componentObject = inputObject;
		startState = inputStart;
		goalState = inputGoal;
		
		restrictionState= new GameState(); 
		fullConditionState= new GameState(); 
		
	}
	
	
	
	/////////////// Set basic //////////////
	
	public void setStartGameState(GameState newStartState) 
	{
		startState = newStartState;
	}
	
	public void setGoalGameState(GameState newGoalState) 
	{
		goalState = newGoalState;
	}
	public void setRestrictionState(GameState newState) 
	{
		restrictionState = newState;
	}
	public void setRestrictionStateOnlyItsLevel (GameState newState)
	{
		restrictionGameConditionList = newState;
	}
	
	public void setFullConditionState(GameState newState) 
	{
		fullConditionState = newState;
	}
	
	
	
	
	public void setObject(Object inputOb)
	{
		componentObject = inputOb;
	}
	
	public void setObjectSecondary(Object inputOb)
	{
		componentObjectSecondary = inputOb;
	}
	
	public void setPreorderNumber(int inputPosition)
	{
		positionInTree = inputPosition;
	}
	
	public void setTypeOfToken(int inputType)
	{
		typeOfToken = inputType;
	}
	
	public void setComponentLevel(int input)
	{
		componentLevel = input;
	}
	
	////////////////////////////////////////////
	
	
	///////////////////TREE ////////////////////
	
	/*
	 * 
	public void addTreeChild(Object newChild)
	{
	//	TreeNode newChildNode = new TreeNode(newChild);
	//	componentTree.add(newChildNode);
	}

	
	public TreeNode getComponentTree(){
		return componentTree;
	}
	
	//This will return only the list of all Component Name (terminal and non-terminal)
	public ArrayList<String> getComponentRule(){
		
		
		ArrayList<String> listComponentRule = componentTree.getLeaves();
		
		return listComponentRule;
	}
	
	*
	*/
	
	
	
	///////////////////////////////////////	
	
	
	///////////////////get basic //////////
	
	public GameState getStartGameState()
	{
		return startState;
	}
	public GameState getGoalGameState()
	{
		return goalState;
	}
	public GameState getrestrictionState()
	{
		return restrictionState;
	}
	public GameState getfullConditionState()
	{
		return fullConditionState;
	}
	
	
	
	public String getComponentName()
	{
		return componentName;
	}
	
	public Object getComponentObject()
	{
		return  componentObject;
	}
	
	public Object getComponentObjectSecondary()
	{
		return componentObjectSecondary;
	}
	
	public Object getComponentObjectThird()
	{
		return componentObjectThird;
	}
	
	public int getPreorderNumber()
	{
		return positionInTree;
	}
	
	public int gettypeOfToken()
	{
		return typeOfToken;
	}
	
	public int getComponentLevel()
	{
		return componentLevel;
	}
	/////////////////////////////
	
	
	
	
	
	
	

	
	@Override
	public String toString()
	{
		String strReturn = "[";
		strReturn += componentName;
		strReturn += ", Position: "+positionInTree;
		strReturn += ", Type of Token: "+ typeOfToken;
		strReturn += ", Object:" +componentObject;
		strReturn += "]";
		strReturn += "\n";
		strReturn += "START_STATE:";
		strReturn += startState.toString();
		strReturn += "\n";
		strReturn += "GOAL_STATE:";
		strReturn += goalState.toString();
		strReturn += "\n";
		strReturn += "RESTRICTION_STATE:";
		strReturn += restrictionState.toString();
		strReturn += "\n";
		strReturn += "FULL-CONDITION_STATE:";
		strReturn += fullConditionState.toString();
		strReturn += "\n";
		
		
		return strReturn;
	}
	
	
	
	/*   OLD CODE
	 * 
	 * 
	
	//If [targetName] die = complete
	public void setComponentAs_SimpleKill(String targetCharName, String Location)
	{	
		//Set Start state
		componentName = "Simple Kill Component";
		GameWorld GS = new GameWorld();
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
		GS = new GameWorld();
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
		GameWorld GS = new GameWorld();
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
	
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
}
