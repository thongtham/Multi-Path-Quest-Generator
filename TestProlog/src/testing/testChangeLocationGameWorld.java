package testing;

import enviroment.GameWorld;

public class testChangeLocationGameWorld {

	public static void main (String[] args)
	{
		GameWorld test1GameWorld = new GameWorld();
		
		test1GameWorld.setBasicNewGameWorld();
		
		System.out.println(test1GameWorld.getPlayerLo());
		test1GameWorld.setPlayerLocation("jail");
		System.out.println(test1GameWorld.getPlayerLo());
		
	}
}
