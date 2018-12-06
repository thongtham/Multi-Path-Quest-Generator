package templateComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pengyifan.commons.collections.tree.TreeNode;

import gameObject.Location;


public class Template {
	
	String templateName;

	TreeNode componentTree = new TreeNode();

	
	///////////////// Constructor ///////////////////////////////////
	
	public Template(){
		String root = "ROOT NODE";
		Component newCom = new Component(root);
		componentTree = new TreeNode(newCom);
		
	}
	
	public Template(String name){
		templateName = name;
		String root = "ROOT NODE";
		Component newCom = new Component(root);
		componentTree = new TreeNode(newCom);
	}
	
	public Template(Template newTemplate){
		templateName = newTemplate.getName();
		componentTree = newTemplate.getComponentTree();
		
	}
	////////////////////// Constructor ///////////////////////////////////

	
	/////////////////////////add data /////////////////////////////////

	
	
	
	
	
	/////////////////////////add data /////////////////////////////////
	
	
	
	///////////////////////// reset ///////////////////////////////////////////
	
	//This will remove all <Component> and set the list of <Components> to be 
	//that of [Conquest: Attack Enemy] 
	public void setTemplateConquest_Attack_Enemy()
	{
		templateName = "simpleKill";
	}
	
	public void setTemplateComponent(ArrayList<String> inputListString, TreeNode inputCom)
	{
		//start & goal state will not be created until the whole quest tree is completed
		//During breakdown, only the componentName (<goto>, etc.) && componentObject will only and created.
		TreeNode newNode = new TreeNode();
		Component newCom = new Component();
		Component curCom = (Component)inputCom.getObject();
		
		Object inputComObject = newCom.getComponentObject();
		Object inputComObject2 = newCom.getComponentObjectSecondary();
		Object inputComObject3 = newCom.getComponentObjectThird();
		
		for (String currentStr : inputListString){
				newCom = new Component(currentStr, inputComObject, inputComObject2, inputComObject3);
				newNode = new TreeNode(newCom);
				inputCom.add(newNode);
		}
	}

	public void setTemplateComponentTree(TreeNode inputComponentTree)
	{
		componentTree = inputComponentTree;
	}
	
	///////////////////////// reset ///////////////////////////////////////////
		
	

	/////////////////////////////  TREE  ///////////////////////////////////////
	
	//TreeNode componentTree = new TreeNode();
	
	public void addTreeChild(Object newChild, TreeNode nodeLocation)
	{
		TreeNode newNode = new TreeNode(newChild);
		nodeLocation.add(newNode);
	}

	
	public TreeNode getComponentTree(){
		return componentTree;
	}
	
	//This will return only the list of all Component Name (terminal and non-terminal)
	// This does not directly using "componentTree" because we don't want it to be static variable.
	public static ArrayList<String> getComponentRuleString(TreeNode inputTree ){
		
		ArrayList<String> listComponentRule = new ArrayList<String>();
		List<TreeNode> listLeaves = inputTree.getLeaves();

		for (TreeNode currentLeaf : listLeaves){
			
			// Legacy from testing, use for testing, ignore this IF
			if (currentLeaf.getObject() instanceof Location) {
				Location curLo = (Location)currentLeaf.getObject();
				listComponentRule.add(curLo.getLocationName());
			}
			//Main Focus of this methods
			else if (currentLeaf.getObject() instanceof Component){
				Component curCom = (Component)currentLeaf.getObject();
				listComponentRule.add(curCom.getComponentName());
			}
			else {
				listComponentRule.add(currentLeaf.getObject().toString());
			}
		}
		
		return listComponentRule;
	}
	
	/////////////////////////////  TREE  ///////////////////////////////////////
		
	
	///////////////////////////////////////////////////////////////////////////
	
	
	public String getName()
	{
		return templateName;
	}
	

	
	public String toString()
	{
		System.out.println("TemplateToString");
		String strReturn = "Template Name:";
		strReturn += templateName;
		strReturn += ", Component Rule:";
		strReturn += "";
		ArrayList<String> result = this.getComponentRuleString(componentTree);
		strReturn += result;
		strReturn += "";
		return strReturn;
	}
	
	
	
	/*
	public void appendTemplate(Template inputTemplate){
		
		List<Component> newListComponent = new ArrayList<Component>(listComponent);
		List<Component> inputListComponent = inputTemplate.getComponent();
		newListComponent.addAll(inputListComponent);
		listComponent = newListComponent;
	}
	
	public void appendListComponent(List<Component> inputListComponent){
		
		List<Component> newListComponent = new ArrayList<Component>(listComponent);
		newListComponent.addAll(inputListComponent);
		listComponent = newListComponent;
	}
	*/
	
	
	
}










