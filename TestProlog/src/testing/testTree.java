package testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pengyifan.commons.collections.tree.TreeNode;

import gameObject.Location;

public class testTree {

	TreeNode componentTree = new TreeNode();
	
	public static void main (String args[]) {

		
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("ab");
		result.add("cd");
		result.add("ef");
		
		String pout = "kkk";
		pout += result;
		System.out.println(pout);
		
		
		
		String x = "ROOT";
		TreeNode testTree = new TreeNode(x);
		
		x = "1";
		TreeNode newChild1 = new TreeNode(x);
		x = "2";
		TreeNode newChild2 = new TreeNode(x);
		x = "3";
		TreeNode newChild3 = new TreeNode(x);
		
		//Location k = new Location();
		//k.setToGenericCity();
		String k = "11";
		TreeNode newChild11 = new TreeNode(k);
		
		// Child of Root
		testTree.add(newChild1);
		testTree.add(newChild2);
		testTree.add(newChild3);
		// Child of 1st Child
		newChild1.add(newChild11);
		
		
		
		
		
		System.out.println("///////////// Old test ////////////");
		

		//System.out.println(k.getLocationName());
		System.out.println(testTree.toString());
		System.out.println(testTree.getLeafObjects().toString());
		System.out.println(testTree.getFirstChild().toString());
		System.out.println(testTree.getLastChild().toString());
		System.out.println(testTree.children().toString());
		System.out.println(getComponentRule(testTree));

		
		System.out.println("///////////// Index ////////////");
		System.out.println(testTree.indexOf(newChild1));
		System.out.println(testTree.indexOf(newChild2));
		System.out.println(testTree.indexOf(newChild3));
		System.out.println(testTree.indexOf(newChild11));
		
		
		System.out.println("///////////// Preorder ////////////");
		
		List<TreeNode> itr = testTree.preorderList();
		for (TreeNode curTre : itr)
		{
			System.out.print(curTre.toString()+", ");
		}
		//testTree.pre
		
	}
	
	////////////////////////////////////////////////////////////
	

	
	
	public void addTreeChild(Object newChild, TreeNode nodeLocation)
	{
		TreeNode newNode = new TreeNode(newChild);
		nodeLocation.add(newNode);
	}

	
	public TreeNode getComponentTree(){
		return componentTree;
	}
	
	//This will return only the list of all Component Name (terminal and non-terminal)
	public static ArrayList<String> getComponentRule(TreeNode inputTree ){
		
		ArrayList<String> listComponentRule = new ArrayList<String>();
		List<TreeNode> listLeaves = inputTree.getLeaves();

		for (TreeNode currentLeaf : listLeaves){
			
			if (currentLeaf.getObject() instanceof Location) {
				Location curLo = (Location)currentLeaf.getObject();
				listComponentRule.add(curLo.getLocationName());
			}
			else {
				listComponentRule.add(currentLeaf.getObject().toString());
			}
		}
		
		return listComponentRule;
	}
	
	
	
	
}
