package templateComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Template {
	
	String templateName;
	
	// this list contain the non-terminal <Component> that can still be broken down
	ArrayList<String> listComponentRule = new ArrayList<String>();
	
	
	
	
	public Template(){
		
	}
	
	public Template(String name, ArrayList<String> newListComponent){
		templateName = name;
		listComponentRule = newListComponent;
	}
	
	public Template(Template newTemplate){
		templateName = newTemplate.getName();
		listComponentRule = newTemplate.getComponentRule();
		
	}
	
	//This will remove all <Component> and set the list of <Components> to be 
	//that of [Conquest: Attack Enemy] 
	public void setTemplateConquest_Attack_Enemy()
	{
		templateName = "simpleKill";
		listComponentRule.clear();
		listComponentRule.add("goto");
		listComponentRule.add("defeat");
		listComponentRule.add("report");
	}

	
	public String getName(){
		return templateName;
	}
	
	public ArrayList<String> getComponentRule(){
		return listComponentRule;
	}
	
	public void setTemplateComponent(ArrayList<String> newListComponent)
	{
		listComponentRule = newListComponent;
	}
	
	public String toString()
	{
		System.out.println("TemplateToString");
		String strReturn = "";
		strReturn += templateName;
		strReturn += ":";
		strReturn += "{";
		String result = listComponentRule.toString();
		strReturn += result;
		strReturn += "}";
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










