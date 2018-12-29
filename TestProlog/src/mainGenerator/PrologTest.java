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

public class PrologTest {


	//static QuestFrame mainQuestFrame = new QuestFrame();
	static GameWorld currentGameWorld = new GameWorld();
	static List<Component> QuestComponent = new ArrayList<Component>();
	
	public static void main(String args[])
	{

		try {
			            PrologProcess process = Connector.newPrologProcess();
			 
			            // fill the factbase
			            fillFactbaseWithDemoData(process);

			            
			            
			            // ****************************************************************************************
			            // ********************need B in "writeFile([a,b,c],[kkk],B)"; *********************************************
			            // ****************************************************************************************
			            // ********************process.queryOnce need 'B' (unknown) of it will throw error *******************************************************************
			            // ****************************************************************************************
			            
			            String queryTest = "writeFile([a,b,c],[kkk],B)";
			            Map<String, Object> resultA = process.queryOnce(queryTest);
			            System.out.println(resultA);
			            //List<Map<String, Object>> resultAll = process.queryAll(queryTest);
			            //System.out.println(resultAll);  
			            
			            
			            
			            
			            
			            
			            
			            
			            
			            
			            
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
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/test.pl'");
         process.queryOnce(consultQuery);

    	
    	
    }
	
}
