package mainGenerator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.cs3.prolog.connector.Connector;
import org.cs3.prolog.connector.common.QueryUtils;
import org.cs3.prolog.connector.process.PrologProcess;
import org.cs3.prolog.connector.process.PrologProcessException;

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

public class QuestToProlog {


	//static QuestFrame mainQuestFrame = new QuestFrame();
	static GameState currentGameState = new GameState();
	static List<Component> QuestComponent = new ArrayList<Component>();
	
	public static void main(String args[])
	{
		currentGameState.setBasicNewGameState();
		
		Component firstCom = new Component();
		Component secondCom = new Component();
		
		firstCom.setComponentAs_SimpleKill("jack", "market");
		//System.out.println(firstCom.toString());
		//System.out.println(firstCom.getStartGameState().toString());
		//System.out.println(firstCom.getStartGameState().getCharListAsString());
		//System.out.println(firstCom.getStartGameState().getRelationshipListAsString());
		//System.out.println(firstCom.getGoalGameState().toString());
		//System.out.println(firstCom.getGoalGameState().getCharListAsString());
		
		try {
			            PrologProcess process = Connector.newPrologProcess();
			 
			            // fill the factbase
			            fillFactbaseWithDemoData(process);
			 
			            String AllItem = currentGameState.getItemListAsString();
			            //AllItem = "[item(provision,food),item(provision,water),item(provision,food),item(equipment,weapon),item(equipment,armor),item(medicine,potion),item(medicine,bondage),item(medicine,alcohol),item(medicine,tonic),item(luxury,gem),item(luxury,gold),item(luxury,fancyWare),item(information,book),item(information,letter),item(information,note),item(information,document),item(information,tape)]";
			            
			            String AllCharacter = currentGameState.getCharListAsString();
						
			            String AllRelation = currentGameState.getRelationshipListAsString();
			            //AllRelation = "[relationship(jack,john,friend),relationship(jack,john,friend),relationship(jack,john,friend),relationship(jack,john,friend)]";
			            
						String AllAttribute = "X";
						AllAttribute = "[attribute(player,player),attribute(jack,weak)]";
						
						String kill = "kill";
						String Goal = firstCom.getGoalGameState().getCharListAsGoal();
						Goal = Goal.replaceAll("dead", "alive");
						String Path = "quest_Start_Point";
						int LoopCounter = 1;
						
						System.out.println("testQuestGameState("+AllItem+","+AllCharacter+","+AllRelation+","+AllAttribute+","+kill+","+Goal+","+Path+","+LoopCounter+")");
			            String queryTest = "testQuestGameState("+AllItem+","+AllCharacter+","+AllRelation+","+AllAttribute+","+kill+","+Goal+","+Path+","+LoopCounter+")";
			            process.queryAll(queryTest);
			            //Map<String, Object> resultA = process.queryOnce(queryTest);
			            //System.out.println(resul A);
			            //List<Map<String, Object>> resultAll = process.queryAll(queryTest);
			            //System.out.println(resultAll);
			            
			   
			            /////////////  Test Reading answer from Prolog  ///////////
			            Scanner sc = new Scanner(new File("C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/filePath.txt"));
			            
			        	while(sc.hasNextLine())
			        	{
			        		System.out.println(sc.nextLine());
			        	}
			     
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
		
		
		//Just checking if run the correct file
		//System.out.println("Start Quest Generator");
		
		//Initialize core attribute
		//currentGameState.setNewGameState();
		//mainQuestFrame.setListTemplateBasicKill();
		
		//System.out.println(mainQuestFrame.ToString());
		//List<Template> listTemplateMainQuest = mainQuestFrame.getListTemplate();
		
		//for (Template currentTemplate : listTemplateMainQuest )
		//{
		//	List<Component> listComponentMainQuest = currentTemplate.getComponent();
		//	
		//	for (Component currentComponent : listComponentMainQuest )
		//	{
		//		GameState startState = currentComponent.getStartGameState();
		//		GameState goalState = currentComponent.getGoalGameState();
		//		
		//		String listCharStartState = startState.getCharListAsString();
		//		String listCharGoalState = goalState.getCharListAsString();
		//		
		//		
		//		try {
		//            PrologProcess process = Connector.newPrologProcess();
		// 
		//            // fill the factbase
		//            fillFactbaseWithDemoData(process);
		// 
		//            String AllItem = "";
		//            String AllCharacter = listCharStartState;
		//			String AllRelation = "";
		//			String AllAttribute = "";
		//			String kill = "kill";
		//			String Goal = listCharGoalState;
		//			String Path = "test";
		//			
		//            String queryTest = "testQuestGameState("+AllItem+","+AllCharacter+","+AllRelation+","+AllAttribute+","+kill+","+Goal+","+Path+")";
		//            
		//            Map<String, Object> resultA = process.queryOnce(queryTest);
		//           System.out.println(resultA);
		//            List<Map<String, Object>> resultAll = process.queryAll(queryTest);
		//            System.out.println(resultAll);
		            
		   
		            /////////////  Test Reading answer from Prolog  ///////////
		//            Scanner sc = new Scanner(new File("C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt"));
		//            
		//        	while(sc.hasNextLine())
		//        	{
		//        		System.out.println(sc.nextLine());
		//        	}
		     
		//        } catch (Exception e) {
		//            e.printStackTrace();
		//        }
		//		
		//	}
		//}
		
		
	}
		
	
	
	
	

	
	public void generateQuest(String typeOfQuest){
		
	}
	
	
	
	
	
	
	//Utility function
	//
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
	
	
	private static void fillFactbaseWithDemoData(PrologProcess process) throws PrologProcessException, FileNotFoundException {

        // or by consulting a file
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/QuestGenerator.pl'");
         process.queryOnce(consultQuery);

    	
    	
    }
	
}
