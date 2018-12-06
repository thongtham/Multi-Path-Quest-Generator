package mainGenerator;


import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import gameObject.Character;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//Test Pengyifan's Tree
		
		Tree t = new Tree();
		

		
		
		
		
		//Test ArrayList<Character>
		ArrayList<Character> listChar = new ArrayList<Character>();
		Character newChar1 = new Character();
		
		newChar1.setToGenericHuman("A", "Town");
		listChar.add(newChar1);
		newChar1.setToGenericHuman("B", "Town");
		listChar.add(newChar1);
		newChar1.setToGenericHuman("C", "Town");
		listChar.add(newChar1);
		newChar1.setToGenericHuman("D", "Town");
		listChar.add(newChar1);
		System.out.println(listChar);
		
		System.out.println("\n\n");
		
		
		//Test equals methods (only compare name)
		
		Character newChar2 = new Character();
		newChar2.setToGenericHuman("A", "Town");
		Character newChar3 = new Character();
		newChar3.setToGenericHuman("A", "sss");
		
		System.out.println(newChar2.equals(newChar3));
		
		Character newChar4 = new Character("A");
		Character newChar5 = new Character("A");
		
		System.out.println("/n" + "::");
		System.out.println(newChar4.equals(newChar5));
		
		
		
		//Test random integer
		/*
		
		int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		System.out.println(randomNum);
		randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		System.out.println(randomNum);
		randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		System.out.println(randomNum);
		randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		System.out.println(randomNum);
		
		*/
		
		
		// Test remove all [null] from ArrayList
		/*
		
		System.out.println("HHH");
		ArrayList<String> Stringoutput = new ArrayList<String>();
		System.out.println(Stringoutput);
		Stringoutput.add(null);
		System.out.println(Stringoutput);
		Stringoutput.add(null);
		Stringoutput.add("a");
		System.out.println(Stringoutput);
		Stringoutput.removeAll(Collections.singleton(null));
		System.out.println(Stringoutput);
		Stringoutput = null;
		System.out.println(Stringoutput);
		
		*/
		
	}

}
