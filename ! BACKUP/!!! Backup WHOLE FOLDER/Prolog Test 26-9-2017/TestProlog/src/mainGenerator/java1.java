package mainGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.Spring;

import templateComponent.AtomAction;
import templateComponent.AtomItem;

class java1 {
	
	public static void main (String args[]) throws FileNotFoundException{
		System.out.println("Hello World");
		
		/*
        Scanner sc = new Scanner(new File("src/Prolog1.pl"));
        while(sc.hasNextLine()){
        	System.out.println(sc.nextLine());
        }
        */
		
        AtomAction aa1 = new AtomAction();
        System.out.println(aa1.getAtomType());
        AtomItem aa2 = new AtomItem();
        System.out.println(aa2.getAtomType());
        
	}
	
}