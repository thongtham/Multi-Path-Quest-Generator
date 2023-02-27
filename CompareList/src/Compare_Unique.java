
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Compare_Unique {
	
	
	//********  Argument Guide
	//*
	//* 	1st string = [Only integer] 	= 	quest template number (folder name that hold all quest info)
	//*		2nd string = [Only integer]		= 	basic GameState to compare (include the [] that cover whole state, ex. [[a,b,c],[a,b,b],[a,b,e]].
	//*
	//********
	
	//*This is basic arg[1] that will be used (kept here for easy copy-paste)
	//*
	//*[[player,isAlive,true,z,zz,zzz],[player,currentLocation,city,z,zz,zzz],[player,level,1,z,zz,zzz],[mob_npc_1,isAlive,true,z,zz,zzz],[mob_npc_1,currentLocation,city,z,zz,zzz],[mob_npc_1,level,15,z,zz,zzz],[king,isAlive,true,z,zz,zzz],[king,currentLocation,palace,z,zz,zzz],[king,level,20,z,zz,zzz],[king,listOccupation,king,z,zz,zzz],[king,listOccupation,noble,z,zz,zzz],[king,listStatus,rich,z,zz,zzz],[soldier1,isAlive,true,z,zz,zzz],[soldier1,currentLocation,city,z,zz,zzz],[soldier1,level,20,z,zz,zzz],[soldier1,listOccupation,soldier,z,zz,zzz],[soldier3,isAlive,true,z,zz,zzz],[soldier3,currentLocation,palace,z,zz,zzz],[soldier3,level,20,z,zz,zzz],[soldier3,listOccupation,soldier,z,zz,zzz],[doctor1,isAlive,true,z,zz,zzz],[doctor1,currentLocation,city,z,zz,zzz],[doctor1,level,20,z,zz,zzz],[doctor1,listOccupation,doctor,z,zz,zzz],[doctor1,listSkill,heal,z,zz,zzz],[blacksmith1,isAlive,true,z,zz,zzz],[blacksmith1,currentLocation,city,z,zz,zzz],[blacksmith1,level,20,z,zz,zzz],[blacksmith1,listOccupation,blacksmith,z,zz,zzz],[thief1,isAlive,true,z,zz,zzz],[thief1,currentLocation,jail,z,zz,zzz],[thief1,level,20,z,zz,zzz],[thief1,listOccupation,thief,z,zz,zzz],[thief1,listItem,lockpick,itemName,lockpick,300101],[thief1,listItem,lockpick,ownerName,thief1,300101],[thief1,listItem,lockpick,isOnGround,false,300101],[thief1,listItem,lockpick,currentLocation,jail,300101],[thief1,listItem,lockpick,typeOfItem,supply,300101],[thief1,listItem,lockpick,typeOfFunction,consumable,300101],[miner1,isAlive,true,z,zz,zzz],[miner1,currentLocation,dungeon,z,zz,zzz],[miner1,level,20,z,zz,zzz],[miner1,listOccupation,miner,z,zz,zzz],[lumberjack1,isAlive,true,z,zz,zzz],[lumberjack1,currentLocation,forest,z,zz,zzz],[lumberjack1,level,20,z,zz,zzz],[lumberjack1,listOccupation,lumberjack,z,zz,zzz],[merchant1,isAlive,true,z,zz,zzz],[merchant1,currentLocation,city,z,zz,zzz],[merchant1,level,20,z,zz,zzz],[merchant1,listOccupation,merchant,z,zz,zzz],[merchant1,listStatus,rich,z,zz,zzz],[merchant1,listItem,potion_poison,itemName,potion_poison,200101],[merchant1,listItem,potion_poison,ownerName,merchant1,200101],[merchant1,listItem,potion_poison,isOnGround,false,200101],[merchant1,listItem,potion_poison,currentLocation,city,200101],[merchant1,listItem,potion_poison,typeOfItem,supply,200101],[merchant1,listItem,potion_poison,typeOfFunction,consumable,200101],[merchant1,listItem,potion_heal,itemName,potion_heal,200201],[merchant1,listItem,potion_heal,ownerName,merchant1,200201],[merchant1,listItem,potion_heal,isOnGround,false,200201],[merchant1,listItem,potion_heal,currentLocation,city,200201],[merchant1,listItem,potion_heal,typeOfItem,supply,200201],[merchant1,listItem,potion_heal,typeOfFunction,consumable,200201],[merchant1,listItem,antidote,itemName,antidote,200301],[merchant1,listItem,antidote,ownerName,merchant1,200301],[merchant1,listItem,antidote,isOnGround,false,200301],[merchant1,listItem,antidote,currentLocation,city,200301],[merchant1,listItem,antidote,typeOfItem,supply,200301],[merchant1,listItem,antidote,typeOfFunction,consumable,200301],[city,locationName,city,z,zz,zzz],[city,listConnectLocation,forest,z,zz,zzz],[city,listConnectLocation,dungeon,z,zz,zzz],[city,listConnectLocation,jail,z,zz,zzz],[city,listConnectLocation,palace,z,zz,zzz],[dungeon,locationName,dungeon,z,zz,zzz],[dungeon,listConnectLocation,city,z,zz,zzz],[dungeon,listItem,treasure_map,itemName,treasure_map,900201],[dungeon,listItem,treasure_map,ownerName,null,900201],[dungeon,listItem,treasure_map,isOnGround,true,900201],[dungeon,listItem,treasure_map,currentLocation,dungeon,900201],[dungeon,listItem,treasure_map,typeOfItem,quest,900201],[dungeon,listItem,treasure_map,typeOfFunction,object,900201],[forest,locationName,forest,z,zz,zzz],[forest,listConnectLocation,city,z,zz,zzz],[forest,listItem,berry,itemName,berry,100101],[forest,listItem,berry,ownerName,null,100101],[forest,listItem,berry,isOnGround,true,100101],[forest,listItem,berry,currentLocation,forest,100101],[forest,listItem,berry,typeOfItem,supply,100101],[forest,listItem,berry,typeOfFunction,consumable,100101],[forest,listItem,poison_plant,itemName,poison_plant,100201],[forest,listItem,poison_plant,ownerName,null,100201],[forest,listItem,poison_plant,isOnGround,true,100201],[forest,listItem,poison_plant,currentLocation,forest,100201],[forest,listItem,poison_plant,typeOfItem,supply,100201],[forest,listItem,poison_plant,typeOfFunction,consumable,100201],[forest,listItem,poison_plant,itemName,poison_plant,100202],[forest,listItem,poison_plant,ownerName,null,100202],[forest,listItem,poison_plant,isOnGround,true,100202],[forest,listItem,poison_plant,currentLocation,forest,100202],[forest,listItem,poison_plant,typeOfItem,supply,100202],[forest,listItem,poison_plant,typeOfFunction,consumable,100202],[jail,locationName,jail,z,zz,zzz],[jail,listConnectLocation,city,z,zz,zzz],[jail,listItem,treasure_map,itemName,treasure_map,900201],[jail,listItem,treasure_map,ownerName,null,900201],[jail,listItem,treasure_map,isOnGround,true,900201],[jail,listItem,treasure_map,currentLocation,jail,900201],[jail,listItem,treasure_map,typeOfItem,quest,900201],[jail,listItem,treasure_map,typeOfFunction,object,900201],[palace,locationName,palace,z,zz,zzz],[palace,listConnectLocation,city,z,zz,zzz],[palace,listItem,diamond,itemName,diamond,900101],[palace,listItem,diamond,ownerName,null,900101],[palace,listItem,diamond,isOnGround,true,900101],[palace,listItem,diamond,currentLocation,palace,900101],[palace,listItem,diamond,typeOfItem,luxury,900101],[palace,listItem,diamond,typeOfFunction,object,900101]]

	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		
		double basic_GS_size 	= 0;
		double new_GS_size		= 0;
		double biggest_GS_size 	= 0;
		
		double totalRatio 	= 0;
		int number_of_path	= 0;
		
		double totalComponent 	= 0;
		
		
		String folderDirectory = "C:/Users/user/Desktop/Prolog Test/Cal_Folder/";
		//Args #1
		folderDirectory += args[0];
		
		String finalFolderPath = "";

		//This level will go to [Q1,Q2,....] level
		ArrayList<String> folderList_d1 = findFoldersInDirectory(folderDirectory);
		//This is where the path to [Q1,Q2...] are stored
		ArrayList<String> folderList_d1_path = new ArrayList<String>();
		
		
		//add [Q1,Q2, path to the list]
		for(String folderName : folderList_d1)
		{
			String newpath = folderDirectory;
			newpath	+= "/";
			newpath += folderName;
			folderList_d1_path.add(newpath);
		}
		
		//now for each path to [Q1,Q2,....] , we would get the path to each folder of [a,b,c,d,e...]
		for(String pathNameQ : folderList_d1_path)
		{
			//Access folder and get file name
			File folder = new File(pathNameQ);
			ArrayList<String> fileNameList =  listFilesForFolder(folder);
			ArrayList<String> fileNamePath =  new ArrayList<String>();
			
			for(String fileName : fileNameList)
			{
				String newpath = pathNameQ;
				newpath	+= "/";
				newpath += fileName;
				fileNamePath.add(newpath);
				
			}
			
			//Access to Meta File to get the Component's type
			for (String filePath : fileNamePath)
			{
				System.out.println("filePath " + filePath);
				
				ArrayList<String> componentNameList = new ArrayList<String>();
				String scannerNextLine;
				
				
				File file = new File(filePath);
				Scanner sc = new Scanner(file);
				int count = 0;
				boolean isEndOfComponent = false;
				boolean isAutoFormat	= true;  // true = has FINAL, false = has LINE
				
				while (sc.hasNext())
				{
					scannerNextLine = sc.nextLine();
					System.out.println("scannerNextLine "+scannerNextLine);
					
					if (scannerNextLine.contains("line"))
					{
						isAutoFormat = false;
						continue;
					}
					
					if (scannerNextLine.contains("FINAL"))
					{
						count++;
						if (count >= 2)
						{
							break;
						}
						continue;
					}
					
					if (isAutoFormat)
					{
						componentNameList.add(scannerNextLine.substring(0, scannerNextLine.indexOf(":")));
						System.out.println(scannerNextLine.substring(0, scannerNextLine.indexOf(":")));
						
					}
					else 
					{
						if (scannerNextLine.contains("USER") || scannerNextLine.trim().isEmpty())
						{
							break;
						}
						else
						{
						componentNameList.add(scannerNextLine.substring(0, scannerNextLine.indexOf(":")));
						System.out.println(scannerNextLine.substring(0, scannerNextLine.indexOf(":")));
						}
					}
					
				}
					
				//Create baseline arraylist to be addAll & Retain
				ArrayList<String> temp_base_intersect =  new ArrayList<String>();
				
				for (String listSTR : componentNameList)
				{
					if (temp_base_intersect.contains(listSTR))
					{
						//do nothing
					}
					else
					{
						temp_base_intersect.add(listSTR);
					}
				}
				
				
				System.out.println("Calculate Weight of Choice");
				
				System.out.println(componentNameList);
				System.out.println(temp_base_intersect);
				
				//calculate the ratio, weight of choice
				double intersect_size 	= temp_base_intersect.size();
				double union_size		= componentNameList.size();
				double ratio 			= intersect_size/union_size;
				
				System.out.println(intersect_size);
				System.out.println(union_size);
				System.out.println("ratio = " + ratio);
				
				totalRatio += ratio;
				totalComponent += union_size;
				number_of_path += 1;
			}
			
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Total sum Ratio = " + totalRatio + "/" + number_of_path ); 
		System.out.println("Uniqueness  = " + totalRatio/number_of_path); 
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Total sum Component = " + totalComponent + "/" + number_of_path ); 
		System.out.println("Narrative Content = " + totalComponent/number_of_path); 
		
	}
	
	
	

    public static ArrayList<String> listFilesForFolder(final File folder) {
    	ArrayList<String> returnSTR = new ArrayList<String>();
    	
	    for (final File fileEntry : folder.listFiles()){
	        if (fileEntry.isDirectory()){
	            listFilesForFolder(fileEntry);
	        } 
	        else{
	        	returnSTR.add(fileEntry.getName());
	            //System.out.println(fileEntry.getName());
	        }
	    }
	    return returnSTR;
    }
	
	
    public static ArrayList<String> findFoldersInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
    	
        FileFilter directoryFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
    		
        File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
        ArrayList<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
        for (File directoryAsFile : directoryListAsFile) {
            foldersInDirectory.add(directoryAsFile.getName());
        }

        return foldersInDirectory;
    }
    
    
    
    public static <T> List<T> intersect(List<T> c1, List<T> c2) {
        List<T> inter = new ArrayList<>(c1);
        inter.retainAll(c2);
        return inter;
    }

    public static <T> List<T> intersect(List<T> first, List<T>... rest) {
        if (rest.length == 0)
            return first;

        List<T> second = rest[0];

        first = intersect(first,second);
        rest = Arrays.copyOfRange(rest, 1, rest.length);

        return intersect(first, rest);
    }
    
    

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }


    
    
	
}
