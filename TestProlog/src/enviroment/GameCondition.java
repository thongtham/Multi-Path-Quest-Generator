package enviroment;

import java.util.stream.Collectors;

public class GameCondition {

	public String desireString; // ex: PLAYER:isAlive:true  (doesn't have the 1:PLAYER:isAlive:true)
	
	public int componentLevel;
	
	// 1 = character
	// 2 = item = character = location    (item must be check in either Character or location. THUS: must be put in same list as either )
	// 3 = location  
	// 4 = relationship
	
	public String typeOfObject;
	
	//------------- Constructor ---------------//

	public GameCondition()
	{
	}
	
	public GameCondition(String inputStr, int inputLVL, String inputStr2)
	{
		desireString = new String (inputStr);
		componentLevel = inputLVL;
		typeOfObject = new String (inputStr2);
	}
	
	public GameCondition(GameCondition GCin)
	{
		desireString = new String (GCin.getDesireState());
		componentLevel = GCin.getComponentLevel();
		typeOfObject = new String (GCin.getTypeOfObject());
	}
	
	//------------- END Constructor ---------------//
	
	
	//------------- get DATA ---------------//
	
	public String getDesireState()
	{
		return desireString;
	}
	
	public int getComponentLevel()
	{
		return componentLevel;
	}
	
	public String getTypeOfObject()
	{
		return typeOfObject;
	}
	
	public String getName()
	{
		String HOLD_Name = desireString.substring(0, desireString.indexOf(":"));;
		return HOLD_Name;
	}
	
	public String getVariable1()
	{
		int first = desireString.indexOf(":");
		int second = desireString.indexOf(":", first + 1);
		
		String variable = desireString.substring(first+1,second);
		
		return variable;
	}
	
	public String getDesireValue1()
	{
		int first = desireString.indexOf(":");
		int second = desireString.indexOf(":", first + 1);
		
		String desireValue = desireString.substring(second+1,desireString.length());
		return desireValue;
	}
	
	
	// FOR ITEM ONLY, OR IT WILL ERROR
	public String getVariable2()
	{
		//EX: ITEM ["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		//EX:  [: number]        1                  2             3          4       5
		
		//["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		//String desireString;
		
		//["listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		String stringDesire_2 = desireString.substring(desireString.indexOf(":")+1, desireString.length());
		//["THE_ITEM_NAME:typeOfItem:luxury:001"]  
		String stringDesire_3 = stringDesire_2.substring(stringDesire_2.indexOf(":")+1, stringDesire_2.length());;
		//["typeOfItem:luxury:001"]  
		String stringDesire_4 = stringDesire_3.substring(stringDesire_3.indexOf(":")+1, stringDesire_3.length());;
		
		
		String variable_2 		= stringDesire_4.substring(0,stringDesire_4.indexOf(":"));
		String desireValue_2	= stringDesire_4.substring(stringDesire_4.indexOf(":")+1,stringDesire_4.lastIndexOf(":"));
		
		
		return variable_2;
	}
	
	// FOR ITEM ONLY, OR IT WILL ERROR
	public String getDesireValue2()
	{
		//EX: ITEM ["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		//EX:  [: number]        1                  2             3          4       
		
		//["LocationNAME:listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		//String desireString;
		
		//["listItemInLocation:THE_ITEM_NAME:typeOfItem:luxury:001"]  
		String stringDesire_2 = desireString.substring(desireString.indexOf(":")+1, desireString.length());
		//["THE_ITEM_NAME:typeOfItem:luxury:001"]  
		String stringDesire_3 = stringDesire_2.substring(stringDesire_2.indexOf(":")+1, stringDesire_2.length());;
		//["typeOfItem:luxury:001"]  
		String stringDesire_4 = stringDesire_3.substring(stringDesire_3.indexOf(":")+1, stringDesire_3.length());;
		
		
		String variable_2 		= stringDesire_4.substring(0,stringDesire_4.indexOf(":"));
		String desireValue_2	= stringDesire_4.substring(stringDesire_4.indexOf(":")+1,stringDesire_4.lastIndexOf(":"));
		
		return desireValue_2;
	}
	
	
	
	//------------- END get DATA ---------------//
	
	
	//------------- edit DATA ---------------//
	
	public void setDesire(String inputStr)
	{
		desireString = inputStr;
	}
	
	public void setComponentLevel(int input)
	{
		componentLevel = input;
	}
	
	
	
	//------------- END edit DATA ---------------//
	
	
	
	
	//-----------------------Override------------------------//
	
	@Override
	public String toString()
	{

			String strReturn = "\n      ";
			strReturn += "COM_LVL: = ";
			strReturn += componentLevel;
			strReturn += " [] ";
			strReturn += "DESIRE: = ";
			strReturn += desireString;
			return strReturn;
			
			
	}
	
	
	
	
}
