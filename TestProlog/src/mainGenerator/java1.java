package mainGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

class java1 {
	
	static QuestFrame mainQuestFrame = new QuestFrame();
	static GameState currentGameState = new GameState();
	
	public static void main (String args[]) throws FileNotFoundException
	{
		System.out.println("HHH");
		List<String> Stringoutput = new ArrayList<String>();
		System.out.println(Stringoutput);
		Stringoutput = null;
		System.out.println(Stringoutput);
		//Template testTemplate = new Template();
		//testTemplate.setTemplateConquest_Attack_Enemy();
		//System.out.println(testTemplate.toString());
		
		
		//currentGameState.setNewGameState();
		//System.out.println(currentGameState.ToString());
		
		
		//Character target = new Character();
		//target.setToGenericHuman("kkk", "karket");
		//target.setIsAlive(false);
		//System.out.println(target);
		//System.out.println("");
	
		//boolean target = true;
		//System.out.println(target);
		
		
		//Component newCom = new Component();
		//newCom.setComponentAs_SimpleKill("jack", "Market");
		//System.out.println(newCom.getStartGameState().ToString());
		//System.out.println(newCom.getStartGameState().getCharListAsString());
		//System.out.println(newCom.getGoalGameState().ToString());
		//System.out.println(newCom.ToString());
	
	
	
	}
	
	public static String convertRelationship(List<Relationship> listRelationship) {
		String outputRelationship = "[";
		
		for (Relationship currentRelationship: listRelationship){
			outputRelationship += "relatioship";
			outputRelationship += currentRelationship.getRelationship();
		}
		
		outputRelationship = outputRelationship.substring(0,outputRelationship.length()-1);
		outputRelationship += "]";
		return outputRelationship;
	}
	
}