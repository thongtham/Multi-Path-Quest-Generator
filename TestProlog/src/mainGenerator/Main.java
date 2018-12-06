package mainGenerator;


import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


import com.pengyifan.commons.collections.tree.*;

import gameObject.Character;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	      ArrayList<String> al = new ArrayList<String>();
	      al.add("pen");
	      al.add("pencil");
	      al.add("ink");
	      al.add("notebook");

	      System.out.println("ArrayList contains the string 'ink pen': "
	                                           +al.contains("ink pen"));
	      System.out.println("ArrayList contains the string 'pen': "
	                                             +al.contains("pen"));
	      System.out.println("ArrayList contains the string 'pencil': "
	                                          +al.contains("pencil"));
	      System.out.println("ArrayList contains the string 'book': "
	                                           +al.contains("book"));
	      
	      System.out.println("notebook".contains("n"));

	      
	      
	      
	      ArrayList<Integer> al2 = new ArrayList<Integer>();
	      al2.add(1);
	      al2.add(99);
	      al2.add(56);
	      al2.add(13);
	      al2.add(44);
	      al2.add(6);

	      System.out.println("'1' is present in arraylist: "+al2.contains(1));
	      System.out.println("'55' is present in arraylist: "+al2.contains(55));
	      System.out.println("'44' is there in arraylist: "+al2.contains(44));
	      System.out.println("'7' is there in arraylist: "+al2.contains(7));
	   
		
		
		//Test Pengyifan's Tree
		/*
		
		TreeNode testTree = new TreeNode();
		
		Integer x = 2;
		TreeNode newChild = new TreeNode(x);
		Integer y = 3;
		TreeNode newChild2 = new TreeNode(y);
		Integer z = 4;
		TreeNode newChild3 = new TreeNode(z);
		
		Character k = new Character();
		k.setToGenericHuman("jack", "market");
		TreeNode newChild4 = new TreeNode(k);
		
		testTree.add(newChild);
		testTree.add(newChild2);
		testTree.add(newChild4);
		newChild.add(newChild3);
		

		
		System.out.println(testTree.toString());
		System.out.println(testTree.getLeaves().toString());

		*/
		
		
		
		//Test ArrayList<Character>
		/*
		
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
		
		*/
		
		
		
		//Test equals methods (only compare name)
		/*
		 
		Character newChar2 = new Character();
		newChar2.setToGenericHuman("A", "Town");
		Character newChar3 = new Character();
		newChar3.setToGenericHuman("A", "sss");
		
		System.out.println(newChar2.equals(newChar3));
		
		Character newChar4 = new Character("A");
		Character newChar5 = new Character("A");
		
		System.out.println("/n" + "::");
		System.out.println(newChar4.equals(newChar5));
		
		*/
		
		
		
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
