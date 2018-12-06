package templateComponent;

import gameObject.Item;
import gameObject.Location;

// This class will record the token used in quest generation, specifically, the 
// passing down of 'object' of each <Component>&[Component]
public class Token {

	Object tokenObject; //This is similar to Component's componentObject;
						// Ojbect can be either ....
						// [Character]
						// [Location]
						// [Item]
						// [Item] can be either [Information] OR [NOT Information] ???
	
	int preorderNumber;
	
	
	
	// In case of [Character]
	// 1st token = Information
	// 2nd token = Location
	// 3rd token = Itself
	
	// In case of [Location]
	// 1st token = Itself  (Location)
	
	// In case of [Item]
	// 1st token = Information
	// 2nd token = Location
	// 3rd token = Itself
	boolean isFirstInfoCanUse = true;
	boolean isSecondLocationCanUse = true;
	boolean isThirdItselfCanUse = true;
	
	//Object firstObject;
	//Object secondObject;
	//Object thirdObject;
	
	
	//////////////////////Constructor ///////////////////////////////////
	public Token()
	{

	}
	
	// When token is initiated, each type of object has different possible set of token
	public Token(int inputPreorder, Object inputObject)
	{
		System.out.println("CLASS = "+inputObject.getClass().toString());
		preorderNumber = inputPreorder;
		tokenObject = inputObject;
		
		isFirstInfoCanUse = true;
		isSecondLocationCanUse = true;
		isThirdItselfCanUse = true;
		
		if (inputObject instanceof Character ) {
			Character inputCharacter = (Character) inputObject;
			
			isFirstInfoCanUse = true;
			isSecondLocationCanUse = true;
			isThirdItselfCanUse = true;
			
			System.out.println("inputObj = Char");
			
		}
		else if (inputObject instanceof Location ) {
			Location inputLo = (Location) inputObject;
			
			isFirstInfoCanUse = true;
			isSecondLocationCanUse = true;
			isThirdItselfCanUse = true;
			
			System.out.println("inputObj = Location");
			
		}
		else if (inputObject instanceof Item ) {
			Item inputItem = (Item) inputObject;
			
			isFirstInfoCanUse = true;
			isSecondLocationCanUse = true;
			isThirdItselfCanUse = true;
			
			System.out.println("inputObj = Item");
			
		}
		else {
			System.out.println("Invalid type of token object" + inputObject.getClass());
		}
			
	}
	
	////////////////////// Constructor ///////////////////////////////////
	
	
	
	////////////////////// SETTING ///////////////////////////////////
	
	public void setFirstInfo(boolean inputBoo) {
		isFirstInfoCanUse = inputBoo;
	}
	
	public void setSecondLoc(boolean inputBoo) {
		
		isSecondLocationCanUse = inputBoo;
	}
	public void setThirdItself(boolean inputBoo) {
		
		isThirdItselfCanUse = inputBoo;
	}
	
	
	
	
	////////////////////// SETTING ///////////////////////////////////
	
	public boolean getInfo()
	{
		return isFirstInfoCanUse;
	}
	
	public boolean getLoc()
	{
		return isSecondLocationCanUse;
	}
	
	public boolean getItself()
	{
		return isThirdItselfCanUse;
	}
	
	
	//////////////////////Get info/token ///////////////////////////////////
	
	public int getCurrentTokenAsInt()
	{
		int returnInt = 0;
		if (isFirstInfoCanUse = true) {
			returnInt = 1;
		}
		else if (isSecondLocationCanUse = true) {
			returnInt = 2;
		}	
		else if (isThirdItselfCanUse = true) {
			returnInt = 3;
		}
		else 
		{
			returnInt = 0;
		}
		return returnInt;
	}
	
	
	public boolean getTokenInfo()
	{
		boolean TokenAvailable = false;
		
		
		if (isFirstInfoCanUse == true) {
			TokenAvailable = true;
			this.setFirstInfo(false);
			System.out.println("Info part is used");
		}
		else{
			System.out.println("Info part already use");
		}
		return TokenAvailable;
	}
	
	public boolean getTokenLoc()
	{
		boolean TokenAvailable = false;
		
		
		if (isSecondLocationCanUse == true) {
			TokenAvailable = true;
			setFirstInfo(false);
			setSecondLoc(false);
		}	
		else {
			System.out.println("Loc part already use");
		}
		return TokenAvailable;
	}
	
	public boolean getTokenItself()
	{
		boolean TokenAvailable = false;
		
		
		if (isThirdItselfCanUse == true) {
			TokenAvailable = true;
			setFirstInfo(false);
			setSecondLoc(false);
			setThirdItself(false);
		}
		else {
			System.out.println("Itself part already use");
		}
		return TokenAvailable;
	}
	
	
	//////////////////////END Get info/token ///////////////////////////////////
	
	
	public int getPreOrderNumber()
	{
		return preorderNumber;
	}
	
	public Object getTokenObject()
	{
		return tokenObject;
	}

	//-----------------OVERRIDE------------------------
	
	@Override
	public String toString()
	{
		String strReturn = "Preorder Num: " + preorderNumber + "  ";
		strReturn += tokenObject.toString();
		strReturn += "";
		strReturn += "\n";
	//	strReturn += "START_STATE: (now // out for debug)";
	//	strReturn += startState.toString();
	//	strReturn += "GOAL_STATE: (now // out for debug)";
	//	strReturn += goalState.toString();
	//	strReturn += ")";
		return strReturn;
	}
	
	
	
	
	
	
	
	
	
	
}
