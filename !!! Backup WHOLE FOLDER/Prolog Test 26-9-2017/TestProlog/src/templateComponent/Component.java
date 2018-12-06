package templateComponent;

import enviroment.GameState;

public class Component {
	
	String componentName;
	GameState startState;
	GameState goalState;
	
	
	
	
	public Component(){
		
	}
	
	public Component(String name){
		componentName = name;
	}
	
	public GameState getStartCondition(){
		return startState;
	}
	public GameState getGoalCondition(){
		return goalState;
	}
}
