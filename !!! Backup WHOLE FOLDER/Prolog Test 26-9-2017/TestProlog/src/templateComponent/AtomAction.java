package templateComponent;

import enviroment.GameState;

public class AtomAction extends AtomParent{
	
	final String type = "Action";
	
	GameState startGameState = new GameState();
	GameState goalGameState = new GameState();
	
	
	public AtomAction() {
	}
	
	@Override
	public String getAtomType() {
		return type;
	}
}
