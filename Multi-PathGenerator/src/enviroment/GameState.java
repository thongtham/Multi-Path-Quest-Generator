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
import enviroment.Relationship;

import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Skill;
import gameObject.Status;

import templateComponent.AtomAction;
import templateComponent.AtomItem;
import templateComponent.AtomParent;
import templateComponent.Component;
import templateComponent.QuestFrame;
import templateComponent.Template;

//Everything not stated in this GameState == "not care" and can be anything

//All list have to be converted into 'prolog form' before doing quarry.
//
//Relationship >> 					relationship(etc...) >> [Then append to main]
//listCharacter.friendlist >> 		relationship(friend,char,target) >> [Then add to main relationship]
//listCharacter.ability >> 			ability(ability,char)
//listCharacter.item >> 			item(item,char)
//listItemOnLand >> 				item(item,no_owner)

public class GameState {
	
	ArrayList<Relationship> listRelationship = new ArrayList<Relationship>();
	ArrayList<Character> listCharacter = new ArrayList<Character>();
	ArrayList<Item> listItemOnLand = new ArrayList<Item>();
	
	public GameState() 
	{

	}
	
	public GameState(ArrayList<Relationship> newListRelationship, ArrayList<Character> newListCharacter, ArrayList<Item> newListItemOnLand) 
	{
		listRelationship = newListRelationship;
		listCharacter = newListCharacter;
		listItemOnLand = newListItemOnLand;
	}
	
	// This will (re)set the GameState to a basic one 
	// for either testing/debugging or quick setup
	
	public void setBasicNewGameState()
	{
		listCharacter = new ArrayList<Character>();
		listRelationship = new ArrayList<Relationship>();
		listItemOnLand = new ArrayList<Item>();
		
		
		//Item
		Item newItem = new Item();
		
		newItem.setItemOnGround("food", "provision");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("water", "provision");
		listItemOnLand.add(newItem);
		
		newItem.setItemOnGround("weapon", "equipment");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("armor", "equipment");
		listItemOnLand.add(newItem);
		
		newItem.setItemOnGround("potion", "medicine");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("bondage", "medicine");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("alcohol", "medicine");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("tonic", "medicine");
		listItemOnLand.add(newItem);
		
		newItem.setItemOnGround("gem", "luxury");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("gold", "luxury");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("fancyWare", "luxury");
		listItemOnLand.add(newItem);
		
		newItem.setItemOnGround("book", "information");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("letter", "information");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("note", "information");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("document", "information");
		listItemOnLand.add(newItem);
		newItem.setItemOnGround("tape", "information");
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
		     if (next.equals(target)) 
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
		     if (next.equals(target)) 
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
	
	
	
	
	///////////Below = override Java Utility methods/////////////////////////////
	
	
	
	public String toString()
	{

			String strReturn = "";
			strReturn += "RELATIONSHIP:{";
			String result = listRelationship.stream().map(Object::toString).collect(Collectors.joining(", "));
			strReturn += result;
			strReturn += "}";
			strReturn += ",CHARACTER{";
			result = listCharacter.stream().map(Object::toString).collect(Collectors.joining(", "));
			strReturn += result;
			strReturn += "}";
			strReturn += ",ITEM{";
			result = listItemOnLand.stream().map(Object::toString).collect(Collectors.joining(", "));
			strReturn += result;
			strReturn += "}";
			return strReturn;

		
	}
	
	
}
