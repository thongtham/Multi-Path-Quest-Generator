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

public class ConnectorDemoMY {

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
            
            String queryTest = "a(a,A).";
            Map<String, Object> resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            List<Map<String, Object>> resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);
            
            
            queryTest = "lengthList([a,b,c,d],A).";
            resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);

            
            fillFactbaseWithDemoData2(process);
            
            queryTest = "testJavaConnect('abc',A).";
            resultA = process.queryOnce(queryTest);
            System.out.println(resultA);
            resultAll = process.queryAll(queryTest);
            System.out.println(resultAll);
            
            queryTest = 		
            "startQuestPath(\r\n" + 
            "[[mob_npc_1,isAlive,false,z,zz,zzz]],\r\n" + 
            "[\r\n" + 
            "[player,isAlive,true,z,zz,zzz],\r\n" + 
            "[player,currentLocation,city,z,zz,zzz],\r\n" + 
            "[player,level,1,z,zz,zzz],\r\n" + 
            "[mob_npc_1,isAlive,true,z,zz,zzz],\r\n" + 
            "[mob_npc_1,currentLocation,jail,z,zz,zzz],\r\n" + 
            "[mob_npc_1,level,15,z,zz,zzz],\r\n" + 
            "[mob_npc_2,isAlive,true,z,zz,zzz],\r\n" + 
            "[mob_npc_2,currentLocation,city,z,zz,zzz],\r\n" + 
            "[mob_npc_2,level,15,z,zz,zzz],\r\n" + 
            "[city,locationName,city,z,zz,zzz],\r\n" + 
            "[city,locationEnvironment,sunny,z,zz,zzz],\r\n" + 
            "[city,listConnectLocation,jail,z,zz,zzz],\r\n" + 
            "[jail,locationName,jail,z,zz,zzz],\r\n" + 
            "[jail,listConnectLocation,city,z,zz,zzz]\r\n" + 
            "],\r\n" + 
            "[[enemy,test1,test2]],\r\n" + 
            "[],\r\n" + 
            "[a,0,start],\r\n" + 
            "PF).\r\n"; 
            		
            queryTest = 
            "startQuestPath([[messenger1,isAlive,true,z,zz,zzz],[blacksmith1,isAlive,true,z,zz,zzz]],[[player,isAlive,true,z,zz,zzz],[player,currentLocation,city,z,zz,zzz],[player,level,1,z,zz,zzz],[mob_npc_1,isAlive,true,z,zz,zzz],[mob_npc_1,currentLocation,city,z,zz,zzz],[mob_npc_1,level,15,z,zz,zzz],[king,isAlive,true,z,zz,zzz],[king,currentLocation,palace,z,zz,zzz],[king,level,20,z,zz,zzz],[king,listOccupation,king,z,zz,zzz],[king,listOccupation,noble,z,zz,zzz],[king,listStatus,rich,z,zz,zzz],[soldier1,isAlive,true,z,zz,zzz],[soldier1,currentLocation,city,z,zz,zzz],[soldier1,level,20,z,zz,zzz],[soldier1,listOccupation,soldier,z,zz,zzz],[soldier2,isAlive,true,z,zz,zzz],[soldier2,currentLocation,jail,z,zz,zzz],[soldier2,level,20,z,zz,zzz],[soldier2,listOccupation,soldier,z,zz,zzz],[soldier3,isAlive,true,z,zz,zzz],[soldier3,currentLocation,palace,z,zz,zzz],[soldier3,level,20,z,zz,zzz],[soldier3,listOccupation,soldier,z,zz,zzz],[doctor1,isAlive,true,z,zz,zzz],[doctor1,currentLocation,city,z,zz,zzz],[doctor1,level,20,z,zz,zzz],[doctor1,listOccupation,doctor,z,zz,zzz],[doctor1,listSkill,heal,z,zz,zzz],[blacksmith1,isAlive,true,z,zz,zzz],[blacksmith1,currentLocation,city,z,zz,zzz],[blacksmith1,level,20,z,zz,zzz],[blacksmith1,listOccupation,blacksmith,z,zz,zzz],[thief1,isAlive,true,z,zz,zzz],[thief1,currentLocation,jail,z,zz,zzz],[thief1,level,20,z,zz,zzz],[thief1,listOccupation,thief,z,zz,zzz],[thief1,listItem,lockpick,itemName,lockpick,50000],[thief1,listItem,lockpick,ownerName,thief1,50000],[thief1,listItem,lockpick,isOnGround,false,50000],[thief1,listItem,lockpick,currentLocation,jail,50000],[thief1,listItem,lockpick,typeOfItem,supply,50000],[thief1,listItem,lockpick,typeOfFunction,consumable,50000],[messenger1,isAlive,true,z,zz,zzz],[messenger1,currentLocation,city,z,zz,zzz],[messenger1,level,20,z,zz,zzz],[messenger1,listOccupation,messenger,z,zz,zzz],[miner1,isAlive,true,z,zz,zzz],[miner1,currentLocation,dungeon,z,zz,zzz],[miner1,level,20,z,zz,zzz],[miner1,listOccupation,miner,z,zz,zzz],[lumberjack1,isAlive,true,z,zz,zzz],[lumberjack1,currentLocation,forest,z,zz,zzz],[lumberjack1,level,20,z,zz,zzz],[lumberjack1,listOccupation,lumberjack,z,zz,zzz],[merchant1,isAlive,true,z,zz,zzz],[merchant1,currentLocation,city,z,zz,zzz],[merchant1,level,20,z,zz,zzz],[merchant1,listOccupation,merchant,z,zz,zzz],[merchant1,listStatus,rich,z,zz,zzz],[merchant1,listItem,potion_poison,itemName,potion_poison,50000],[merchant1,listItem,potion_poison,ownerName,merchant1,50000],[merchant1,listItem,potion_poison,isOnGround,false,50000],[merchant1,listItem,potion_poison,currentLocation,city,50000],[merchant1,listItem,potion_poison,typeOfItem,supply,50000],[merchant1,listItem,potion_poison,typeOfFunction,consumable,50000],[merchant1,listItem,potion_heal,itemName,potion_heal,50001],[merchant1,listItem,potion_heal,ownerName,merchant1,50001],[merchant1,listItem,potion_heal,isOnGround,false,50001],[merchant1,listItem,potion_heal,currentLocation,city,50001],[merchant1,listItem,potion_heal,typeOfItem,supply,50001],[merchant1,listItem,potion_heal,typeOfFunction,consumable,50001],[merchant1,listItem,antidote,itemName,antidote,50002],[merchant1,listItem,antidote,ownerName,merchant1,50002],[merchant1,listItem,antidote,isOnGround,false,50002],[merchant1,listItem,antidote,currentLocation,city,50002],[merchant1,listItem,antidote,typeOfItem,supply,50002],[merchant1,listItem,antidote,typeOfFunction,consumable,50002],[city,locationName,city,z,zz,zzz],[city,listConnectLocation,forest,z,zz,zzz],[city,listConnectLocation,jail,z,zz,zzz],[city,listConnectLocation,palace,z,zz,zzz],[dungeon,locationName,dungeon,z,zz,zzz],[dungeon,listConnectLocation,forest,z,zz,zzz],[dungeon,listItem,dagger,itemName,dagger,5],[dungeon,listItem,dagger,ownerName,null,5],[dungeon,listItem,dagger,isOnGround,true,5],[dungeon,listItem,dagger,currentLocation,dungeon,5],[dungeon,listItem,dagger,typeOfItem,weapon,5],[dungeon,listItem,dagger,typeOfFunction,equipment,5],[forest,locationName,forest,z,zz,zzz],[forest,listConnectLocation,city,z,zz,zzz],[forest,listConnectLocation,dungeon,z,zz,zzz],[forest,listItem,berry,itemName,berry,100000],[forest,listItem,berry,ownerName,null,100000],[forest,listItem,berry,isOnGround,true,100000],[forest,listItem,berry,currentLocation,forest,100000],[forest,listItem,berry,typeOfItem,supply,100000],[forest,listItem,berry,typeOfFunction,consumable,100000],[forest,listItem,poison_plant,itemName,poison_plant,101000],[forest,listItem,poison_plant,ownerName,null,101000],[forest,listItem,poison_plant,isOnGround,true,101000],[forest,listItem,poison_plant,currentLocation,forest,101000],[forest,listItem,poison_plant,typeOfItem,supply,101000],[forest,listItem,poison_plant,typeOfFunction,consumable,101000],[forest,listItem,poison_plant,itemName,poison_plant,101001],[forest,listItem,poison_plant,ownerName,null,101001],[forest,listItem,poison_plant,isOnGround,true,101001],[forest,listItem,poison_plant,currentLocation,forest,101001],[forest,listItem,poison_plant,typeOfItem,supply,101001],[forest,listItem,poison_plant,typeOfFunction,consumable,101001],[jail,locationName,jail,z,zz,zzz],[jail,listConnectLocation,city,z,zz,zzz],[palace,locationName,palace,z,zz,zzz],[palace,listConnectLocation,city,z,zz,zzz],[palace,listItem,diamond,itemName,diamond,1],[palace,listItem,diamond,ownerName,null,1],[palace,listItem,diamond,isOnGround,true,1],[palace,listItem,diamond,currentLocation,palace,1],[palace,listItem,diamond,typeOfItem,luxury,1],[palace,listItem,diamond,typeOfFunction,object,1]],[[friend,alpha,beta]],[],[a,0],PF)"; 
            System.out.println(queryTest);

            
            //System.out.println(queryTest);
            	
            PrologSession session = null;
            try {
                // create a session for the current process
                session = process.getSession();
                // execute a lot of queries in a short period
                for (int i = 0; i < 1; i++) {

                    // use the queryOnce method from the session
                    // this prevents the creation of a new session for every query
                	
                	System.out.println(queryTest);
                	
                	resultA = session.queryOnce(queryTest);
                    System.out.println(resultA);
                    
                    resultAll = session.queryAll(queryTest);
                    System.out.println(resultAll);
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
    }
 
    private static void fillFactbaseWithDemoData(PrologProcess process) throws PrologProcessException, FileNotFoundException {
        // this can be done by asserting facts directly
        //process.queryOnce("assertz(father_of(paul, peter))");
        //process.queryOnce("assertz(father_of(john, paul))");
        //process.queryOnce("assertz(father_of(john, ringo))");
        //process.queryOnce("assertz(father_of(john, george))");
 
        // or by consulting a file
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/TestPrologPDT/testLoop.pl'");
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
    
    private static void fillFactbaseWithDemoData2(PrologProcess process) throws PrologProcessException, FileNotFoundException {

        // or by consulting a file
         String consultQuery = QueryUtils.bT("reconsult", "'c:/Users/user/Desktop/Prolog Test/TestPrologPDT/QuestGeneratorMain.pl'");
         process.queryOnce(consultQuery);

    	
    }
    
    
}