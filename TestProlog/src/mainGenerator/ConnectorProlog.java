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
import org.cs3.prolog.connector.session.PrologSession;
import org.cs3.prolog.connector.internal.session.socket.IterableQuery;
import org.cs3.prolog.connector.process.PrologException;
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

public class ConnectorProlog {

    public static String queryOnce(String input) {
        // get prolog process ... will be created if it doesn't already exist
    	
    	
        Map<String, Object> resultA = null;
        List<Map<String, Object>> resultAll = null;
    	
        try {
            PrologProcess process = Connector.newPrologProcess();
 
            // fill the factbase
            fillFactbaseWithDemoDataMAIN(process);
            	
            PrologSession session = null;
            try {
                // create a session for the current process
                session = process.getSession();
                // execute a lot of queries in a short period
                for (int i = 0; i < 1; i++) {

                    // use the queryOnce method from the session
                    // this prevents the creation of a new session for every query
                	
                	
                	resultA = session.queryOnce(input);
                    //resultAll = session.queryAll(input);
                    
                }
            } catch (PrologProcessException e) {
                e.printStackTrace();
            } finally {
                // make sure to close the session after the queries are finished
                if (session != null) {
                    session.dispose();
                }
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(resultA);
        
        if (resultA == null)
        {
        	return "null";
        }
        else 
        {
        	return resultA.toString(); 
        }
    }
 
    
    private static void fillFactbaseWithDemoDataMAIN(PrologProcess process) throws PrologProcessException, FileNotFoundException {

        // or by consulting a file
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/TestPrologPDT/QuestGeneratorMain.pl'");
         process.queryOnce(consultQuery);

    	
    }
    
    
}