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
 
import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Skill;
import gameObject.Status;




public class ConnectorDemo {
 
    public static void main(String[] args) {
        // get prolog process ... will be created if it doesn't already exist
        try {
            PrologProcess process = Connector.newPrologProcess();
 
            // fill the factbase
            fillFactbaseWithDemoData(process);
 
            
            /////////////////////////////////
            //MODIFY BY THONGTHAM CHONGMESUK
            
         // create query with the buildTerm method
            // this is the same as "father_of(Father, peter)"
            
            String queryTest = "testQuest(kill,john,jill,dead,Path)";
            Map<String, Object> resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            List<Map<String, Object>> resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);
            
            queryTest = "testQuest(kill,john,jill,d,Path)";
            resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);            
            
            queryTest = "getfirst([a,b,c],ActorName)";
            resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);           

            
            
            
            
            /////////////  Test Reading answer from Prolog  ///////////
            Scanner sc = new Scanner(new File("C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt"));
            
        	 while(sc.hasNextLine()){
        		 System.out.println(sc.nextLine());
        	 }
            
            
            queryTest = "testProtocolJava([abcdef,1,2,3]).";
            resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);  
            
            sc = new Scanner(new File("C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog/file.txt"));
            
            while(sc.hasNextLine()){
       		 System.out.println(sc.nextLine());
            }
            
            /////////////////////////////////
            //Quest Generator
            
            List<String> mainBody = new ArrayList<String>();
            
            
            
            
            
            
            
            //MODIFY BY THONGTHAM CHONGMESUK
            /////////////////////////////////
            
            
            
           
            // create query with the buildTerm method
            // this is the same as "father_of(Father, peter)"
            //String query = QueryUtils.bT("father_of", "Father", "peter");
            // get the first result of the query (ignore other results if there
            // are any)
            // Map<String, Object> result = process.queryOnce(query);
            //if (result == null) {
                // if the result is null, the query failed (no results)
            //    System.out.println("peter has no father");
            //} else {
                // if the query succeeds, the resulting map contains mappings
                // from variable name to the binding
            //    System.out.println(result.get("Father") + " is the father of peter");
            //}
 
            // create another query: father_of(john, Child)
            //query = QueryUtils.bT("father_of", "john", "Child");
            // get ALL results of the query as a list
            // every element in this list is one result
            // if the query fails, the list will be empty (but it won't be null)
            //List<Map<String, Object>> results = process.queryAll(query);
            //for (Map<String, Object> r : results) {
                // iterate over every result
            //    System.out.println(r.get("Child") + " is a child of john");
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static void fillFactbaseWithDemoData(PrologProcess process) throws PrologProcessException, FileNotFoundException {
        // this can be done by asserting facts directly
        //process.queryOnce("assertz(father_of(paul, peter))");
        //process.queryOnce("assertz(father_of(john, paul))");
        //process.queryOnce("assertz(father_of(john, ringo))");
        //process.queryOnce("assertz(father_of(john, george))");
 
        // or by consulting a file
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/QuestGenerator.pl'");
         process.queryOnce(consultQuery);

    	
        /////////////////////////////////
        //MODIFY BY THONGTHAM CHONGMESUK

    	//String consultQuery = QueryUtils.prologFileName(new File("src/Prolog1.pl"));
    	//process.queryOnce(consultQuery);
    	
      //Scanner sc = new Scanner(new File("src/Prolog1.pl"));
       
    	// while(sc.hasNextLine()){
    	// 	System.out.println("assertz(\""+sc.nextLine()+"\")");
    	// 	process.queryOnce("assertz(\""+sc.nextLine()+"\")");
    	// }
        
        //MODIFY BY THONGTHAM CHONGMESUK
        /////////////////////////////////
    	
    	
    	
    }
}